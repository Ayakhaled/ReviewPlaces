package com.example.aya.pleces_rev;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by aya on 22/05/17.
 */

public class User implements Serializable {
    @SerializedName("name")
    private String user_name;
    @SerializedName("email")
    private String user_email;
    @SerializedName("password")
    private String password;

    public User(String user_name, String user_email, String password) {
        this.user_name = user_name;
        this.user_email = user_email;
        this.password = password;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }
}
