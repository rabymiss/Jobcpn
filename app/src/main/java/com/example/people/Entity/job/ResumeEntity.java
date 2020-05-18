package com.example.people.Entity.job;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ResumeEntity {



    @PrimaryKey(autoGenerate = true)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @ColumnInfo
    private String addressWork;
    @ColumnInfo
    private String birthday;
    @ColumnInfo
    private String email;
    @ColumnInfo
    private String ifMary;
    @ColumnInfo
    private String phone;
    @ColumnInfo
    private String politics;
    @ColumnInfo
    private String qwer;
    @ColumnInfo
    private String showbyshelf;
    @ColumnInfo
    private String teached;
    @ColumnInfo
    private String workming;
    @ColumnInfo
    private String youname;
    @ColumnInfo
    private String uid;
    @ColumnInfo
    private String uuid;
    @ColumnInfo
    private String img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAddressWork() {
        return addressWork;
    }

    public void setAddressWork(String addressWork) {
        this.addressWork = addressWork;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIfMary() {
        return ifMary;
    }

    public void setIfMary(String ifMary) {
        this.ifMary = ifMary;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPolitics() {
        return politics;
    }

    public void setPolitics(String politics) {
        this.politics = politics;
    }

    public String getQwer() {
        return qwer;
    }

    public void setQwer(String qwer) {
        this.qwer = qwer;
    }

    public String getShowbyshelf() {
        return showbyshelf;
    }

    public void setShowbyshelf(String showbyshelf) {
        this.showbyshelf = showbyshelf;
    }

    public String getTeached() {
        return teached;
    }

    public void setTeached(String teached) {
        this.teached = teached;
    }

    public String getWorkming() {
        return workming;
    }

    public void setWorkming(String workming) {
        this.workming = workming;
    }

    public String getYouname() {
        return youname;
    }

    public void setYouname(String youname) {
        this.youname = youname;
    }

    @Override
    public String toString() {
        return "ResumeEntity{" +
                "id=" + id +
                ", addressWork='" + addressWork + '\'' +
                ", birthday='" + birthday + '\'' +
                ", email='" + email + '\'' +
                ", ifMary='" + ifMary + '\'' +
                ", phone='" + phone + '\'' +
                ", politics='" + politics + '\'' +
                ", qwer='" + qwer + '\'' +
                ", showbyshelf='" + showbyshelf + '\'' +
                ", teached='" + teached + '\'' +
                ", workming='" + workming + '\'' +
                ", youname='" + youname + '\'' +
                ", uid='" + uid + '\'' +
                ", uuid='" + uuid + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
