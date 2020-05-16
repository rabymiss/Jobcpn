package com.example.people.Entity.job;

import java.util.List;

public class ReturnJobMessageAll {


    /**
     * errno : 200
     * errmsg : 成功
     * data : [{"id":17,"jobName":"???","cpnAddress":"???","good1":"?????","good2":"???","good3":" ???","good4":"???","jobPay":100,"conditionOne":"????","conditionTwo":"???? as??? ","condition3":"?????","workContent":"Asdasd","workContentShow":"???","workAddress":"???","cpnImage":"???","cpnName":"???","dizhi":" ???"},{"id":18,"jobName":"???","cpnAddress":"???","good1":"?????","good2":"???","good3":" ???","good4":"???","jobPay":100,"conditionOne":"????","conditionTwo":"???? as??? ","condition3":"?????","workContent":"Asdasd","workContentShow":"???","workAddress":"???","cpnImage":"???","cpnName":"???","dizhi":" ???"},{"id":19,"jobName":"???","cpnAddress":"???","good1":"?????","good2":"???","good3":" ???","good4":"???","jobPay":100,"conditionOne":"????","conditionTwo":"???? as??? ","condition3":"?????","workContent":"Asdasd","workContentShow":"???","workAddress":"???","cpnImage":"???","cpnName":"???","dizhi":" ???"}]
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
         * id : 17
         * jobName : ???
         * cpnAddress : ???
         * good1 : ?????
         * good2 : ???
         * good3 :  ???
         * good4 : ???
         * jobPay : 100
         * conditionOne : ????
         * conditionTwo : ???? as???
         * condition3 : ?????
         * workContent : Asdasd
         * workContentShow : ???
         * workAddress : ???
         * cpnImage : ???
         * cpnName : ???
         * dizhi :  ???
         */

        private int id;
        private String jobName;
        private String cpnAddress;
        private String good1;
        private String good2;
        private String good3;
        private String good4;
        private String jobPay;
        private String conditionOne;
        private String conditionTwo;
        private String condition3;
        private String workContent;
        private String workContentShow;
        private String workAddress;
        private String cpnImage;
        private String cpnName1;
        private String dizhi;

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
    }
}
