package com.example.people.ui.login;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.people.Dao.LoginDao;
import com.example.people.data.model.LoginUser;

@Database(entities = {LoginUser.class},version = 1,exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    private static UserDatabase INSTANCE;
    static synchronized UserDatabase getDatabase(Context context){
        if (INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(),UserDatabase.class,"user_database").build();
        }
        return INSTANCE;
    }
    public abstract LoginDao getLoginDao();
}
