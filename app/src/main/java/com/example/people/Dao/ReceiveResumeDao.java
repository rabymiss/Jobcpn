package com.example.people.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.people.Entity.job.ResumeEntity;

import java.util.List;

@Dao
public interface ReceiveResumeDao {

    @Insert
    void insertResume(ResumeEntity...resumeEntities);
    @Query("DELETE FROM ResumeEntity ")
    void deleteall();
    @Query("SELECT * FROM ResumeEntity ORDER BY ID DESC")
        //List<Word> getAllWords();
   LiveData  < List<ResumeEntity> >getAllResLive();
    @Query("SELECT * FROM ResumeEntity WHERE uuid  LIKE :pattern ORDER BY ID DESC")
    LiveData<List<ResumeEntity>> findResumeWithPattern(String pattern);
}
