package vn.fpt.coursesupport.prm.multithreading.spaceship.entity.path;

import vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent.GameObject;
import vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent.IPath;

public class VerticalIncrementalPath implements IPath {

    private int screenWidth, screenHeight;
    private int coeff = 1;

    public VerticalIncrementalPath() {
        screenWidth = GameObject.screenWidth;
        screenHeight = GameObject.screenHeight;
    }

    @Override
    public float nextLocationX(float currentX, float currentY, float Vx, float Vy) {
        float nextX = currentX + coeff*Vx;
        if (0<nextX && nextX<screenWidth) return nextX;
        coeff = -coeff;
        return currentX + coeff*Vx;
    }

    @Override
    public float nextLocationY(float currentX, float currentY, float Vx, float Vy) {
        return currentY + 1;
    }

}
