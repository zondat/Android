package vn.fpt.coursesupport.prm.multithreading.spaceship.entity;

import vn.fpt.coursesupport.prm.multithreading.spaceship.R;
import vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent.Game;
import vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent.IAttacking;
import vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent.ICollider;
import vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent.IControllable;
import vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent.IDestroyable;
import vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent.Location;
import vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent.MovingObject;

public class Spaceship extends MovingObject implements IAttacking, ICollider, IDestroyable, IControllable {

    private boolean isDestroyed = false;

    public Spaceship(Game owner, float x, float y) {
        super(owner, x, y, 12, 20, 15, 15);
        image = R.drawable.mig21;
    }

    public Spaceship(Game owner,Location initLocation) {
        super(owner, initLocation, 12, 20, 15, 15);
        image = R.drawable.mig21;
    }

    @Override
    public void collide(ICollider other) {
        destroy();
    }

    @Override
    public void destroy() {
        isDestroyed = true;
    }

    @Override
    public boolean isDestroyed() {
        return isDestroyed;
    }

    @Override
    public void moveUp() {
        updateY(getY() - velocityY);
    }

    @Override
    public void moveDown() {
        updateY(getY() + velocityY);
    }

    @Override
    public void moveLeft() {
        updateX(getX() - velocityX);
    }

    @Override
    public void moveRight() {
        updateX(getX() + velocityX);
    }

    @Override
    public void updateLocation() {}

    @Override
    public void shoot() {
        game.createBullet(location.toUp(50 ));
    }
}
