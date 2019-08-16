package com.example.myapplication;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.myapplication.DataFollowerHistory.DaoFollowerHistory;
import com.example.myapplication.DataFollowerHistory.FollowerHistory;
import com.example.myapplication.DataLastFollower.DaoLastFollower;
import com.example.myapplication.DataLastFollower.LastFollower;

@Database(entities = {LastFollower.class, FollowerHistory.class}, version = 1,exportSchema = false)
@TypeConverters(  DateConverter.class)
public abstract class DatabaseMyFollower extends RoomDatabase {
    public abstract DaoLastFollower daoLastFollower();
    public abstract DaoFollowerHistory daoFollowerHistory();
}
