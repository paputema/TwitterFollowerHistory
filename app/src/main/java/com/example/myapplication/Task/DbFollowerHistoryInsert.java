package com.example.myapplication.Task;

import android.os.AsyncTask;

import com.example.myapplication.DataFollowerHistory.FollowerHistory;
import com.example.myapplication.DataLastFollower.LastFollower;
import com.example.myapplication.DatabaseMyFollower;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public
class DbFollowerHistoryInsert extends AsyncTask<Object,Integer,Object> {
    private DatabaseMyFollower db;
    private FollowerHistory followerHistory;


    @Override
    protected Object doInBackground(Object... object) {
        db.daoFollowerHistory().Insert(followerHistory);
        return null;

    }

}
