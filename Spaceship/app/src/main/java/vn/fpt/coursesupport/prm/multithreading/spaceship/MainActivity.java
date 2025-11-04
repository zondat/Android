package vn.fpt.coursesupport.prm.multithreading.spaceship;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.HashMap;
import java.util.Map;

import vn.fpt.coursesupport.prm.multithreading.spaceship.entity.Alien;
import vn.fpt.coursesupport.prm.multithreading.spaceship.entity.Spaceship;
import vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent.Game;
import vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent.GameObject;
import vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent.IGameListener;
import vn.fpt.coursesupport.prm.multithreading.spaceship.handler.ITimeListener;
import vn.fpt.coursesupport.prm.multithreading.spaceship.handler.KeyboardHandler;
import vn.fpt.coursesupport.prm.multithreading.spaceship.handler.GameTick;

public class MainActivity extends AppCompatActivity implements ITimeListener, IGameListener {

    private Game game;
    private ConstraintLayout rootLayout;
    private KeyboardHandler eventHandler;
    private ConstraintLayout.LayoutParams params;
    private Map<GameObject, View> objectViews;
//    private Map<Integer, Bitmap> loadedImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        objectViews = new HashMap<>();
//        loadedImages = new HashMap<>();

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

        game = new Game();
        game.addListener(this);

        Spaceship spaceship = game.createSpaceship(250, 1500);
        Alien alien1 = game.createAlien(120, 250);

        eventHandler = new KeyboardHandler(spaceship);
        eventHandler.start();
        GameTick.INSTANCE.addListener(game);
        GameTick.INSTANCE.addListener(this);
        GameTick.INSTANCE.start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
         eventHandler.addEventKey(keyCode);
        return super.onKeyDown(keyCode, event);
    }

    public synchronized void updateLocation() {
        runOnUiThread(() -> {
            for (GameObject gameObject: game.getObjectList()) {
                View view = objectViews.get(gameObject);

                if (view!=null) {
                    // Update location
                    view.setX(gameObject.getX());
                    view.setY(gameObject.getY());
                }
            }
        });
    }

    @Override
    public void updateGameTick(long currentTime) {
        updateLocation();
    }

    @Override
    public void updateNewGameObject(GameObject gameObject) {
        // Add to UI
        runOnUiThread(() ->{
            View objectView = loadImage(gameObject);
            rootLayout.addView(objectView, params);

             // Store to map
            objectViews.put(gameObject, objectView);
        });
    }

    @Override
    public synchronized void updateGameObjectDeleted(GameObject gameObject) {
        // Get associated view
        View objectView = objectViews.get(gameObject);

        // Remove from UI
        runOnUiThread(() ->{
            rootLayout.removeView(objectView);
        });

        // Remove tracking
        objectViews.remove(gameObject);
    }

    private View loadImage(GameObject gameObject) {
        int drawableId = gameObject.getImage();

        // Attach the loaded image to a object view;
        ImageView objectView = new ImageView(this);
        objectView.setImageResource(drawableId);
        objectView.setRotation(gameObject.getRotation());

        return objectView;
    }

}