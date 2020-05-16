package com.example.people.tableDo;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.people.Dao.JobMessageAllDao;
import com.example.people.Dao.UuidDao;
import com.example.people.Entity.Uuid;
import com.example.people.Entity.job.JobMessageAll;
import com.example.people.JobDatabase;

import java.util.List;

public class UuidRe {
    private List<Uuid>uuidList;
    private UuidDao uuidDao;


    UuidRe(Context context){

        JobDatabase jobDatabase=JobDatabase.getDatabase(context.getApplicationContext());
        uuidDao = jobDatabase.getUuidDao();
        uuidList=uuidDao.getuuid();


    }
    List<Uuid> finduuid() {
        return  uuidDao.getuuid();

    }


    public   void insertuuid(Uuid... uuids) {
        new UuidRe.InsertAsyncTask(uuidDao).execute(uuids);
    }
    public  void deleteuuid(Uuid... uuids) {
        new UuidRe.DeleteAsyncTask(uuidDao).execute(uuids);
    }
    public  void deleteuuidAll(Void... voids) {
        new UuidRe.DeleteAllAsyncTask(uuidDao).execute();
    }
//    public  void updateMsgs(JobMessageAll... jobMessageAlls) {
//        new MsgAllReposiry.UpdateAsyncTask(jobMessageAllDao).execute(jobMessageAlls);
//    }
//    public  void deleteAmsgs(Void... voids) {
//        new MsgAllReposiry.InsertAsyncTask(jobMessageAllDao).execute();
//    }



    static class  InsertAsyncTask extends AsyncTask<Uuid, Void, Void> {

        private UuidDao uuidDao;

        InsertAsyncTask(UuidDao uuidDao) {
            this.uuidDao =uuidDao ;
        }
        @Override
        protected Void doInBackground(Uuid...uuids) {
            uuidDao.insertUuid(uuids);
            return null;
        }
    }

    static class  DeleteAsyncTask extends AsyncTask<Uuid, Void, Void> {

        private UuidDao uuidDao;

        DeleteAsyncTask(UuidDao uuidDao) {
            this.uuidDao =uuidDao ;
        }
        @Override
        protected Void doInBackground(Uuid...uuids) {
            uuidDao.deleteUuid(uuids);
            return null;
        }
    }
    static class  DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {

        private UuidDao uuidDao;

        DeleteAllAsyncTask(UuidDao uuidDao) {
            this.uuidDao =uuidDao ;
        }
        @Override
        protected Void doInBackground(Void...voids) {
            uuidDao.deleteAlluuid();
            return null;
        }
    }

//
}
