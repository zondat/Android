package vn.fpt.coursesupport.prm.project.shopeeapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;

import vn.fpt.coursesupport.prm.project.shopeeapp.view.ViewProductImage;

public class MainActivity extends AppCompatActivity {

    private ImageButton btnHome, btnMall, btnLive, btnAccount;
    private ScrollView viewHome;
    private ConstraintLayout viewMall, viewLive, viewAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        viewHome = findViewById(R.id.viewHome);
        viewMall = findViewById(R.id.viewMall);
        viewLive = findViewById(R.id.viewLive);
        viewAccount = findViewById(R.id.viewAccount);

        btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHome.bringToFront();
            }
        });

        btnMall = findViewById(R.id.btnMall);
        btnMall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewMall.bringToFront();
            }
        });

        btnLive = findViewById(R.id.btnLive);
        btnLive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewLive.bringToFront();
            }
        });

        btnAccount = findViewById(R.id.btnAccount);
        btnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewAccount.bringToFront();
            }
        });

        viewHome.bringToFront();

        // View home's contents
        LinearLayout panelProductLeft = viewHome.findViewById(R.id.panelProductLeft);
        ViewProductImage productChocopie = new ViewProductImage();
        Bundle args = new Bundle();
        args.putString("productName", "Chocopie");
        args.putString("drawableId", "product_chocopie");
        productChocopie.setArguments(args);

        // Begin fragment transaction
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(panelProductLeft.getId(), productChocopie);
        transaction.addToBackStack("fragmentProductChocopie");
        transaction.commit();

        ViewProductImage productChipChip = new ViewProductImage();
        args = new Bundle();
        args.putString("productName", "Keo Chip Chip");
        args.putString("drawableId", "product_chip_chip");
        productChipChip.setArguments(args);

        // Begin fragment transaction
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(panelProductLeft.getId(), productChipChip);
        transaction.addToBackStack("fragmentProductChipChip");
        transaction.commit();

    }
}