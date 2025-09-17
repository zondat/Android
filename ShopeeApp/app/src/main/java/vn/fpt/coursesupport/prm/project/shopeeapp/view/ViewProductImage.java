package vn.fpt.coursesupport.prm.project.shopeeapp.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
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
    private static final String ARG_PRODUCT_NAME = "productName";
    private static final String ARG_DRAWABLE_ID = "drawableId";

    // TODO: Rename and change types of parameters
    private TextView txtViewProduct;
    private ImageView imgViewProduct;

    public ViewProductImage() {
        // Required empty public constructor
    }

    public static ViewProductImage newInstance(String id, String name, String drawableId) {
        ViewProductImage fragment = new ViewProductImage();
        Bundle args = new Bundle();
        args.putString(ARG_PRODUCT_NAME, name);
        args.putString(ARG_DRAWABLE_ID, drawableId);
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
            String drawableId = getArguments().getString(ARG_DRAWABLE_ID);

            txtViewProduct.setText(productName);
            imgViewProduct.setImageDrawable(loadDrawableByName(getContext(), drawableId));
        }

        return view;
    }

    public static Drawable loadDrawableByName(Context context, String name) {
        Resources resources = context.getResources();
        int drawableId = resources.getIdentifier(name, "drawable", context.getPackageName());

        if (drawableId != 0) {
            return ResourcesCompat.getDrawable(resources, drawableId, null);
        } else {
            // Handle case where drawable is not found
            return null;
        }
    }


}