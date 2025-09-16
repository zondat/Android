package vn.fpt.coursesupport.prm.project.shopeeapp;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ScrollView viewHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        viewHome = findViewById(R.id.viewHome);
        LinearLayout pnHomeProductLeft = viewHome.findViewById(R.id.panelProductLeft);
        
    }
}