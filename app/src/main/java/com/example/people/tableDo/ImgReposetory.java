package com.example.people.tableDo;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


import com.example.people.Dao.UserDao;
import com.example.people.Entity.cpn.ImguEntity;
import com.example.people.JobDatabase;

import java.util.List;

public class ImgReposetory {

private LiveData<List<ImguEntity> > list;
private UserDao userDao;
    ImgReposetory(Context context) {

        JobDatabase jobDatabase = JobDatabase.getDatabase(context.getApplicationContext());
        userDao = jobDatabase.getUserDao();
        list = userDao.findAccount();
    }

    public void DeleAll(){

      new  ImgReposetory.DeleteAllAsncTask(userDao).execute();
    }
    public void InsertUser( ImguEntity...registerEntities){

        new ImgReposetory.InsertAsyncTask(userDao).execute(registerEntities);
    }
    LiveData<List<ImguEntity>> allAccout(){return  list;}
    public static class InsertAsyncTask extends AsyncTask<ImguEntity,Void,Void>{
private UserDao userDao;
        InsertAsyncTask( UserDao userDao){
            this.userDao=userDao;

        }

        @Override
        protected Void doInBackground(ImguEntity... registerEntities) {
            userDao.insert(registerEntities);
            return null;
        }
    }


public static class DeleteAllAsncTask extends AsyncTask<Void,Void,Void>{
private UserDao userDao;
    DeleteAllAsncTask(UserDao userDao){this.userDao=userDao;}
    @Override
    protected Void doInBackground(Void... voids) {
        userDao.deleteAll();
        return null;
    }
}


}
