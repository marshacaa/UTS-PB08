package com.example.uts_pb.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uts_pb.R;
import com.example.uts_pb.data.api.ApiClient;
import com.example.uts_pb.data.retrofit.ApiService;
import com.example.uts_pb.data.model.GitHubUser;
import com.example.uts_pb.data.response.GitHubSearchResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements UserAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private UserAdapter adapter;
    private List<GitHubUser> userList = new ArrayList<>();
    private ApiService apiService;
    private EditText searchEditText;
    private Button searchButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new UserAdapter(userList);
        adapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Token
        String authToken = "1223";

        // Inisialisasi ApiService dengan token akses pribadi
        apiService = ApiClient.getApiService();
        fetchGitHubUsers();

        // Inisialisasi EditText dan Button pencarian
        searchEditText = findViewById(R.id.searchEditText);
        searchButton = findViewById(R.id.searchButton);

        // Set OnClickListener untuk tombol pencarian
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchEditText.getText().toString();
                if (!query.isEmpty()) {
                    performSearch(query);
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a search query", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void fetchGitHubUsers() {
        Call<GitHubSearchResponse> call = apiService.searchUsers("marshaca");

        call.enqueue(new Callback<GitHubSearchResponse>() {
            @Override
            public void onResponse(@NonNull Call<GitHubSearchResponse> call, @NonNull Response<GitHubSearchResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    userList.clear();
                    userList.addAll(response.body().getItems());
                    adapter.notifyDataSetChanged();

                } else {
                    Toast.makeText(MainActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<GitHubSearchResponse> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void performSearch(String query) {
        Call<GitHubSearchResponse> call = apiService.searchUsers(query);

        call.enqueue(new Callback<GitHubSearchResponse>() {
            @Override
            public void onResponse(@NonNull Call<GitHubSearchResponse> call, @NonNull Response<GitHubSearchResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    userList.clear();
                    userList.addAll(response.body().getItems());
                    adapter.notifyDataSetChanged();

                } else {
                    Toast.makeText(MainActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<GitHubSearchResponse> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        GitHubUser clickedUser = userList.get(position);
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("user", clickedUser);
        startActivity(intent);
    }
}
