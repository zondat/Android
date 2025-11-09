package vn.fpt.coursesupport.prm.services.basicservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private BoundService mService;
    private boolean mBound = false;
    private TextView txtStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        txtStatus = findViewById(R.id.txtStatus);

        Intent intent1 = new Intent(this, StartedService.class);
        startService(intent1);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Bind to LocalService.
        Intent intent2 = new Intent(this, BoundService.class);
        bindService(intent2, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection);
        mBound = false;
    }

    /** Defines callbacks for service binding, passed to bindService(). */
    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance.
            BoundService.LocalBinder binder = (BoundService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
            txtStatus.setText("Connected to bound service");
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };
}