package vn.fpt.coursesupport.prm.multithreading.spaceship.handler;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent.Game;
import vn.fpt.coursesupport.prm.multithreading.spaceship.gamecomponent.GameObject;

public class AnimationHandler {

    private Game owner;
    private Context context;
    private Map<Integer, Bitmap> loadedImages;
    private Map<GameObject, ImageView> objectViews;

    public AnimationHandler(Game game) {
        owner = game;
        loadedImages = new HashMap<>();
        objectViews = new HashMap<>();
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

//    @Override
//    public void updateNewGameObject(GameObject gameObject) {
//        int drawableId = gameObject.getImage();
//
//        // Check if the image is already loaded
//        if (!loadedImages.containsKey(drawableId)) {
//            // Load image
//            Bitmap original = BitmapFactory.decodeResource(context.getResources(), drawableId);
//            Bitmap image = Bitmap.createScaledBitmap(
//                    original, (int) gameObject.getWidth(), (int) gameObject.getHeight(), true);
//            loadedImages.put(drawableId, image);
//            original.recycle();
//        }
//
//        // Attach the loaded image to a object view;
//        ImageView objectView = new ImageView(context);
//        objectView.setImageResource(drawableId);
//        objectView.setRotation(90);
//
//        // Store to map
//        objectViews.put(gameObject, objectView);
//    }

//    @Override
//    public void updateGameObjectDeleted(GameObject gameObject) {
//        objectViews.remove(gameObject);
//    }
//
//    public View getObjectView(GameObject gameObject) {
//        return objectViews.get(gameObject);
//    }
}
