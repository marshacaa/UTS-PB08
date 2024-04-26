package com.example.uts_pb.ui;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.uts_pb.R;
import com.example.uts_pb.data.model.GitHubUser;

import com.squareup.picasso.Picasso;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<GitHubUser> userList;
    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public UserAdapter(List<GitHubUser> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_github_user, parent, false);
        return new UserViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        GitHubUser user = userList.get(position);
        holder.usernameTextView.setText(user.getUsername());
        Picasso.get().load(user.getAvatarUrl()).into(holder.avatarImageView);

        holder.itemView.setOnClickListener(click -> {
            Intent intent = new Intent(click.getContext(), DetailActivity.class);
            intent.putExtra("nama", user.getName());
            intent.putExtra("username", user.getUsername());
            intent.putExtra("bio", user.getBio());
            intent.putExtra("gambar", user.getAvatarUrl());
            click.getContext().startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return userList.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {

        private ImageView avatarImageView;
        private TextView usernameTextView, nameTextView, bioTextView;

        UserViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            avatarImageView = itemView.findViewById(R.id.avatarImageView);
            usernameTextView = itemView.findViewById(R.id.usernameTextView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            bioTextView = itemView.findViewById(R.id.bioTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }

        void bind(GitHubUser user) {
            Picasso.get().load(user.getAvatarUrl()).into(avatarImageView);
            usernameTextView.setText(user.getUsername());
            nameTextView.setText(user.getName());
            bioTextView.setText(user.getBio());
        }
    }
}
