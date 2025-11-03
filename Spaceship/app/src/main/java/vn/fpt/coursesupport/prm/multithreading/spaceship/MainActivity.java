package vn.fpt.coursesupport.prm.multithreading.spaceship;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import vn.fpt.coursesupport.prm.multithreading.spaceship.entity.Alien;
import vn.fpt.coursesupport.prm.multithreading.spaceship.entity.Spaceship;
import vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent.Game;
import vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent.GameObject;
import vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent.IGameListener;
import vn.fpt.coursesupport.prm.multithreading.spaceship.handler.CollisionHandler;
import vn.fpt.coursesupport.prm.multithreading.spaceship.handler.EventHandler;
import vn.fpt.coursesupport.prm.multithreading.spaceship.handler.GameTick;
import vn.fpt.coursesupport.prm.multithreading.spaceship.handler.ITimeListener;
import vn.fpt.coursesupport.prm.multithreading.spaceship.handler.ImageHandler;

public class MainActivity extends AppCompatActivity implements ITimeListener, IGameListener {

    private ConstraintLayout rootLayout;
    private CollisionHandler collisionHandler;
    private ImageHandler imageHandler;
    private EventHandler eventHandler;
    private ScheduledExecutorService executor;
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

        // Init game
        Game game = new Game();


        collisionHandler = new CollisionHandler(game);
        imageHandler = new ImageHandler(game);
        imageHandler.setContext(this);

        game.addListener(imageHandler);
        game.addListener(this);

        Spaceship spaceship = game.createSpaceship(250, 1050);
//        Alien alien1 = game.createAlien(120, 120);
//        Alien alien2 = game.createAlien(90, 120);

        eventHandler = new EventHandler(spaceship);
        eventHandler.start();
        GameTick.INSTANCE.addTimeListener(game);
        GameTick.INSTANCE.addTimeListener(collisionHandler);
        GameTick.INSTANCE.addTimeListener(this);
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

    @Override
    public void updateGameObject(GameObject gameObject) {
        runOnUiThread(() -> {
            rootLayout.addView(imageHandler.getObjectView(gameObject), params);
        });
    }

    @Override
    public void notifyGameObjectDeleted(GameObject gameObject) {
        runOnUiThread(() -> {
            rootLayout.removeView(imageHandler.getObjectView(gameObject));
        });
    }
}