package vn.fpt.coursesupport.prm.recyclerview.viewproductlist;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductViewHolder extends RecyclerView.ViewHolder {

    private TextView txtPrice;
    private TextView txtName;
    private ImageView imgProduct;

    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);

        txtName = itemView.findViewById(R.id.txtProductName);
        txtPrice = itemView.findViewById(R.id.txtPrice);
        imgProduct = itemView.findViewById(R.id.imgProduct);
    }
}
