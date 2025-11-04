package vn.fpt.coursesupport.prm.multithreading.spaceship.entity;

import vn.fpt.coursesupport.prm.multithreading.spaceship.R;
import vn.fpt.coursesupport.prm.multithreading.spaceship.entity.path.HorizontalPath;
import vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent.Bot;
import vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent.Game;
import vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent.IAttacking;
import vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent.ICollider;
import vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent.IPath;
import vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent.Location;
public class Alien extends Bot implements IAttacking {

    public Alien(Game owner, float x, float y) {
        super(owner, x, y, 12, 20, 10, 10);
        image = R.drawable.phantom;
        path = new HorizontalPath();
    }

    public Alien(Game owner, Location initLocation) {
        super(owner, initLocation, 12, 20, 10, 10);
        image = R.drawable.phantom;
        path = new HorizontalPath();
    }

    @Override
    public void shoot() {
        game.createBullet(location.toDown(50), false);
    }

    @Override
    public void collide(ICollider other) {
        if (!(other instanceof Alien)) destroy();
    }
}
