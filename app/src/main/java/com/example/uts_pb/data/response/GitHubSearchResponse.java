package com.example.uts_pb.data.response;

import com.example.uts_pb.data.model.GitHubUser;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class GitHubSearchResponse {

    @SerializedName("items")
    private List<GitHubUser> items;

    public List<GitHubUser> getItems() {
        return items;
    }
}
