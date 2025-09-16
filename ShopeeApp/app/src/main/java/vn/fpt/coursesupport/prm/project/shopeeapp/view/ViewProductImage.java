package vn.fpt.coursesupport.prm.project.shopeeapp.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import vn.fpt.coursesupport.prm.project.shopeeapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewProductImage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewProductImage extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PRODUCT_ID = "Id";
    private static final String ARG_PRODUCT_NAME = "productName";
    private static final String ARG_DRAWABLE_PATH = "drawableId";

    // TODO: Rename and change types of parameters
    private TextView txtViewProduct;
    private ImageView imgViewProduct;

    public ViewProductImage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ViewProductImage.
     */
    public static ViewProductImage newInstance(String id, String name, String drawablePath) {
        ViewProductImage fragment = new ViewProductImage();
        Bundle args = new Bundle();
        args.putString(ARG_PRODUCT_ID, id);
        args.putString(ARG_PRODUCT_NAME, name);
        args.putString(ARG_DRAWABLE_PATH, drawablePath);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.view_product_image, container, false);
        txtViewProduct = view.findViewById(R.id.txtProductName);
        imgViewProduct = view.findViewById(R.id.imgProduct);

        if (getArguments() != null) {
            String productName = getArguments().getString(ARG_PRODUCT_NAME);
            String imagePath = getArguments().getString(ARG_DRAWABLE_PATH);

            txtViewProduct.setText(productName);
            imgViewProduct.setImageDrawable(getDrawableFromFilePath(imagePath));
        }

        return view;
    }

    public Drawable getDrawableFromFilePath(String filePath) {
        try {
            // Create a Bitmap from the file path
            Bitmap bitmap = BitmapFactory.decodeFile(filePath);

            if (bitmap != null) {
                // Convert Bitmap to Drawable
                return new BitmapDrawable(getResources(), bitmap);
            } else {
                // Handle the case where the bitmap couldn't be loaded
                Log.e("DrawableUtils", "Failed to load bitmap from path: " + filePath);
                return null;
            }
        } catch (Exception e) {
            Log.e("DrawableUtils", "Error creating drawable from file path: " + e.getMessage());
            return null;
        }
    }


}