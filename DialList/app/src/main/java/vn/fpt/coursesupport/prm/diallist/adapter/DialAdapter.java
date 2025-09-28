package vn.fpt.coursesupport.prm.diallist.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.fpt.coursesupport.prm.diallist.R;
import vn.fpt.coursesupport.prm.diallist.model.DialUser;
import vn.fpt.coursesupport.prm.diallist.view.DialViewHolder;

public class DialAdapter extends RecyclerView.Adapter<DialViewHolder> {

    private List<DialUser> dialList;
    private List<IDialListener> observerList;

    public DialAdapter(List<DialUser> dialList) {
        this.dialList = dialList;
        this.observerList = new ArrayList<>();
    }

    @NonNull
    @Override
    public DialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dial, parent, false);
        return new DialViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DialViewHolder holder, int position) {
        DialUser user = dialList.get(position);
        holder.bindData(user);
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyCall(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dialList.size();
    }

    public void addObserver(IDialListener observer) {
        if (!observerList.contains(observer)) observerList.add(observer);
    }

    public void notifyCall(int userIndex) {
        for (IDialListener observer: observerList) {
            observer.update(userIndex);
        }
    }

    public void removeObserver(IDialListener observer) {
        observerList.remove(observer);
    }
}
