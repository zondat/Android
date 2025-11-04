package vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent;

public interface IGameListener {
    void updateNewGameObject(GameObject gameObject);
    void updateGameObjectDeleted(GameObject gameObject);
}
