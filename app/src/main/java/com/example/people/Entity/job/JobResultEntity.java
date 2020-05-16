package com.example.people.Entity.job;

import java.util.List;

public class JobResultEntity {
    /**
     * errno : 408
     * errmsg : 成功
     * data :
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
         * jobName : 妈妈
         * jobConditionOne : 人过的
         * jobConditionTwo : 俊德
         * cpnName : 阿里
         * jobPay : 阿鲁
         */

        private String jobName;
        private String jobConditionOne;
        private String jobConditionTwo;
        private String cpnName;
        private String jobPay;
        private String image;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        private  String uuid;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        private String username;

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
    }
}
