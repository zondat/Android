package vn.fpt.coursesupport.prm.recyclerview.viewproductlist;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.fpt.coursesupport.prm.recyclerview.viewproductlist.adapter.IAdapterListener;
import vn.fpt.coursesupport.prm.recyclerview.viewproductlist.adapter.ProductAdapter;
import vn.fpt.coursesupport.prm.recyclerview.viewproductlist.model.Product;

public class MainActivity extends AppCompatActivity implements IAdapterListener {

    private RecyclerView recviewProductList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        List<Product> data = new ArrayList<>();
        data.add(new Product("HP", 800.5f, "product_hp"));
        data.add(new Product("DELL", 700.5f, "product_dell"));
        data.add(new Product("Lenovo", 1000.5f, "product_lenovo"));
        data.add(new Product("Macbook", 1500.5f, "product_macbook"));

        recviewProductList = findViewById(R.id.recviewProductList);
        recviewProductList.setLayoutManager(new LinearLayoutManager(this));
        ProductAdapter adapter = new ProductAdapter(data);
        adapter.addListener(this);
        recviewProductList.setAdapter(adapter);

    }

    @Override
    public void update(int position) {
        Log.d("Shopee App", "Clicked on data " + position);
    }
}