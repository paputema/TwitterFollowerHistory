package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.myapplication.DataFollowerHistory.FollowerHistory;
import com.example.myapplication.DataLastFollower.LastFollower;
import com.example.myapplication.Task.DbFollowerHistoryInsert;
import com.example.myapplication.Task.DbLastFollowerGetAllId;
import com.example.myapplication.Task.DbLastFollowerGetall;
import com.example.myapplication.Task.DbLastFollowerInsert;
import com.example.myapplication.Task.TwitterGetFollowerIdsTask;
import com.example.myapplication.Task.TwitterGetMyIdTask;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import twitter4j.Twitter;

import static android.content.Context.MODE_PRIVATE;

public class MainActivity extends AppCompatActivity {
    private Twitter twitter;
    private DatabaseMyFollower db;
    private Long myTwitterId;
    //private TwitterSearchTask twitterSearchTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        twitter = TwitterUtil.getTwitterInstance();
        TwitterGetMyIdTask twitterGetMyIdTask = new TwitterGetMyIdTask(twitter);
        twitterGetMyIdTask.execute();
        try {
            myTwitterId = twitterGetMyIdTask.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        db = Room.databaseBuilder(getApplicationContext(),DatabaseMyFollower.class,"database-name").build();

        SharedPreferences config = getSharedPreferences("config", MODE_PRIVATE);

        TwitterGetFollowerIdsTask twitterGetFollowerIdsTask = new TwitterGetFollowerIdsTask(twitter);
        twitterGetFollowerIdsTask.execute();

        Boolean initialized = config.getBoolean("initialized",false);
        try {
            if (initialized) {

                DbLastFollowerGetAllId dbLastFollowerGetallId = new DbLastFollowerGetAllId(db,myTwitterId);
                dbLastFollowerGetallId.execute();

                List<Long> LastFollowerIds =  dbLastFollowerGetallId.get();
                List<Long> nowFollowerIds = twitterGetFollowerIdsTask.get();
                List<Long> removeFollowerIds =
                        (List<Long>) CollectionUtils.subtract(LastFollowerIds,nowFollowerIds);
                List<Long> newFollowerIds =
                        (List<Long>) CollectionUtils.subtract(nowFollowerIds,LastFollowerIds);

                List<LastFollower> removeLastFollowerList = new ArrayList<>();
                for(Long removeFollowerId : removeFollowerIds)
                {
                    DbFollowerHistoryInsert dbFollowerHistoryInsert = new DbFollowerHistoryInsert(db,new FollowerHistory(myTwitterId,removeFollowerId, FollowerHistory.HistoryType.remove));
                    dbFollowerHistoryInsert.execute();
                    removeLastFollowerList.add(new LastFollower(myTwitterId,removeFollowerId));
                }
                DbLastFollowerInsert dbLastFollowerInsert = new DbLastFollowerInsert(db,removeLastFollowerList);
                dbLastFollowerInsert.execute();

                List<LastFollower> newLastFollowerList = new ArrayList<>();
                for(Long newFollowerId : newFollowerIds)
                {
                    DbFollowerHistoryInsert dbFollowerHistoryInsert = new DbFollowerHistoryInsert(db,new FollowerHistory(myTwitterId,newFollowerId, FollowerHistory.HistoryType.follow));
                    dbFollowerHistoryInsert.execute();
                    newLastFollowerList.add(new LastFollower(myTwitterId,newFollowerId));
                }
                dbLastFollowerInsert = new DbLastFollowerInsert(db,newLastFollowerList);
                dbLastFollowerInsert.execute();

            } else {

                List<LastFollower> lastFollowerList = new ArrayList<>();
                for(Long id : twitterGetFollowerIdsTask.get())
                {
                    lastFollowerList.add( new LastFollower(myTwitterId,id));
                }
                DbLastFollowerInsert dbLastFollowerInsert = new DbLastFollowerInsert(db,lastFollowerList);
                dbLastFollowerInsert.execute();
                config.edit().putBoolean("initialized", true);
               }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public  void readDb(View view)
    {
        DbLastFollowerGetall dbLastFollowerGetall = new DbLastFollowerGetall(db,myTwitterId);
        dbLastFollowerGetall.execute();
        String result = "";
        try {
            for (LastFollower lastFollower : dbLastFollowerGetall.get())
            {
                result += lastFollower.getFollowerId().toString() + "\r\n";
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TextView editText2 =  findViewById(R.id.textView2);
        editText2.setText(result);
    }

    public void sendMessage(View view)
    {


        TextView editText2 =  findViewById(R.id.textView2);


        String result = "";

        /*
        //TwitterSearchTask twitterSearchTask = new TwitterSearchTask(twitter);
        //twitterSearchTask.execute(new Query[]{new Query(message)});
        QueryResult queryResult =
                null;
        try {
            //queryResult = twitterSearchTask.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(twitterSearchTask.getTwitterException() == null && queryResult !=null) {
            for (Status status : queryResult.getTweets()) {
                result += status.getText() + "\r\n";
            }
        }
        */

        TwitterGetFollowerIdsTask twitterGetFollowerIdsTask = new TwitterGetFollowerIdsTask(twitter);
        twitterGetFollowerIdsTask.execute();


        try {
            List<Long> ids  = twitterGetFollowerIdsTask.get();
            List<LastFollower> lastFollowerList = new ArrayList<>();
            for (Long id : ids)
            {
                result += id.toString() + "\r\n";
                lastFollowerList.add( new LastFollower(myTwitterId,id));
            }
            DbLastFollowerInsert dbLastFollowerInsert = new DbLastFollowerInsert(db,lastFollowerList);
            dbLastFollowerInsert.execute();

        } catch (ExecutionException e) {
            e.printStackTrace();
            result = e.getMessage();
        } catch (InterruptedException e) {
            e.printStackTrace();
            result = e.getMessage();
        }
        editText2.setText(result);
    }

}
