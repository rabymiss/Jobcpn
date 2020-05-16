package com.example.people;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.people.Dao.JobDao;
import com.example.people.Entity.job.JobMessage;

import java.util.List;

public class JobRepository {
    private LiveData<List<JobMessage>> allJobsLive;
    private JobDao jobDao;

    JobRepository(Context context){

        JobDatabase jobDatabase = JobDatabase.getDatabase(context.getApplicationContext());
        jobDao = jobDatabase.getJobDao();
        allJobsLive=jobDao.getAllJobsLive();
    }
    LiveData<List<JobMessage>> findAllJobsLive(String patten) {
        return  jobDao.findJobsWithPattern("%"+patten+"%");
    }
    LiveData<List<JobMessage>> findcpn(String patten) {
        return  jobDao.findcpn("%"+patten+"%");
    }

    void insertJobs(JobMessage... jobMessages) {
        new InsertAsyncTask(jobDao).execute(jobMessages);
    }

    void deleteJobs(JobMessage... jobMessages) { new DeleteAsyncTask(jobDao).execute(jobMessages); }
    LiveData<List<JobMessage>> getAllJobsLive() {
        return  allJobsLive;
    }
    void updateJobs(JobMessage... jobMessages) {
        new UpdateAsyncTask(jobDao).execute(jobMessages);
    }

    void deleteAllJobs() { new DeleteAllJobsAsyncTask(jobDao).execute();
    }
   static class  InsertAsyncTask extends AsyncTask<JobMessage, Void, Void>{

        private JobDao jobDao;

       InsertAsyncTask(JobDao jobDao) {
           this.jobDao =jobDao ;
       }
       @Override
       protected Void doInBackground(JobMessage... jobMessages) {
           jobDao.insertJob(jobMessages);
           return null;
       }
   }
    static class  DeleteAsyncTask extends AsyncTask<JobMessage, Void, Void>{

        private JobDao jobDao;

        DeleteAsyncTask(JobDao jobDao) {
            this.jobDao =jobDao ;
        }
        @Override
        protected Void doInBackground(JobMessage... jobMessages) {
            jobDao.deleteJob(jobMessages);
            return null;
        }
    }
    static class  UpdateAsyncTask extends AsyncTask<JobMessage, Void, Void>{

        private JobDao jobDao;

        UpdateAsyncTask(JobDao jobDao) {
            this.jobDao =jobDao ;
        }
        @Override
        protected Void doInBackground(JobMessage... jobMessages) {
            jobDao.updateJob(jobMessages);
            return null;
        }
    }
    static class  DeleteAllJobsAsyncTask extends AsyncTask<Void, Void, Void>{

        private JobDao jobDao;

        DeleteAllJobsAsyncTask(JobDao jobDao) {
            this.jobDao =jobDao ;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            jobDao.deleteAllJobs();
            return null;
        }
    }

}
