package com.example.myapplication.Task;

import android.os.AsyncTask;

import lombok.Getter;
import lombok.NoArgsConstructor;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;

@NoArgsConstructor
public
class TwitterSearchTask extends AsyncTask<Query,Integer,QueryResult> {
    private Twitter twitter;
    @Getter
    private TwitterException twitterException = null;
    public TwitterSearchTask(Twitter twitter)
    {
        this.twitter = twitter;
    }

    @Override
    protected QueryResult doInBackground(Query... query) {
        QueryResult queryResult = null;
        try {
            for (Query q :  query) {
                 queryResult = twitter.search(q);
            }
        } catch (TwitterException e) {
            e.printStackTrace();
            this.twitterException = e;
        }
        return queryResult;
    }

    @Override
    protected void onPostExecute(QueryResult queryResult) {
        super.onPostExecute(queryResult);
    }
}
