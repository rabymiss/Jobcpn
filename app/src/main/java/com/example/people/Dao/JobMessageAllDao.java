package com.example.people.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.people.Entity.job.JobMessageAll;

import java.util.List;

@Dao
public interface JobMessageAllDao {

    @Insert
    void insertAmsg(JobMessageAll...jobMessageAlls);
//    @Delete
//    void deleteAmsg(JobMessageAll...jobMessageAlls);
//    @Update
//    void updateAmsg(JobMessageAll...jobMessageAlls);
//    @Query("DELETE FROM  JOBMESSAGEALL  ")
//    void deleteAllAmsgs();
//    @Query("SELECT * FROM JOBMESSAGEALL ORDER BY ID DESC")
//       // List<JobMessageAll> getAllAmsgs();
//    LiveData<List<JobMessageAll>> getAllAmsgLive();
    @Query("SELECT * FROM JobMessageAll WHERE  cpnName LIKE :pattern ORDER BY ID DESC")
   LiveData<List<JobMessageAll>>findcnpsWithPattern(String pattern);

    @Query("SELECT * FROM JobMessageAll WHERE  福利1 LIKE :pattern ORDER BY ID DESC")
    LiveData<List<JobMessageAll>>finduuid(String pattern);

   // List<JobMessageAll>findcnpsWithPattern(String pattern);


}
