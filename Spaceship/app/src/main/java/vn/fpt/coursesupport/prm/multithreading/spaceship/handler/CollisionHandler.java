package vn.fpt.coursesupport.prm.multithreading.spaceship.handler;

import java.util.List;

import vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent.Game;
import vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent.GameObject;
import vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent.ICollider;
import vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent.Location;

public class CollisionHandler implements ITimeListener {
    private Game owner;

    public CollisionHandler(Game game) {
        owner = game;
    }
    private boolean handle(ICollider object1, ICollider object2) {
        Location location1 = object1.getLocation();
        Location location2 = object2.getLocation();
        float distance = location1.distanceTo(location2);

        if (distance<object1.size() || distance<object2.size()) {
            object1.collide(object2);
            object2.collide(object1);
            return true;
        }
        return false;
    }

    public void resolve() {
        List<ICollider> colliderList = owner.getColliderList();
        int n = colliderList.size();
        for (int i=0; i<n-1; i++) {
            for (int j=i+1; j<n; j++) {
                handle(colliderList.get(i), colliderList.get(j));
            }
        }
    }

    @Override
    public void updateGameTick(long currentTime) {
        resolve();
    }
}
