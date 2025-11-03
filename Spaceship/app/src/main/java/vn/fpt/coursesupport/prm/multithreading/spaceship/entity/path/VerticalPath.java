package vn.fpt.coursesupport.prm.multithreading.spaceship.entity.path;

import vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent.IPath;

public class VerticalPath implements IPath {
    @Override
    public float nextLocationX(float currentX, float currentY, float Vx, float Vy) {
        return currentX;
    }

    @Override
    public float nextLocationY(float currentX, float currentY, float Vx, float Vy) {
        return currentY + Vy;
    }
}
