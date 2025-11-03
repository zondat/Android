package vn.fpt.coursesupport.prm.multithreading.spaceship.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameTick {
    private ScheduledExecutorService scheduler;
    private long currentTime;
    private int interval = 50;
    private List<ITimeListener> listeners;

    public static GameTick INSTANCE = new GameTick();

    private GameTick(){
        currentTime = 0;
        scheduler = Executors.newScheduledThreadPool(1);
        listeners = new ArrayList<>();
    }

    public void setInterval(int newInterval) {
        this.interval = newInterval;
    }

    public ScheduledExecutorService getScheduler() {
        return scheduler;
    }

    public void start() {
        scheduler.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                currentTime ++;
                notifyGameTick();
            }
        }, 0, 16, TimeUnit.MILLISECONDS); // ~60 FPS
    }

    public void addTimeListener(ITimeListener l) {
        listeners.add(l);
    }

    public void removeTimeListener(ITimeListener l) {
        listeners.remove(l);
    }

    public void notifyGameTick() {
        for (ITimeListener listener: listeners) {
            listener.updateGameTick(currentTime);
        }
    }
}
