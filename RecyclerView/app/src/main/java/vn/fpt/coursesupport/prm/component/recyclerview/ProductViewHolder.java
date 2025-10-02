package vn.fpt.coursesupport.prm.component.recyclerview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class ProductViewHolder extends RecyclerView.ViewHolder {
    public TextView productName;
    public TextView productPrice;
    public ImageView productImage;
    public Button buyButton;

    public ProductViewHolder(View itemView) {
        super(itemView);
        productName = itemView.findViewById(R.id.tv_product_name);
        productPrice = itemView.findViewById(R.id.tv_product_price);
        productImage = itemView.findViewById(R.id.iv_product_image);
        buyButton = itemView.findViewById(R.id.btn_buy);
    }

    public void bind(Product product) {
        productName.setText(product.getName());
        productPrice.setText(String.format("$%.2f", product.getPrice()));

        // Set image (using image name as drawable resource identifier)
        int imageResource = itemView.getContext().getResources()
                .getIdentifier(product.getImageName(), "drawable", itemView.getContext().getPackageName());
        productImage.setImageResource(imageResource);
    }
}