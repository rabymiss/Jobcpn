package com.example.people.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
@Entity
public class LoginUser {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "account")
    private String userId;
    @ColumnInfo(name = "password")
    private String userPassWord;

    public LoginUser(String userId, String userPassWord) {
        this.userId = userId;
        this.userPassWord = userPassWord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassWord() {
        return userPassWord;
    }

    public void setUserPassWord(String userPassWord) {
        this.userPassWord = userPassWord;
    }
}
