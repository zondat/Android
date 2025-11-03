package vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent;

public abstract class MovingObject extends GameObject {

    protected float velocityX = 5;
    protected float velocityY = 5;

    public MovingObject(Game owner, float x, float y, float w, float h, float vx, float vy) {
        super(owner, x, y, w, h);
        setVelocityX(vx);
        setVelocityY(vy);
    }

    public MovingObject(Game owner, Location initLocation, float w, float h, float vx, float vy) {
        super(owner, initLocation, w, h);
        setVelocityX(vx);
        setVelocityY(vy);
    }

    @Override
    public void updateLocation() {
        location.setX(location.getX() + velocityX);
        location.setY(location.getY() + velocityY);
    }

    public float getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(float velocityX) {
        this.velocityX = velocityX;
    }

    public float getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(float velocityY) {
        this.velocityY = velocityY;
    }

}
