package com.example.people.Api.entity;

import java.util.List;

public class Resume {


    /**
     * errno : 200
     * errmsg : 成功
     * data : [{"id":14,"youname":"陈管副","birthday":"1996","politics":"团员","email":"2d d","phone":"273 ","addresswork":"阿斯大的","ifmary":"啊十大上的","qwer":"啊十大上的人","teached":"阿斯打算打算打算的","workming":"啊十大上的","showbyshelf":"啊","uuid":"e34f943f-ad02-4f2a-905a-752bb5ff6b9c小米","cpnid":"17781140502","t1":"17781140011"},{"id":15,"youname":"hsjdhasd ","birthday":"asdasd ","politics":"q we防守打法","email":"阿达说的","phone":null,"addresswork":null,"ifmary":"asd ","qwer":"asd ","teached":"asd ","workming":null,"showbyshelf":null,"uuid":null,"cpnid":"17781140502","t1":null}]
     */

    private int errno;
    private String errmsg;
    private List<DataBean> data;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 14
         * youname : 陈管副
         * birthday : 1996
         * politics : 团员
         * email : 2d d
         * phone : 273
         * addresswork : 阿斯大的
         * ifmary : 啊十大上的
         * qwer : 啊十大上的人
         * teached : 阿斯打算打算打算的
         * workming : 啊十大上的
         * showbyshelf : 啊
         * uuid : e34f943f-ad02-4f2a-905a-752bb5ff6b9c小米
         * cpnid : 17781140502
         * t1 : 17781140011
         */

        private int id;
        private String youname;
        private String birthday;
        private String politics;
        private String email;
        private String phone;
        private String addresswork;
        private String ifmary;
        private String qwer;
        private String teached;
        private String workming;
        private String showbyshelf;
        private String uuid;
        private String cpnid;
        private String t1;
        private String img;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getYouname() {
            return youname;
        }

        public void setYouname(String youname) {
            this.youname = youname;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getPolitics() {
            return politics;
        }

        public void setPolitics(String politics) {
            this.politics = politics;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddresswork() {
            return addresswork;
        }

        public void setAddresswork(String addresswork) {
            this.addresswork = addresswork;
        }

        public String getIfmary() {
            return ifmary;
        }

        public void setIfmary(String ifmary) {
            this.ifmary = ifmary;
        }

        public String getQwer() {
            return qwer;
        }

        public void setQwer(String qwer) {
            this.qwer = qwer;
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

        public String getShowbyshelf() {
            return showbyshelf;
        }

        public void setShowbyshelf(String showbyshelf) {
            this.showbyshelf = showbyshelf;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getCpnid() {
            return cpnid;
        }

        public void setCpnid(String cpnid) {
            this.cpnid = cpnid;
        }

        public String getT1() {
            return t1;
        }

        public void setT1(String t1) {
            this.t1 = t1;
        }
    }
}
