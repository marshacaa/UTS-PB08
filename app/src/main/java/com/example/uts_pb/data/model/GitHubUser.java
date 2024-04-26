package com.example.uts_pb.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class GitHubUser implements Parcelable {

    @SerializedName("login")
    private String username;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("name")
    private String name;

    @SerializedName("bio")
    private String bio;

    public String getUsername() {
        return username;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    protected GitHubUser(Parcel in) {
        username = in.readString();
        avatarUrl = in.readString();
        name = in.readString();
        bio = in.readString();
    }

    public static final Creator<GitHubUser> CREATOR = new Creator<GitHubUser>() {
        @Override
        public GitHubUser createFromParcel(Parcel in) {
            return new GitHubUser(in);
        }

        @Override
        public GitHubUser[] newArray(int size) {
            return new GitHubUser[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(avatarUrl);
        dest.writeString(name);
        dest.writeString(bio);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
