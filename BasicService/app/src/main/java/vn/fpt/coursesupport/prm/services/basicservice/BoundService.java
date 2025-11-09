package vn.fpt.coursesupport.prm.services.basicservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class BoundService extends Service {
    private final IBinder binder = new LocalBinder();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(() -> {
            Log.d("Bound Service", "Background task");
            stopSelf();
        }).start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d("Bound Service", "Bound service is destroyed...");
    }

    public class LocalBinder extends Binder {
        BoundService getService() {
            return BoundService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

}
