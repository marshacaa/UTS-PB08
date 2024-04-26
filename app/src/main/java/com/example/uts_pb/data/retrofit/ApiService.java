package com.example.uts_pb.data.retrofit;

import com.example.uts_pb.data.model.GitHubUser;
import com.example.uts_pb.data.response.GitHubSearchResponse;
import com.example.uts_pb.ui.GithubUserAdapter;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("search/users")
    Call<GitHubSearchResponse> searchUsers(@Query("q") String query);

    @GET("users/{username}")
    Call<GitHubUser> getUser(@Path("username") String username);

}
