package com.example.myapplication.DataFollowerHistory;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface  DaoFollowerHistory {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void Insert(FollowerHistory followerHistory );

    @Update
    void update(FollowerHistory followerHistory);

    @Delete
    void delete(FollowerHistory followerHistory);

    @Query("SELECT * FROM FollowerHistory")
    List<FollowerHistory> getAll();

    @Query("SELECT * FROM FollowerHistory WHERE userTwitterId = :i_userTwitterId")
    List<FollowerHistory> getAllByUserTwitterId(Long i_userTwitterId);

    @Query("SELECT * FROM FollowerHistory WHERE userTwitterId = :i_userTwitterId AND followerId = :i_followerId")
    List<FollowerHistory> getAllByUserTwitterIdAndFollowerId(Long i_userTwitterId, Long i_followerId);




}
