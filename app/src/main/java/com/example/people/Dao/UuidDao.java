package com.example.people.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.people.Entity.Uuid;
import com.example.people.Entity.job.JobMessage;

import java.util.List;

@Dao
public interface UuidDao {

    @Insert
    void insertUuid(Uuid...uuids);
    @Delete
    void deleteUuid(Uuid...uuids);
    @Query("DELETE FROM UUID")
    void deleteAlluuid();
    @Query("SELECT * FROM Uuid ORDER BY ID DESC")

    List<Uuid>getuuid();
}
