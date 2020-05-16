package com.example.people.tableDo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.people.Entity.cpn.ImguEntity;

import java.util.List;

public class ImgViewmodel extends AndroidViewModel {

    private ImgReposetory imgReposetory;


    public ImgViewmodel(@NonNull Application application) {
        super(application);
        imgReposetory = new ImgReposetory(application);

    }

    public LiveData<List<ImguEntity>> list(){

     return    imgReposetory.allAccout();
    }
    public  void insert(ImguEntity...registerEntities){

        imgReposetory.InsertUser(registerEntities);
    }
    public void deleteall(){
        imgReposetory.DeleAll();

    }
}
