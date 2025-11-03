package vn.fpt.coursesupport.prm.multithreading.spaceship.handler;

import android.util.Log;
import android.view.KeyEvent;
import android.widget.Space;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import vn.fpt.coursesupport.prm.multithreading.spaceship.entity.Spaceship;

public class EventHandler extends Thread {
    private Spaceship spaceship;
    private BlockingQueue<Integer> eventQueue;

    public EventHandler(Spaceship character) {
        eventQueue = new LinkedBlockingQueue<>();
        spaceship = character;
    }

    public void addEventKey(int event) {
        if (handlableEvents.contains(event)) {
            eventQueue.offer(event);
        }
    }

    private static List<Integer> handlableEvents
                                    = Arrays.asList(KeyEvent.KEYCODE_DPAD_UP,
                                                    KeyEvent.KEYCODE_DPAD_DOWN,
                                                    KeyEvent.KEYCODE_DPAD_LEFT,
                                                    KeyEvent.KEYCODE_DPAD_RIGHT,
                                                    KeyEvent.KEYCODE_SPACE);

    @Override
    public void run() {
        try {
            while (true) {
                Integer event = eventQueue.take();
                if (event == KeyEvent.KEYCODE_DPAD_UP) spaceship.moveUp();
                else if (event == KeyEvent.KEYCODE_DPAD_DOWN) spaceship.moveDown();
                else if (event == KeyEvent.KEYCODE_DPAD_LEFT) spaceship.moveLeft();
                else if (event == KeyEvent.KEYCODE_DPAD_RIGHT) spaceship.moveRight();
                else if (event == KeyEvent.KEYCODE_SPACE) spaceship.shoot();
                else {
                    Log.d("Game Spaceship", "Unhandled event");
                }
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
