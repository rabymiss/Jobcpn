package com.example.people.Entity.cpn;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Officesend  {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRencainame() {
        return rencainame;
    }

    public void setRencainame(String rencainame) {
        this.rencainame = rencainame;
    }

    public String getOftime() {
        return oftime;
    }

    public void setOftime(String oftime) {
        this.oftime = oftime;
    }

    public String getLianxiren() {
        return lianxiren;
    }

    public void setLianxiren(String lianxiren) {
        this.lianxiren = lianxiren;
    }

    public String getLxphone() {
        return lxphone;
    }

    public void setLxphone(String lxphone) {
        this.lxphone = lxphone;
    }

    public String getCpnname() {
        return cpnname;
    }

    public void setCpnname(String cpnname) {
        this.cpnname = cpnname;
    }

    public String getCpnid() {
        return cpnid;
    }

    public void setCpnid(String cpnid) {
        this.cpnid = cpnid;
    }

    public String getOfuuid() {
        return ofuuid;
    }

    public void setOfuuid(String ofuuid) {
        this.ofuuid = ofuuid;
    }

    public String getDozhi() {
        return dozhi;
    }

    public void setDozhi(String dozhi) {
        this.dozhi = dozhi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getT1() {
        return t1;
    }

    public void setT1(String t1) {
        this.t1 = t1;
    }

    public String getT2() {
        return t2;
    }

    public void setT2(String t2) {
        this.t2 = t2;
    }

    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }

    public String getOfcontent() {
        return ofcontent;
    }

    public void setOfcontent(String ofcontent) {
        this.ofcontent = ofcontent;
    }
@PrimaryKey
    private Integer id;

    private String rencainame;

    private String oftime;

    private String lianxiren;

    private String lxphone;

    private String cpnname;

    private String cpnid;

    private String ofuuid;

    private String dozhi;

    private String email;

    private String t1;

    private String t2;

    private String ofcontent;
    private String jobname;


    @Override
    public String toString() {
        return "Officesend{" +
                "id=" + id +
                ", rencainame='" + rencainame + '\'' +
                ", oftime='" + oftime + '\'' +
                ", lianxiren='" + lianxiren + '\'' +
                ", lxphone='" + lxphone + '\'' +
                ", cpnname='" + cpnname + '\'' +
                ", cpnid='" + cpnid + '\'' +
                ", ofuuid='" + ofuuid + '\'' +
                ", dozhi='" + dozhi + '\'' +
                ", email='" + email + '\'' +
                ", t1='" + t1 + '\'' +
                ", t2='" + t2 + '\'' +
                ", ofcontent='" + ofcontent + '\'' +
                ", jobname='" + jobname + '\'' +
                '}';
    }
}
