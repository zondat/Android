package vn.fpt.coursesupport.prm.component.recyclerview;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment implements ProductAdapter.OnProductClickListener {
    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private List<Product> products;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);

        // Setup RecyclerView
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Create sample products
        products = createSampleProducts();

        // Create adapter and set listener
        adapter = new ProductAdapter(products);
        adapter.setOnProductClickListener(this);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private List<Product> createSampleProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("iPhone 15", "ic_phone", 999.99));
        productList.add(new Product("Samsung Galaxy", "ic_phone", 899.99));
        productList.add(new Product("Google Pixel", "ic_phone", 799.99));
        productList.add(new Product("Xiaomi Mi", "ic_phone", 599.99));
        productList.add(new Product("iPhone 15", "ic_phone", 999.99));
        productList.add(new Product("Samsung Galaxy", "ic_phone", 899.99));
        productList.add(new Product("Google Pixel", "ic_phone", 799.99));
        productList.add(new Product("Xiaomi Mi", "ic_phone", 599.99));
        productList.add(new Product("iPhone 15", "ic_phone", 999.99));
        productList.add(new Product("Samsung Galaxy", "ic_phone", 899.99));
        productList.add(new Product("Google Pixel", "ic_phone", 799.99));
        productList.add(new Product("Xiaomi Mi", "ic_phone", 599.99));
        productList.add(new Product("iPhone 15", "ic_phone", 999.99));
        productList.add(new Product("Samsung Galaxy", "ic_phone", 899.99));
        productList.add(new Product("Google Pixel", "ic_phone", 799.99));
        productList.add(new Product("Xiaomi Mi", "ic_phone", 599.99));
        productList.add(new Product("iPhone 15", "ic_phone", 999.99));
        productList.add(new Product("Samsung Galaxy", "ic_phone", 899.99));
        productList.add(new Product("Google Pixel", "ic_phone", 799.99));
        productList.add(new Product("Xiaomi Mi", "ic_phone", 599.99));
        productList.add(new Product("iPhone 15", "ic_phone", 999.99));
        productList.add(new Product("Samsung Galaxy", "ic_phone", 899.99));
        productList.add(new Product("Google Pixel", "ic_phone", 799.99));
        productList.add(new Product("Xiaomi Mi", "ic_phone", 599.99));
        productList.add(new Product("iPhone 15", "ic_phone", 999.99));
        productList.add(new Product("Samsung Galaxy", "ic_phone", 899.99));
        productList.add(new Product("Google Pixel", "ic_phone", 799.99));
        productList.add(new Product("Xiaomi Mi", "ic_phone", 599.99));
        return productList;
    }

    // Handle product item click
    @Override
    public void onProductClick(int position) {
        Product product = products.get(position);
        Toast.makeText(getContext(), "Viewing: " + product.getName(), Toast.LENGTH_SHORT).show();

        // Show product details in Activity (communication with Activity)
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).showProductDetails(product);
        }
    }

    // Handle buy button click
    @Override
    public void onBuyButtonClick(int position) {
        Product product = products.get(position);
        Toast.makeText(getContext(), "Added to cart: " + product.getName(), Toast.LENGTH_SHORT).show();
        processPurchase(product);
    }

    private void processPurchase(Product product) {
        // Business logic
        Log.d("ProductFragment", "Processing purchase: " + product.getName());

        // Show confirmation in Activity
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).showPurchaseConfirmation(product);
        }
    }
}