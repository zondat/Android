package vn.fpt.coursesupport.prm.multithreading.runner;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.MutableLiveData;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private ScheduledExecutorService executor;
    private ScheduledFuture<?> thread;
    private Button btnClick;
    private TextView txtCount, txtResult;
    private ProgressBar barTime;
    private boolean isFinished = false;
    private int progress = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnClick = findViewById(R.id.btnClick);
        txtCount = findViewById(R.id.txtCount);
        txtResult = findViewById(R.id.txtResult);
        barTime = findViewById(R.id.barTime);

        txtCount.setText("0");

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFinished) return;
                int count = Integer.parseInt(String.valueOf(txtCount.getText())) + 1;
                txtCount.setText(""+count);
            }
        });

        executor = Executors.newScheduledThreadPool(1);
        thread = executor.scheduleWithFixedDelay(() -> {
                    runOnUiThread(() -> {
                        barTime.setProgress(progress--);
                        if (progress == 0) {
                            int count = Integer.parseInt(String.valueOf(txtCount.getText()));
                            if (count<20) txtResult.setText("You lose...");
                            else txtResult.setText("You win!!!");

                            // Unable update count
                            isFinished = true;

                            // Close the thread
                            this.thread.cancel(true);
                            this.executor.shutdown();
                        }
                    });
                }, 0, 100, TimeUnit.MILLISECONDS
        );

    }
}