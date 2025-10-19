package vn.fpt.coursesupport.prm.mvi.grab.statemachine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import vn.fpt.coursesupport.prm.mvi.grab.R;

public class Finished extends AppCompatActivity {

    private Button btnFinished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_finished);

        Intent finished = new Intent(this, Idle.class);

        btnFinished = findViewById(R.id.btnFinish);
        btnFinished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(finished);
            }
        });
    }
}