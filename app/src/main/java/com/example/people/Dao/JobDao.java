package com.example.people.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.people.Entity.job.JobMessage;

import java.util.List;

@Dao
     public interface JobDao {

       @Insert
       void insertJob(JobMessage...jobMessages);
    @Delete
    void deleteJob(JobMessage...jobMessages);
    @Update
     void updateJob(JobMessage...jobMessages);
    @Query("DELETE FROM JOBMESSAGE")
    void deleteAllJobs();
    @Query("SELECT * FROM JOBMESSAGE ORDER BY ID DESC")
        //List<Word> getAllWords();
    LiveData<List<JobMessage>> getAllJobsLive();
    @Query("SELECT * FROM JOBMESSAGE WHERE  JobName LIKE :pattern ORDER BY ID DESC")
    LiveData<List<JobMessage>>findJobsWithPattern(String pattern);
    @Query("SELECT * FROM JOBMESSAGE WHERE  公司名称 LIKE :pattern ORDER BY ID DESC")
    LiveData<List<JobMessage>>findcpn(String pattern);



}

