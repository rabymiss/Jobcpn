package com.example.people;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.people.Dao.EditmsgDao;
import com.example.people.Dao.JobDao;
import com.example.people.Dao.JobMessageAllDao;
import com.example.people.Dao.LoginDao;
import com.example.people.Dao.ReceiveResumeDao;
import com.example.people.Dao.UserDao;
import com.example.people.Dao.UuidDao;
import com.example.people.Entity.Uuid;
import com.example.people.Entity.cpn.CpnMessage;
import com.example.people.Entity.cpn.ImguEntity;
import com.example.people.Entity.job.JobMessage;
import com.example.people.Entity.job.JobMessageAll;
import com.example.people.Entity.job.ResumeEntity;
import com.example.people.data.model.LoginUser;

@Database(entities = {JobMessage.class, LoginUser.class, JobMessageAll.class, ResumeEntity.class, Uuid.class, ImguEntity.class, CpnMessage.class},version = 1,exportSchema = false)


public abstract class JobDatabase extends RoomDatabase {

    private static JobDatabase INSTANCE;
  public   static synchronized JobDatabase getDatabase(Context context){

    if (INSTANCE==null){
        INSTANCE= Room.databaseBuilder(context.getApplicationContext(),JobDatabase.class,"job_database").allowMainThreadQueries().build();

    }
        return INSTANCE;
    }
    public abstract JobDao getJobDao();
     public abstract LoginDao getLoginDao();
   public  abstract JobMessageAllDao getJobMessageAllDao();
public abstract ReceiveResumeDao getReceiveResumeDao();
public abstract UuidDao getUuidDao();
public abstract UserDao getUserDao();
public  abstract EditmsgDao getEditmsgDao();


}
