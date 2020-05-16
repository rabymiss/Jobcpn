package com.example.people.tableDo;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


import com.example.people.Dao.JobMessageAllDao;

import com.example.people.Entity.job.JobMessageAll;
import com.example.people.JobDatabase;

import java.util.List;

public class MsgAllReposiry {
    private LiveData<List<JobMessageAll>> allAmsgsLive;
    private JobMessageAllDao jobMessageAllDao;


    MsgAllReposiry(Context context){
//
       JobDatabase jobDatabase=JobDatabase.getDatabase(context.getApplicationContext());
        jobMessageAllDao = jobDatabase.getJobMessageAllDao();
  //  allAmsgsLive=jobMessageAllDao.getAllAmsgLive();
//
//
    }
  LiveData<List<JobMessageAll> >findAllAmsgsLive(String patten) {
      return  jobMessageAllDao.findcnpsWithPattern(patten);

    }
    LiveData<List<JobMessageAll> >finduuid(String patten) {
        return  jobMessageAllDao.finduuid(patten);

    }
    LiveData<List<JobMessageAll>> getAllMsgsLive() {
        return  allAmsgsLive;
    }

  public   void insertMsgs(JobMessageAll... jobMessageAlls) {
        new MsgAllReposiry.InsertAsyncTask(jobMessageAllDao).execute(jobMessageAlls);
    }
//    public  void deleteMsgs(JobMessageAll... jobMessageAlls) {
//        new MsgAllReposiry.DeleteAsyncTask(jobMessageAllDao).execute(jobMessageAlls);
//    }
//    public  void updateMsgs(JobMessageAll... jobMessageAlls) {
//        new MsgAllReposiry.UpdateAsyncTask(jobMessageAllDao).execute(jobMessageAlls);
//    }
//    public  void deleteAmsgs(Void... voids) {
//        new MsgAllReposiry.InsertAsyncTask(jobMessageAllDao).execute();
//    }



    static class  InsertAsyncTask extends AsyncTask<JobMessageAll, Void, Void> {

        private JobMessageAllDao jobMessageAllDao;

        InsertAsyncTask(JobMessageAllDao jobMessageAllDao) {
            this.jobMessageAllDao =jobMessageAllDao ;
        }
        @Override
        protected Void doInBackground(JobMessageAll... jobMessageAlls) {
            jobMessageAllDao.insertAmsg(jobMessageAlls);
            return null;
        }
    }
    //...............................................................................
//    static class  DeleteAsyncTask extends AsyncTask<JobMessageAll, Void, Void> {
//
//        private JobMessageAllDao jobMessageAllDao;
//
//        DeleteAsyncTask(JobMessageAllDao jobMessageAllDao) {
//            this.jobMessageAllDao =jobMessageAllDao ;
//        }
//        @Override
//        protected Void doInBackground(JobMessageAll... jobMessageAlls) {
//            jobMessageAllDao.deleteAmsg(jobMessageAlls);
//            return null;
//        }
//    }
//    static class UpdateAsyncTask extends AsyncTask<JobMessageAll, Void, Void> {
//
//        private JobMessageAllDao jobMessageAllDao;
//
//        UpdateAsyncTask(JobMessageAllDao jobMessageAllDao) {
//            this.jobMessageAllDao =jobMessageAllDao ;
//        }
//        @Override
//        protected Void doInBackground(JobMessageAll... jobMessageAlls) {
//            jobMessageAllDao.updateAmsg(jobMessageAlls);
//            return null;
//        }
//    }
//    static class DeleteAmsgsAsyncTask extends AsyncTask<Void, Void, Void> {
//
//        private JobMessageAllDao jobMessageAllDao;
//
//        DeleteAmsgsAsyncTask(JobMessageAllDao jobMessageAllDao) {
//            this.jobMessageAllDao =jobMessageAllDao ;
//        }
//        @Override
//        protected Void doInBackground(Void...voids) {
//            jobMessageAllDao.deleteAllAmsgs();
//            return null;
//        }
//    }
//..................................................................................................
}
