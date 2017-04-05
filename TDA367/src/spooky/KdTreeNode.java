package spooky;

import java.awt.geom.Point2D;

/**
 * Created by miktor on 2017-04-05.
 */

public class KdTreeNode<T>
{
    Point2D.Double point = null;
    KdTreeNode<T> right = null;
    KdTreeNode<T> left = null;
    KdTreeNode parent = null;
    boolean flag = false;
    int splitIndex = 0;
    T data;

    public KdTreeNode()
    {

    }

    public KdTreeNode(Point2D.Double p, KdTreeNode<T> parent, T data, int split)
    {
        this.parent = parent;
        this.point = p;
        this.data = data;
        splitIndex = split;
    }

    double axisDistance(Point2D.Double a)
    {
        if(splitIndex == 0)
        {
            return Math.abs(a.getX() - point.getX());
        }
        else
        {
            return Math.abs(a.getY() - point.getY());
        }
    }

    public double getPointIndexValue(int index)
    {
        if((index % 2) == 0)
        {
            return point.getX();
        }
        else
        {
            return point.getY();
        }
    }

    void unflagAll()
    {
        flag = false;
        if (right != null) right.unflagAll();
        if (left != null) left.unflagAll();
    }

    Point2D.Double vectorTo(Point2D.Double p)
    {
        return new Point2D.Double(p.getX() - point.getX(), p.getY() - p.getY());
    }

    Point2D.Double DirectionTo(Point2D.Double p)
    {
        Point2D.Double d = vectorTo(p);
        double len = d.distance(new Point2D.Double(0,0));
        d.x = d.x / len;
        d.y = d.y / len;
        return d;
    }
};