package vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent;

public interface ICollider {
    void collide(ICollider other);
    Location getLocation();
    float size();
}
