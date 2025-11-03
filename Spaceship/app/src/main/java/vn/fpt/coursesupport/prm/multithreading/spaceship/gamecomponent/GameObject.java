package vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent;

import android.util.DisplayMetrics;

public abstract class GameObject {
    private static long idGenerator = 0;
    protected long id;
    protected Game game;
    protected Location location;
    protected float width = 0;
    protected float height = 0;
    protected int image;
    private DisplayMetrics metrics;

    public GameObject(Game owner, float x, float y, float w, float h) {
        id = idGenerator++;
        game = owner;
        location = new Location(x, y);
        width = w;
        height = h;
        metrics = new DisplayMetrics();
    }

    public GameObject(Game owner, Location initLocation, float w, float h) {
        id = idGenerator++;
        game = owner;
        location = initLocation;
        width = w;
        height = h;
        metrics = new DisplayMetrics();
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public abstract void updateLocation();

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void updateX(float newX) {
        location.setX(newX);
    }

    public void updateY(float newY) {
        location.setY(newY);
    }

    public float getX() {
        return location.getX();
    }

    public float getY() {
        return location.getY();
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float size() {
        return (float) Math.sqrt(width*width + height*height);
    }

    public boolean reachScreenWidth() {
        return location.x <= 0 || location.x >= metrics.widthPixels;
    }

    public boolean reachScreenHeight() {
        return location.y >= metrics.heightPixels || location.y <=0;
    }
}
