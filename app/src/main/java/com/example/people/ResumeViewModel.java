package com.example.people;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.people.Dao.ReceiveResumeDao;
import com.example.people.Entity.job.ResumeEntity;


import java.util.List;

public class ResumeViewModel extends AndroidViewModel {

    private ReceiveResumeDao receiveResumeDao;
    private ResumeReposiry resumeReposiry;

    public ResumeViewModel(@NonNull Application application) {
        super(application);
        resumeReposiry =new ResumeReposiry(application);
    }

    public LiveData<List<ResumeEntity>> findMsgsWithPattern(String patten) {
        return resumeReposiry.findResumeWithPattern(patten);
    }
   public LiveData<List<ResumeEntity>> getAllResLive() { return resumeReposiry.getAllResLive();}
    public   void insertRess(ResumeEntity... resumeEntities) {
        resumeReposiry.insertRess(resumeEntities);
    }




}
