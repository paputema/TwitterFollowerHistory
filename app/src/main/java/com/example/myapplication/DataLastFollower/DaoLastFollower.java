package com.example.myapplication.DataLastFollower;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface  DaoLastFollower {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void Insert(List<LastFollower> lastFollower);

    @Update
    void update(List<LastFollower> lastFollower);

    @Delete
    void delete(List<LastFollower> lastFollower);

    @Query("SELECT * FROM LastFollower")
    List<LastFollower> getAll();

    @Query("SELECT * FROM LastFollower WHERE userTwitterId = :i_userTwitterId")
    List<LastFollower> getAllByUserTwitterId(Long i_userTwitterId);

    @Query("SELECT followerId FROM LastFollower WHERE userTwitterId = :i_userTwitterId")
    List<Long> getAllIdByUserTwitterId(Long i_userTwitterId);



}
