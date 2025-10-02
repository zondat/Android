package vn.fpt.coursesupport.prm.component.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// ProductAdapter.java
public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {
    private List<Product> productList;
    private OnProductClickListener listener;

    // Interface for event forwarding
    public interface OnProductClickListener {
        void onProductClick(int position);
        void onBuyButtonClick(int position);
    }

    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    public void setOnProductClickListener(OnProductClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.bind(product);

        // Set click listeners
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onProductClick(position);
            }
        });

        holder.buyButton.setOnClickListener(v -> {
            if (listener != null) {
                listener.onBuyButtonClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public List<Product> getProductList() {
        return productList;
    }
}