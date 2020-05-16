package com.example.people;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.people.Dao.ReceiveResumeDao;
import com.example.people.Entity.job.ResumeEntity;

import java.util.List;

public class ResumeReposiry {


    private LiveData<List<ResumeEntity>> allResumesLive;
    private ReceiveResumeDao receiveResumeDao;


    ResumeReposiry(Context context) {
        JobDatabase jobDatabase = JobDatabase.getDatabase(context.getApplicationContext());
        receiveResumeDao = jobDatabase.getReceiveResumeDao();
       allResumesLive=receiveResumeDao.getAllResLive();
    }

    LiveData<List<ResumeEntity>> findResumeWithPattern(String patten) {
        return receiveResumeDao.findResumeWithPattern(patten);
    }
    LiveData<List<ResumeEntity>> getAllResLive() {
        return  allResumesLive;
    }
    void insertRess(ResumeEntity... resumeEntities) {
        new ResumeReposiry.InsertAsyncTask(receiveResumeDao).execute(resumeEntities);
    }
    static class  InsertAsyncTask extends AsyncTask<ResumeEntity, Void, Void>{

        private ReceiveResumeDao receiveResumeDao;

        InsertAsyncTask(ReceiveResumeDao receiveResumeDao) {
            this.receiveResumeDao =receiveResumeDao ;
        }
        @Override
        protected Void doInBackground(ResumeEntity... resumeEntities) {
            receiveResumeDao.insertResume(resumeEntities);
            return null;
        }
    }
}