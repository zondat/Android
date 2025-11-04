package vn.fpt.coursesupport.prm.multithreading.spaceship;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import vn.fpt.coursesupport.prm.multithreading.spaceship.entity.Spaceship;
import vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent.Game;
import vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent.GameObject;
import vn.fpt.coursesupport.prm.multithreading.spaceship.handler.CollisionHandler;
import vn.fpt.coursesupport.prm.multithreading.spaceship.handler.KeyboardHandler;
import vn.fpt.coursesupport.prm.multithreading.spaceship.handler.GameTick;
import vn.fpt.coursesupport.prm.multithreading.spaceship.handler.ITimeListener;
import vn.fpt.coursesupport.prm.multithreading.spaceship.handler.AnimationHandler;

public class MainActivity extends AppCompatActivity implements ITimeListener {

    private ConstraintLayout rootLayout;
    private CollisionHandler collisionHandler;
    private AnimationHandler imageHandler;
    private KeyboardHandler eventHandler;
    private ConstraintLayout.LayoutParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        params = new ConstraintLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        params.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
        params.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
        params.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;

        rootLayout = findViewById(R.id.main);

        // Screen resolution
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;
        Log.d("Screen", "Width: " + screenWidth + " Height: " + screenHeight);
        Log.d("Screen", "Density: " + displayMetrics.density + " DPI: " + displayMetrics.densityDpi);

        // Game Initialization
        GameObject.screenWidth = screenWidth;
        GameObject.screenHeight = screenHeight;

        Game game = new Game();
        collisionHandler = new CollisionHandler(game);
        imageHandler = new AnimationHandler(game);
        imageHandler.setContext(this);

        Spaceship spaceship = game.createSpaceship(250, 1500);
//        Alien alien1 = game.createAlien(120, 120);
//        Alien alien2 = game.createAlien(90, 120);

        eventHandler = new KeyboardHandler(spaceship);
        eventHandler.start();
        GameTick.INSTANCE.addListener(game);
        GameTick.INSTANCE.addListener(collisionHandler);
        GameTick.INSTANCE.addListener(this);
        GameTick.INSTANCE.start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
         eventHandler.addEventKey(keyCode);
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void updateGameTick(long currentTime) {
        runOnUiThread(() -> {
            imageHandler.updateImage();
        });
    }

//    @Override
//    public void updateNewGameObject(GameObject gameObject) {
//        runOnUiThread(() -> {
//            rootLayout.addView(imageHandler.getObjectView(gameObject), params);
//        });
//    }
//
//    @Override
//    public void updateGameObjectDeleted(GameObject gameObject) {
//        runOnUiThread(() -> {
//            rootLayout.removeView(imageHandler.getObjectView(gameObject));
//        });
//    }
}