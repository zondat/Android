package vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent;

public class Location {
    protected float x;
    protected float y;

    public Location() {}

    public Location(float x, float y) {
        setX(x);
        setY(y);
    }

    public float distanceTo(Location other) {
        float dx = this.x - other.x;
        float dy = this.y - other.y;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Location toDown(float delta) {
        return new Location(x, y + delta);
    }

    public Location toUp(float delta) {
        return new Location(x, y - delta);
    }

    public Location toLeft(float delta) {
        return new Location(x - delta, y);
    }

    public Location toRight(float delta) {
        return new Location(x + delta, y);
    }
}
