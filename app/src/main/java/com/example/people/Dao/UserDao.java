package com.example.people.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.people.Entity.cpn.ImguEntity;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void  insert(ImguEntity... imguEntities);
    @Query("SELECT * FROM ImguEntity ORDER BY ID DESC ")
    LiveData<List<ImguEntity> > findAccount();
    @Query("DELETE  FROM ImguEntity")
    void deleteAll();
}
