package spooky;

/**
 * Created by miktor on 2017-04-03.
 */
import java.awt.geom.Point2D;
public abstract class WorldObject {

    protected boolean collidable = true;
    protected Point2D.Double position;
    public WorldObject(){}

    public void render(){}

    public void frame(double dt) {}


}
