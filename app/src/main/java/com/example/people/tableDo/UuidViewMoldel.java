package com.example.people.tableDo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.people.Dao.JobMessageAllDao;
import com.example.people.Dao.UuidDao;
import com.example.people.Entity.Uuid;
import com.example.people.Entity.job.JobMessageAll;

import java.util.List;

public class UuidViewMoldel  extends AndroidViewModel {

    private UuidDao uuidDao;
    private UuidRe uuidRe;

    public UuidViewMoldel(@NonNull Application application) {
        super(application);
        uuidRe =new UuidRe(application);

    }

  public   List<Uuid> findUuid() {
        return uuidRe.finduuid();
    }
    public void insertUuid(Uuid...uuids) { uuidRe.insertuuid(uuids); }
    public void deleteuuid(Void...voids) { uuidRe.deleteuuidAll(); }
//    public void updateMsgs(JobMessageAll...jobMessageAlls) { msgAllReposiry.updateMsgs(jobMessageAlls); }
//    public void deletaAmsgs(Void...voids) { msgAllReposiry.deleteAmsgs(); }

}
