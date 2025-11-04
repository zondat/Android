package vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent;

import java.util.ArrayList;
import java.util.List;

import vn.fpt.coursesupport.prm.multithreading.spaceship.entity.Alien;
import vn.fpt.coursesupport.prm.multithreading.spaceship.entity.Bullet;
import vn.fpt.coursesupport.prm.multithreading.spaceship.entity.Spaceship;
import vn.fpt.coursesupport.prm.multithreading.spaceship.handler.ITimeListener;

public class Game implements ITimeListener {

    private Spaceship mainCharacter;
    private List<GameObject> objectList;
    private List<ICollider> colliderList;
    private List<IGameListener> listeners;

    public Game() {
        objectList = new ArrayList<>();
        colliderList = new ArrayList<>();
        listeners = new ArrayList<>();
    }

    public Bullet createBullet(float x, float y, boolean toUp) {
        Bullet aBullet = new Bullet(this, x, y, toUp);
        objectList.add(aBullet);
        colliderList.add(aBullet);
        notifyNewObject(aBullet);
        return aBullet;
    }

    public Bullet createBullet(Location location, boolean toUp) {
        Bullet aBullet = new Bullet(this, location, toUp);
        objectList.add(aBullet);
        colliderList.add(aBullet);
        notifyNewObject(aBullet);
        return aBullet;
    }

    public Alien createAlien(Location location) {
        Alien anAlien = new Alien(this, location);
        objectList.add(anAlien);
        colliderList.add(anAlien);
        notifyNewObject(anAlien);
        return anAlien;
    }

    public Alien createAlien(float x, float y) {
        Alien anAlien = new Alien(this, x, y);
        objectList.add(anAlien);
        colliderList.add(anAlien);
        notifyNewObject(anAlien);
        return anAlien;
    }

    public Spaceship createSpaceship(float x, float y) {
        mainCharacter = new Spaceship(this, x, y);
        objectList.add(mainCharacter);
        colliderList.add(mainCharacter);
        notifyNewObject(mainCharacter);
        return mainCharacter;
    }

    public Spaceship createSpaceship(Location location) {
        mainCharacter = new Spaceship(this, location);
        objectList.add(mainCharacter);
        colliderList.add(mainCharacter);
        notifyNewObject(mainCharacter);
        return mainCharacter;
    }

    public Spaceship getMainCharacter() {
        return mainCharacter;
    }

    public List<GameObject> getObjectList() {
        return objectList;
    }

    public List<ICollider> getColliderList() {
        return colliderList;
    }

    public void remove(GameObject object) {
        objectList.remove(object);
        notifyGameObjectDeleted(object);
    }

    public void addListener(IGameListener l) {
        if (!listeners.contains(l)) listeners.add(l);
    }

    public void removeListener(IGameListener l) {
        listeners.remove(l);
    }

    public void notifyNewObject(GameObject gameObject) {
        for (IGameListener listener : listeners) {
            listener.updateNewGameObject(gameObject);
        }
    }

    public void notifyGameObjectDeleted(GameObject gameObject) {
        for (IGameListener listener : listeners) {
            listener.updateGameObjectDeleted(gameObject);
        }
    }

    @Override
    public void updateGameTick(long currentTime) {
        for (GameObject gameObject: objectList) {
            gameObject.updateLocation();
        }
    }
}
