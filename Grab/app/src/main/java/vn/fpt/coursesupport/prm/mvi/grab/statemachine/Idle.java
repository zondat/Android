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

public class Idle extends AppCompatActivity {

    private Button btnRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_idle);

        btnRequest = findViewById(R.id.btnRequest);

        Intent request = new Intent(this, Searching.class);
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(request);
            }
        });
    }

}