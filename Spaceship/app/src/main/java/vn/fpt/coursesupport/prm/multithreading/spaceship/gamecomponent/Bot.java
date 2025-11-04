package vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent;

import vn.fpt.coursesupport.prm.multithreading.spaceship.entity.path.HorizontalPath;
import vn.fpt.coursesupport.prm.multithreading.spaceship.entity.path.VerticalIncrementalPath;
import vn.fpt.coursesupport.prm.multithreading.spaceship.entity.path.VerticalPath;

public abstract class Bot extends MovingObject implements ICollider, IDestroyable {

    protected IPath path;
    protected boolean isDestroyed = false;

    public Bot(Game owner, float x, float y, float w, float h, float vx, float vy) {
        super(owner, x, y, w, h, vx, vy);
        setPath(path);
    }

    public Bot(Game owner, Location initLocation, float w, float h, float vx, float vy) {
        super(owner, initLocation, w, h, vx, vy);
        setPath(path);
    }


    public void setHorizontalPath() {
        path = new HorizontalPath();
    }

    public void setVerticalPath() {
        path = new VerticalPath();
    }

    public void setVerticalIncremental() {
        path = new VerticalIncrementalPath();
    }

    @Override
    public void updateLocation() {
        location.setX(path.nextLocationX(location.getX(), location.getY(), velocityX, velocityY));
        location.setY(path.nextLocationY(location.getX(), location.getY(), velocityX, velocityY));
    }

    public IPath getPath() {
        return path;
    }

    public void setPath(IPath path) {
        this.path = path;
    }

    @Override
    public void collide(ICollider other) {
        destroy();
    }

    @Override
    public void destroy() {
        isDestroyed = true;
        game.remove(this);
    }

    @Override
    public boolean isDestroyed() {
        return isDestroyed;
    }
}
