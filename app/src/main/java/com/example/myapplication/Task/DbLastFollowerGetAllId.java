package com.example.myapplication.Task;

import android.os.AsyncTask;

import com.example.myapplication.DatabaseMyFollower;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public
class DbLastFollowerGetAllId extends AsyncTask<Object,Integer,List<Long>> {
    private DatabaseMyFollower db;
    private Long myTwitterId;


    @Override
    protected List<Long> doInBackground(Object... object) {
        return db.daoLastFollower().getAllIdByUserTwitterId(myTwitterId);
    }

    @Override
    protected void onPostExecute(List<Long> lastFollowerList) {
        super.onPostExecute(lastFollowerList);
    }
}
