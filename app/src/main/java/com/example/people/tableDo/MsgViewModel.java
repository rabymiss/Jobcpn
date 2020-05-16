package com.example.people.tableDo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.people.Dao.JobMessageAllDao;
import com.example.people.Entity.job.JobMessageAll;

import java.util.List;

public class MsgViewModel  extends AndroidViewModel {



    private JobMessageAllDao jobMessageAllDao;
    private MsgAllReposiry msgAllReposiry;

    public MsgViewModel(@NonNull Application application) {
        super(application);
        msgAllReposiry =new MsgAllReposiry(application);
 ;
    }

   public  LiveData<List<JobMessageAll> > findMsgsWithPattern(String patten) {
        return msgAllReposiry.findAllAmsgsLive(patten);
    }
    public  LiveData<List<JobMessageAll> > finduuid(String patten) {
        return msgAllReposiry.finduuid(patten);
    }
//    public LiveData<List<JobMessageAll>> findAmsgsLive(String pattern) { return msgAllReposiry.findAllAmsgsLive(pattern);}

   // LiveData<List<JobMessageAll>>getAllMsgsLive(){ return msgAllReposiry.getAllMsgsLive();};

    public void insertMsgs(JobMessageAll...jobMessageAlls) { msgAllReposiry.insertMsgs(jobMessageAlls); }


//    public void deleteMsgs(JobMessageAll...jobMessageAlls) { msgAllReposiry.deleteMsgs(jobMessageAlls); }
//    public void updateMsgs(JobMessageAll...jobMessageAlls) { msgAllReposiry.updateMsgs(jobMessageAlls); }
//    public void deletaAmsgs(Void...voids) { msgAllReposiry.deleteAmsgs(); }

}
