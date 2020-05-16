package com.example.people.Entity.cpn;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import retrofit2.http.POST;

@Entity
public class CpnMessage {
    @Override
    public String toString() {
        return "CpnMessage{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", icon='" + icon + '\'' +
                ", burthday='" + burthday + '\'' +
                ", email='" + email + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", addressp='" + addressp + '\'' +
                ", workin='" + workin + '\'' +
                ", showyou='" + showyou + '\'' +
                '}';
    }

    @PrimaryKey(autoGenerate = true)
    private int id;
@ColumnInfo
    private  String username;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getBurthday() {
        return burthday;
    }

    public void setBurthday(String burthday) {
        this.burthday = burthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAddressp() {
        return addressp;
    }

    public void setAddressp(String addressp) {
        this.addressp = addressp;
    }

    public String getWorkin() {
        return workin;
    }

    public void setWorkin(String workin) {
        this.workin = workin;
    }

    public String getShowyou() {
        return showyou;
    }

    public void setShowyou(String showyou) {
        this.showyou = showyou;
    }

    @ColumnInfo
    private String   nickname;
    @ColumnInfo
    private String icon;
    @ColumnInfo
    private String burthday;
    @ColumnInfo
    private String email;
    @ColumnInfo
    private String phonenumber;
    @ColumnInfo
    private String addressp;
    @ColumnInfo
    private String workin;
    @ColumnInfo
    private String showyou;

}
