package com.example.uts_pb.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.uts_pb.R;
import com.example.uts_pb.data.api.ApiClient;
import com.example.uts_pb.data.model.GitHubUser;

import com.example.uts_pb.data.retrofit.ApiService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private ImageView avatarImageView;
    private TextView usernameTextView, nameTextView, bioTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        // Ambil data pengguna dari Intent
        Bundle extras = getIntent().getExtras();

        // Tampilkan data pengguna di views
        if (extras != null) {
            String username = extras.getString("username");
            ApiService apiService = ApiClient.getApiService();
            Call<GitHubUser> userCall = apiService.getUser(username);

            avatarImageView = findViewById(R.id.avatarImageView);
            usernameTextView = findViewById(R.id.usernameTextView);
            nameTextView = findViewById(R.id.nameTextView);
            bioTextView = findViewById(R.id.bioTextView);

            userCall.enqueue(new Callback<GitHubUser>() {
                @Override
                public void onResponse(Call<GitHubUser> call, Response<GitHubUser> response) {
                    if (response.isSuccessful()){
                        GitHubUser user = response.body();
                        if (user != null){
                            String name = "Name: " + user.getName();
                            String usernames = "Username: " + user.getUsername();
                            String bio = "Bio: " + user.getBio();
                            String gambar = user.getAvatarUrl();

                            nameTextView.setText(name);
                            usernameTextView.setText(usernames);
                            bioTextView.setText(bio);
                            Picasso.get().load(gambar).into(avatarImageView);
                        }else {
                            Toast.makeText(DetailActivity.this, "Failed to get user data", Toast.LENGTH_SHORT).show();
                        }
                }
                }

                @Override
                public void onFailure(Call<GitHubUser> call, Throwable t) {
                    Toast.makeText(DetailActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
