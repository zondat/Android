package vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent;

public interface IGameListener {

    void updateGameObject(GameObject gameObject);
    void notifyGameObjectDeleted(GameObject gameObject);
}
