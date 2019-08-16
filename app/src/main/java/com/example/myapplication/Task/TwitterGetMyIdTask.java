package com.example.myapplication.Task;

import android.os.AsyncTask;

import lombok.Getter;
import lombok.NoArgsConstructor;
import twitter4j.RateLimitStatus;
import twitter4j.Twitter;
import twitter4j.TwitterException;

@NoArgsConstructor
public class TwitterGetMyIdTask extends AsyncTask<Object,Integer,Long> {
    private Twitter twitter;
    @Getter
    private TwitterException twitterException = null;
    private static RateLimitStatus rateLimitStatus = null;
    public TwitterGetMyIdTask(Twitter twitter)
    {
        this.twitter = twitter;
    }

    @Override
    protected Long doInBackground(Object[] object) {

        try {
            return twitter.getId();
        } catch (TwitterException e) {
            e.printStackTrace();
            twitterException = e;
            return null;
        }
    }
}
