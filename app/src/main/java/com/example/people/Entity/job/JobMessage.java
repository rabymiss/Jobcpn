package com.example.people.Entity.job;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class JobMessage {
    @PrimaryKey(autoGenerate = true)
   private int id;
    @ColumnInfo(name = "JobName")
    private String  jobName;
    @ColumnInfo(name = "条件1")
    private String jobConditionOne;
    @ColumnInfo(name = "条件2")
    private String jobConditionTwo;
    @ColumnInfo(name = "公司名称")
    private String cpnName;
    @ColumnInfo(name = "工资")
    private String jobPay;
    @ColumnInfo
    private  String uuid;
    @ColumnInfo
    private String username;
    @ColumnInfo
    private String image;


    public JobMessage(String jobConditionOne, String jobConditionTwo,String jobName,String jobPay,String cpnName,String uuid,String username,String image) {
    this.cpnName=cpnName;
    this.jobConditionOne=jobConditionOne;
    this.jobConditionTwo=jobConditionTwo;
    this.jobName=jobName;
    this.jobPay=jobPay;
    this.uuid=uuid;
    this.username=username;
    this.image=image;

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobConditionOne() {
        return jobConditionOne;
    }

    public void setJobConditionOne(String jobConditionOne) {
        this.jobConditionOne = jobConditionOne;
    }

    public String getJobConditionTwo() {
        return jobConditionTwo;
    }

    public void setJobConditionTwo(String jobConditionTwo) {
        this.jobConditionTwo = jobConditionTwo;
    }

    public String getCpnName() {
        return cpnName;
    }

    public void setCpnName(String cpnName) {
        this.cpnName = cpnName;
    }

    public String getJobPay() {
        return jobPay;
    }

    public void setJobPay(String jobPay) {
        this.jobPay = jobPay;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
