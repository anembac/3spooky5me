package spooky;

import java.awt.geom.Point2D;

/**
 * Created by miktor on 2017-04-03.
 */
public class Platform extends WorldObject {

    public Platform(Point2D.Double position)
    {
        this.position = position;
    }

    @Override
    public void frame(double dt) {
        super.frame(dt);
    }

    @Override
    public void render() {
        super.render();
    }
}
