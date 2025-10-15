package vn.fpt.coursesupport.prm.diallist.view;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vn.fpt.coursesupport.prm.diallist.R;
import vn.fpt.coursesupport.prm.diallist.model.DialUser;

public class DialViewHolder extends RecyclerView.ViewHolder {

    private TextView txtUser;
    private ImageButton btnUser;

    public DialViewHolder(@NonNull View itemView) {
        super(itemView);
        txtUser = itemView.findViewById(R.id.txtUser);
        btnUser = itemView.findViewById(R.id.btnUser);
    }

    public void bindData(DialUser user) {
        txtUser.setText(user.getName());
        int imageResource = itemView.getContext().getResources()
                .getIdentifier(user.getImageName(), "drawable", itemView.getContext().getPackageName());
        btnUser.setImageResource(imageResource);
    }

    public void setOnClickListener(View.OnClickListener listener) {
        btnUser.setOnClickListener(listener);
    }
}
