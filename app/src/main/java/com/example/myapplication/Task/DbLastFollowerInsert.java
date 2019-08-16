package com.example.myapplication.Task;

import android.os.AsyncTask;

import com.example.myapplication.DataLastFollower.LastFollower;
import com.example.myapplication.DatabaseMyFollower;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public
class DbLastFollowerInsert extends AsyncTask<Object,Integer,Object> {
    private DatabaseMyFollower db;
    private List<LastFollower> lastFollowerList;


    @Override
    protected Object doInBackground(Object... object) {
        db.daoLastFollower().Insert(lastFollowerList);

        return null;
    }

    @Override
    protected void onPostExecute(Object object) {
        super.onPostExecute(object);
    }
}
