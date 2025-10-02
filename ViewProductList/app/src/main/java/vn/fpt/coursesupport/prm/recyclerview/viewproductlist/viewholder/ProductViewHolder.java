package vn.fpt.coursesupport.prm.recyclerview.viewproductlist.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vn.fpt.coursesupport.prm.recyclerview.viewproductlist.R;
import vn.fpt.coursesupport.prm.recyclerview.viewproductlist.model.Product;

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

    public void bindData(Product product) {
        txtName.setText(product.name);
        txtPrice.setText(""+product.price);
        int imageResource = itemView.getContext().getResources()
                .getIdentifier(product.imageName, "drawable", itemView.getContext().getPackageName());
        imgProduct.setImageResource(imageResource);
    }

    public void setOnClickListener(View.OnClickListener listener) {
        imgProduct.setOnClickListener(listener);
    }
}
