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
class DbLastFollowerGetall extends AsyncTask<Object,Integer,List<LastFollower>> {
    private DatabaseMyFollower db;
    private Long myTwitterId;


    @Override
    protected List<LastFollower> doInBackground(Object... object) {
        return db.daoLastFollower().getAllByUserTwitterId(myTwitterId);


    }

    @Override
    protected void onPostExecute(List<LastFollower> lastFollowerList) {
        super.onPostExecute(lastFollowerList);
    }
}
