package vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent;

public interface IPath {
    float nextLocationX(float currentX, float currentY, float Vx, float Vy);
    float nextLocationY(float currentX, float currentY, float Vx, float Vy);
}
