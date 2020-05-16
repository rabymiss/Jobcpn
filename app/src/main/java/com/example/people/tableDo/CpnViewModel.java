package com.example.people.tableDo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;


import com.example.people.Entity.cpn.CpnMessage;

import java.util.List;

public class CpnViewModel extends AndroidViewModel {

    private CpnRe cpnRe;


    public CpnViewModel(@NonNull Application application) {
        super(application);
        cpnRe = new CpnRe(application);

    }

    public List<CpnMessage> list(){

        return    cpnRe.allAccout();
    }
    public  void insert(CpnMessage...cpnMessages){

        cpnRe.InsertUser(cpnMessages);
    }
    public void deleteall(){
        cpnRe.DeleAll();

    }
}
