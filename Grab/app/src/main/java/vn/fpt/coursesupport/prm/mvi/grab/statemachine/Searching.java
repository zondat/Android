package vn.fpt.coursesupport.prm.mvi.grab.statemachine;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

import vn.fpt.coursesupport.prm.mvi.grab.R;

public class Searching extends AppCompatActivity {

    private static final long COUNTDOWN_DURATION = 5000;
    private static final long COUNTDOWN_INTERVAL = 1000;
    private CountDownTimer timer;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_searching);

        Intent cancel = new Intent(this, Idle.class);
        Intent driverAccepted = new Intent(this, WaitingDriver.class);

        btnCancel = findViewById(R.id.btnCancelRequest);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(cancel);
            }
        });

        timer = new CountDownTimer(COUNTDOWN_DURATION, COUNTDOWN_INTERVAL) {
            private long lastTime = COUNTDOWN_DURATION;

            @Override
            public void onFinish() {
                startActivity(cancel);
                cancel();
            }

            @Override
            public void onTick(long millisUntilFinished) {
                Log.d("MVI", "Timer countdown triggered.");
                if (millisUntilFinished < lastTime - COUNTDOWN_INTERVAL) {
                    if (new Random().nextInt(100)<50) {
                        startActivity(driverAccepted);
                        cancel();
                    }
                    lastTime = millisUntilFinished;
                }
            }
        };
        timer.start();
    }
}