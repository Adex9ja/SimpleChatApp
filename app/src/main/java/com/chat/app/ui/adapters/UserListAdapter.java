package com.chat.app.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chat.app.R;
import com.chat.app.db.entities.UserEntity;
import com.chat.app.util.ItemClickListener;
import com.chat.app.util.Util;

import java.util.List;


public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    private List<UserEntity> userEntities;
    private ItemClickListener<UserEntity> listener;

    public UserListAdapter(ItemClickListener<UserEntity> listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public UserListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListAdapter.ViewHolder holder, int position) {

        UserEntity userEntity = userEntities.get(position);

        holder.txtFullName.setText(Util.getFullName(userEntity));
        holder.txtEmail.setText(userEntity.email);
        Util.loadPix(holder.imageView, userEntity.picture);

    }

    @Override
    public int getItemCount() {
        return userEntities == null ? 0 : userEntities.size();
    }

    public void swapData(List<UserEntity> userEntities) {
        this.userEntities = userEntities;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtFullName;
        TextView txtEmail;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            txtFullName = itemView.findViewById(R.id.txtFullName);
            txtEmail = itemView.findViewById(R.id.txtEmail);
            imageView = itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            listener.onItemClicked(userEntities.get(position));

        }
    }
}
