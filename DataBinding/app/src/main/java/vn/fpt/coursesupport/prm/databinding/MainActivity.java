package vn.fpt.coursesupport.prm.databinding;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;

import vn.fpt.coursesupport.prm.databinding.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        product = new Product("Chocopie", 12.01f, R.drawable.product_chocopie);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setProduct(product);
        binding.setLifecycleOwner(this);
    }
}