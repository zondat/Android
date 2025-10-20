package vn.fpt.coursesupport.prm.mvi.grab.statemachine;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

import vn.fpt.coursesupport.prm.mvi.grab.R;

public class Transporting extends AppCompatActivity {
    private static final long COUNTDOWN_DURATION = 3000;
    private static final long COUNTDOWN_INTERVAL = 1000;
    private CountDownTimer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_transporting);

        Intent arrivedDestination = new Intent(this, Finished.class);

        timer = new CountDownTimer(COUNTDOWN_DURATION, COUNTDOWN_INTERVAL) {
            private long lastTime = COUNTDOWN_DURATION;

            @Override
            public void onFinish() {
                startActivity(arrivedDestination);
                cancel();
            }

            @Override
            public void onTick(long millisUntilFinished) {

            }
        };
        timer.start();
    }
}