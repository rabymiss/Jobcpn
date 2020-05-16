package com.example.people.Entity.job;

public class ReturnResume {


    /**
     * data : {"addressWork":"addressWork1","birthday":"birth1","email":"email1","ifMary":"ifMary1","phone":"phone1","politics":"politics1","qwer":"qwer1","showbyshelf":"shouw1","teached":"teached","workming":"workong1"}
     * errmsg : 成功
     * errno : 200
     */

    private DataBean data;
    private String errmsg;
    private int errno;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public static class DataBean {
        public String getYouname() {
            return youname;
        }

        public void setYouname(String youname) {
            this.youname = youname;
        }

        /**
         * addressWork : addressWork1
         * birthday : birth1
         * email : email1
         * ifMary : ifMary1
         * phone : phone1
         * politics : politics1
         * qwer : qwer1
         * showbyshelf : shouw1
         * teached : teached
         * workming : workong1
         */

        private String addressWork;
        private String birthday;
        private String email;
        private String ifMary;
        private String phone;
        private String politics;
        private String qwer;
        private String showbyshelf;
        private String teached;
        private String workming;
       private String youname;
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
    }
}
