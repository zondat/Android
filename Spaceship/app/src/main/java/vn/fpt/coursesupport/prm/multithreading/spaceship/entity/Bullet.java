package vn.fpt.coursesupport.prm.multithreading.spaceship.entity;

import vn.fpt.coursesupport.prm.multithreading.spaceship.R;
import vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent.Game;
import vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent.ICollider;
import vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent.IDestroyable;
import vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent.Location;
import vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent.MovingObject;

public class Bullet extends MovingObject implements ICollider, IDestroyable {

    private boolean isDestroyed = false;
    public Bullet(Game owner, float x, float y) {
        super(owner,x, y, 5, 7, 0, -25);
        image = R.drawable.bullet;
    }

    public Bullet(Game owner, Location initLocation) {
        super(owner,initLocation, 5, 7, 0, -25);
        image = R.drawable.bullet;
    }

    @Override
    public void updateLocation() {
        super.updateLocation();
        if (reachScreenHeight()) destroy();
    }

    @Override
    public void collide(ICollider other) {}

    @Override
    public void destroy() {
        isDestroyed = true;
    }

    @Override
    public boolean isDestroyed() {
        return isDestroyed;
    }
}
