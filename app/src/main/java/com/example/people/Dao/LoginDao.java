package com.example.people.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.people.data.model.LoginUser;

import java.util.List;


@Dao
public interface LoginDao {



        @Insert
        void insertUser(LoginUser...loginUsers);
        @Delete
        void deleteUser(LoginUser...loginUsers);
        @Update
        void updateUser(LoginUser...loginUsers);
        @Query("DELETE FROM LoginUser")
        void deleteAllUsers();
        @Query("SELECT * FROM LOGINUSER ORDER BY ID DESC")
                //List<Word> getAllWords();
        LiveData<List<LoginUser>> getAllUserLive();

        @Query("SELECT * FROM LoginUser WHERE account  LIKE :pattern ORDER BY ID DESC")
        LiveData<List<LoginUser>>findUserWithPattern(String pattern);

}
