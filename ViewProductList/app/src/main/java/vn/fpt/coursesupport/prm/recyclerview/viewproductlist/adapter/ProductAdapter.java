package vn.fpt.coursesupport.prm.recyclerview.viewproductlist.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.fpt.coursesupport.prm.recyclerview.viewproductlist.R;
import vn.fpt.coursesupport.prm.recyclerview.viewproductlist.model.Product;
import vn.fpt.coursesupport.prm.recyclerview.viewproductlist.viewholder.ProductViewHolder;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    private List<Product> data;
    private List<IAdapterListener> listeners;

    public ProductAdapter(List<Product> data) {
        this.data = data;
        this.listeners = new ArrayList<>();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_view, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = data.get(position);
        holder.bindData(product);
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addListener(IAdapterListener listener) {
        if (!listeners.contains(listener)) listeners.add(listener);
    }

    public void removeListener(IAdapterListener l) {
        listeners.remove(l);
    }

    public void notifyClick(int position) {
        for (IAdapterListener l : listeners) {
            l.update(position);
        }
    }
}
