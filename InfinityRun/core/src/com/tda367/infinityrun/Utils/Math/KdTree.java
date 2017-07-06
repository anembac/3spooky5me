package com.tda367.infinityrun.Utils.Math;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/*

    This is the Kd-tree used for collision and distance calculations. General info about Kd-trees can be found here:

    https://en.wikipedia.org/wiki/K-d_tree

 */

public class KdTree<T> {

    private KdTreeNode<T> parentNode = null;
    private final int dimensions = 2;

    public KdTree() {

    }


    //checks if value is even
    private boolean isEven(int value) {
        return (value % 2) == 0;
    }

    private double getIndexValue(Point2D.Double val, int index) {
        return (isEven(index) ? val.getX() : val.getY());
    }



    //insert method for placing 2d points into cNode for the tree to where it's suited.
    public KdTreeNode<T> insert(Point2D.Double point, T data) {
        KdTreeNode<T> cNode = parentNode;

        if (parentNode == null) {
            parentNode = new KdTreeNode<T>(point, null, data, 0);
            cNode = parentNode;
        } else {
            while (true) {
                if (cNode.getPointIndexValue(cNode.splitIndex) > getIndexValue(point, cNode.splitIndex)) {
                    if (cNode.left != null) {
                        cNode = cNode.left;
                    } else {
                        cNode.left = new KdTreeNode<T>(point, cNode, data, (cNode.splitIndex + 1) % dimensions);
                        cNode = cNode.left;
                        break;
                    }
                } else {
                    if (cNode.right != null) {
                        cNode = cNode.right;
                    } else {
                        cNode.right = new KdTreeNode<T>(point, cNode, data, (cNode.splitIndex + 1) % dimensions);
                        cNode = cNode.right;
                        break;
                    }
                }
            }
        }
        return cNode;
    }

    //this method gets the K nearest neighbors from a  point in the tree.

    public List<KdTreeNode<T>> getKNN(Point2D.Double obj, int k) {
        KdTreeNode<T> node = parentNode;

        if (node == null) return new ArrayList<KdTreeNode<T>>();
        List<KdTreeNode<T>> cBest = new ArrayList<KdTreeNode<T>>();

        double worst = Double.MAX_VALUE;

        cBest.add(node);
        node.flag = true;

        while (node != null) {
            if (getIndexValue(obj, node.splitIndex) < getIndexValue(node.point, node.splitIndex)) {
                if (node.left != null && !node.left.flag) {
                    node = node.left;
                    worst = checkPoint(cBest, obj, node, worst, k);
                    node.flag = true;
                } else if (node.right != null && !node.right.flag && (node.axisDistance(obj) <= worst || cBest.size() < k)) {
                    node = node.right;
                    worst = checkPoint(cBest, obj, node, worst, k);
                    node.flag = true;
                } else {
                    if (node.right != null) node.right.flag = false;
                    if (node.left != null) node.left.flag = false;
                    node = node.parent;
                }
            } else {
                if (node.right != null && !node.right.flag) {
                    node = node.right;
                    worst = checkPoint(cBest, obj, node, worst, k);
                    node.flag = true;
                } else if (node.left != null && !node.left.flag && (node.axisDistance(obj) <= worst || cBest.size() < k)) {
                    node = node.left;
                    worst = checkPoint(cBest, obj, node, worst, k);
                    node.flag = true;
                } else {
                    if (node.right != null) node.right.flag = false;
                    if (node.left != null) node.left.flag = false;
                    node = node.parent;
                }
            }
        }

        parentNode.flag = false;

        return cBest;
    }

    private void insert(KdTreeNode<T> node) {
        KdTreeNode<T> cNode = parentNode;
        node.left = null;
        node.right = null;
        node.parent = null;

        if (parentNode == null) {
            parentNode = node;
        } else {
            while (true) {
                if (getIndexValue(cNode.point, cNode.splitIndex) > getIndexValue(node.point, cNode.splitIndex)) {
                    if (cNode.left != null) {
                        cNode = cNode.left;
                    } else {
                        cNode.left = node;
                        node.splitIndex = (cNode.splitIndex + 1) % dimensions;
                        node.parent = cNode;
                        break;
                    }
                } else {
                    if (cNode.right != null) {
                        cNode = cNode.right;
                    } else {
                        cNode.right = node;
                        node.splitIndex = (cNode.splitIndex + 1) % dimensions;
                        node.parent = cNode;
                        break;
                    }
                }
            }
        }
    }



    //removes point from tree, and adjusts the rest of the tree to this.
    public void removePoint(KdTreeNode<T> pNode) {
        List<KdTreeNode<T>> childs = getAllNodes(pNode);
        for (int i = 0; i < childs.size() - 1; i++) {
            insert(childs.get(i));
        }
        pNode = null;
    }

    public List<KdTreeNode<T>> rangeSearch2D(float l, float r, float t, float b) {
        List<KdTreeNode<T>> output = new ArrayList<KdTreeNode<T>>();
        rangeSearch2DIntern(l, r, t, b, output, parentNode, 0);
        return output;
    }

    // Returns the all child nodes to the input node, and the input node, and all children of these....,
    // if the input node is unwanted the place in the return list is always the last.
    // this function removes the nodes from the tree but does NOT delete them.
    private void rangeSearch2DIntern(float l, float r, float t, float b, List<KdTreeNode<T>> output, KdTreeNode<T> parent, int n) {
        if (parent == null) return;
        n = n % 2;
        if ((n % 2) == 0) {
            if (parent.point.x < l) {
                n++;
                rangeSearch2DIntern(l, r, t, b, output, parent.right, n);
            } else if (parent.point.x > r) {
                n++;
                rangeSearch2DIntern(l, r, t, b, output, parent.left, n);
            } else {
                n++;
                n = n % 2;
                if (parent.point.y > b && parent.point.y < t) {
                    output.add(parent);
                }
                rangeSearch2DIntern(l, r, t, b, output, parent.left, n);
                rangeSearch2DIntern(l, r, t, b, output, parent.right, n);
            }
        } else {
            if (parent.point.y < b) {
                n++;
                rangeSearch2DIntern(l, r, t, b, output, parent.right, n);
            } else if (parent.point.y > t) {
                n++;
                rangeSearch2DIntern(l, r, t, b, output, parent.left, n);
            } else {
                n++;
                n = n % 2;
                if (parent.point.x > l && parent.point.x < r) {
                    output.add(parent);
                }
                rangeSearch2DIntern(l, r, t, b, output, parent.left, n);
                rangeSearch2DIntern(l, r, t, b, output, parent.right, n);
            }
        }
    }


    // Returns the all child nodes to the input node, and the input node, and all children of these....,
    // if the input node is unwanted the place in the return list is always the last.
    // this function removes the nodes from the tree but does NOT delete them.
    private List<KdTreeNode<T>> getAllNodes(KdTreeNode<T> node) {
        if (node == null) return new ArrayList<KdTreeNode<T>>();
        List<KdTreeNode<T>> output = new ArrayList<KdTreeNode<T>>();

        KdTreeNode<T> pNode = node;
        while (pNode != null) {
            if (pNode.right != null && !pNode.right.flag) {
                pNode = pNode.right;
            } else if (pNode.left != null && !pNode.left.flag) {
                pNode = pNode.left;
            } else {
                output.add(pNode);

                if (pNode.parent != null) {
                    if (pNode.parent.left == pNode) (pNode).parent.left = null;
                    if (pNode.parent.right == pNode) pNode.parent.right = null;
                }

                if (pNode == parentNode) {
                    parentNode = null;
                }

                if (pNode == node) {
                    return output;
                }

                pNode = pNode.parent;
            }
        }

        return output;
    }


    private double checkPoint(List<KdTreeNode<T>> inp, Point2D.Double a, KdTreeNode<T> b, double worst, int k) {
        double dist = a.distance(b.point);
        if (inp.size() < k || dist < worst) {
            boolean ins = false;
            for (int i = 0; i < inp.size(); i++) {
                if (a.distance(inp.get(i).point) > dist) {
                    inp.add(i, b);
                    ins = true;
                    break;
                }
            }
            if (!ins) inp.add(b);

            while (inp.size() > k) {
                inp.remove(k);
            }

            if (inp.size() == k) {
                worst = a.distance(inp.get(k - 1).point);
            }
        }

        return worst;
    }
}
