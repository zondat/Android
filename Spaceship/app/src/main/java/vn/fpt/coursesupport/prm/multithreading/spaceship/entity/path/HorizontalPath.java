package vn.fpt.coursesupport.prm.multithreading.spaceship.entity.path;

import android.util.DisplayMetrics;

import vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent.IPath;

public class HorizontalPath implements IPath {

    private int screenWidth, screenHeight;
    private int coeff = 1;

    public HorizontalPath() {
        DisplayMetrics metrics = new DisplayMetrics();
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;
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
        return currentY;
    }
}
