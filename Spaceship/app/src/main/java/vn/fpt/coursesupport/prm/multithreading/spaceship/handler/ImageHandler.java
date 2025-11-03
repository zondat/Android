package vn.fpt.coursesupport.prm.multithreading.spaceship.handler;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vn.fpt.coursesupport.prm.multithreading.spaceship.MainActivity;
import vn.fpt.coursesupport.prm.multithreading.spaceship.R;
import vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent.Game;
import vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent.GameObject;
import vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent.IGameListener;

public class ImageHandler implements IGameListener {

    private Game owner;
    private Context context;
    private Map<Integer, Bitmap> loadedImages;
    private Map<GameObject, ImageView> objectViews;

    public ImageHandler(Game game) {
        owner = game;
        loadedImages = new HashMap<>();
        objectViews = new HashMap<>();
    }

    public void loadImages(Context context) {
        Bitmap image = null;

        for (GameObject gameObject: owner.getObjectList()) {
            int drawableId = gameObject.getImage();

            // Check if the image is already loaded
            if (!loadedImages.containsKey(drawableId)) {
                // Load image
                Bitmap original = BitmapFactory.decodeResource(context.getResources(), drawableId);
                image= Bitmap.createScaledBitmap(original, (int)gameObject.getWidth(), (int)gameObject.getHeight(), true);
                loadedImages.put(drawableId, image);
                original.recycle();

                // Attach the loaded image to a object view;
                ImageView objectView = new ImageView(context);
                objectView.setImageResource(drawableId);
                objectView.setRotation(90);

                objectViews.put(gameObject, objectView);
            }
        }
    }

    public void updateImage() {
        for (GameObject gameObject : objectViews.keySet()) {
            objectViews.get(gameObject).setX(gameObject.getX());
            objectViews.get(gameObject).setY(gameObject.getY());
        }
    }

    public void clear() {
        for (Bitmap image : loadedImages.values()) {
            image.recycle();
        }
        loadedImages.clear();
    }

    public Collection<ImageView> getObjectViews() {
        return objectViews.values();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void updateGameObject(GameObject gameObject) {
        int drawableId = gameObject.getImage();

        // Check if the image is already loaded
        if (!loadedImages.containsKey(drawableId)) {
            // Load image
            Bitmap original = BitmapFactory.decodeResource(context.getResources(), drawableId);
            Bitmap image = Bitmap.createScaledBitmap(original, (int) gameObject.getWidth(), (int) gameObject.getHeight(), true);
            loadedImages.put(drawableId, image);
            original.recycle();

            // Attach the loaded image to a object view;
            ImageView objectView = new ImageView(context);
            objectView.setImageResource(drawableId);
            objectView.setRotation(90);

            objectViews.put(gameObject, objectView);
        }
    }

    @Override
    public void notifyGameObjectDeleted(GameObject gameObject) {
        objectViews.remove(gameObject);
    }

    public View getObjectView(GameObject gameObject) {
        return objectViews.get(gameObject);
    }
}
