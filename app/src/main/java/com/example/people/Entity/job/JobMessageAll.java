package com.example.people.Entity.job;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class JobMessageAll {
    @Override
    public String toString() {
        return "JobMessageAll{" +
                "id=" + id +
                ", jobName='" + jobName + '\'' +
                ", cpnAddress='" + cpnAddress + '\'' +
                ", good1='" + good1 + '\'' +
                ", good2='" + good2 + '\'' +
                ", good3='" + good3 + '\'' +
                ", good4='" + good4 + '\'' +
                ", jobPay='" + jobPay + '\'' +
                ", conditionOne='" + conditionOne + '\'' +
                ", conditionTwo='" + conditionTwo + '\'' +
                ", condition3='" + condition3 + '\'' +
                ", workContent='" + workContent + '\'' +
                ", workContentShow='" + workContentShow + '\'' +
                ", workAddress='" + workAddress + '\'' +
                ", cpnImage='" + cpnImage + '\'' +
                ", cpnName1='" + cpnName1 + '\'' +
                ", dizhi='" + dizhi + '\'' +
                '}';
    }

    public JobMessageAll(String jobName, String cpnAddress, String good1, String good2, String good3, String good4, String jobPay, String conditionOne, String conditionTwo, String condition3, String workContent, String workContentShow, String workAddress, String cpnImage, String cpnName1, String dizhi) {
        this.jobName = jobName;
        this.cpnAddress = cpnAddress;
        this.good1 = good1;
        this.good2 = good2;
        this.good3 = good3;
        this.good4 = good4;
        this.jobPay = jobPay;
        this.conditionOne = conditionOne;
        this.conditionTwo = conditionTwo;
        this.condition3 = condition3;
        this.workContent = workContent;
        this.workContentShow = workContentShow;
        this.workAddress = workAddress;
        this.cpnImage = cpnImage;
        this.cpnName1 = cpnName1;
        this.dizhi = dizhi;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "JobName")
    private String jobName;
    @ColumnInfo(name = "公司地址")
    private String cpnAddress;
    @ColumnInfo(name = "福利1")
    private String good1;
    @ColumnInfo(name = "福利2")
    private String good2;
    @ColumnInfo(name = "福利3")
    private String good3;
    @ColumnInfo(name = "福利4")
    private String good4;
    @ColumnInfo(name = "工资")
    private String jobPay;
    @ColumnInfo(name = "要求1")
    private String conditionOne;
    @ColumnInfo(name = "要求2")
    private String conditionTwo;
    @ColumnInfo(name = "要求3")
    private String condition3;
    @ColumnInfo(name = "岗位职责")
    private String workContent;
    @ColumnInfo(name = "岗位职责描述")
    private String workContentShow;
    @ColumnInfo(name = "工作地点")
    private String workAddress;
    @ColumnInfo(name = "公司照片")
    private String cpnImage;

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

    public String getCpnAddress() {
        return cpnAddress;
    }

    public void setCpnAddress(String cpnAddress) {
        this.cpnAddress = cpnAddress;
    }

    public String getGood1() {
        return good1;
    }

    public void setGood1(String good1) {
        this.good1 = good1;
    }

    public String getGood2() {
        return good2;
    }

    public void setGood2(String good2) {
        this.good2 = good2;
    }

    public String getGood3() {
        return good3;
    }

    public void setGood3(String good3) {
        this.good3 = good3;
    }

    public String getGood4() {
        return good4;
    }

    public void setGood4(String good4) {
        this.good4 = good4;
    }

    public String getJobPay() {
        return jobPay;
    }

    public void setJobPay(String jobPay) {
        this.jobPay = jobPay;
    }

    public String getConditionOne() {
        return conditionOne;
    }

    public void setConditionOne(String conditionOne) {
        this.conditionOne = conditionOne;
    }

    public String getConditionTwo() {
        return conditionTwo;
    }

    public void setConditionTwo(String conditionTwo) {
        this.conditionTwo = conditionTwo;
    }

    public String getCondition3() {
        return condition3;
    }

    public void setCondition3(String condition3) {
        this.condition3 = condition3;
    }

    public String getWorkContent() {
        return workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent;
    }

    public String getWorkContentShow() {
        return workContentShow;
    }

    public void setWorkContentShow(String workContentShow) {
        this.workContentShow = workContentShow;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public String getCpnImage() {
        return cpnImage;
    }

    public void setCpnImage(String cpnImage) {
        this.cpnImage = cpnImage;
    }

    public String getCpnName1() {
        return cpnName1;
    }

    public void setCpnName1(String cpnName1) {
        this.cpnName1 = cpnName1;
    }

    public String getDizhi() {
        return dizhi;
    }

    public void setDizhi(String dizhi) {
        this.dizhi = dizhi;
    }

    @ColumnInfo(name = "cpnName")
    private String cpnName1;
    @ColumnInfo(name = "公司注册地址")
    private String dizhi;


}
