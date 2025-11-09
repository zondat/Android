package vn.fpt.coursesupport.prm.services.basicservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class StartedService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(() -> {
            Log.d("Started Service", "Background task");
            stopSelf();
        }).start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d("Started Service", "Basic service is destroyed...");
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
