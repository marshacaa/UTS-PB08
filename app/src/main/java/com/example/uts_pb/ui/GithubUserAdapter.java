package com.example.uts_pb.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.uts_pb.data.model.GitHubUser;
import com.example.uts_pb.R;

import java.util.List;

public class GithubUserAdapter extends RecyclerView.Adapter<GithubUserAdapter.UserViewHolder> {

    private List<GitHubUser> userList;
    private OnItemClickListener listener;

    public GithubUserAdapter(List<GitHubUser> userList, OnItemClickListener listener) {
        this.userList = userList;
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_github_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        GitHubUser currentUser = userList.get(position);
        holder.usernameTextView.setText(currentUser.getUsername());
        holder.nameTextView.setText(currentUser.getName());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView usernameTextView;
        public TextView nameTextView;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameTextView = itemView.findViewById(R.id.usernameTextView);
            nameTextView = itemView.findViewById(R.id.nameTextView);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && listener != null) {
                    listener.onItemClick(position);
                }
            });
        }
    }
}
