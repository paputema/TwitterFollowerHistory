package com.example.myapplication.Task;

import android.os.AsyncTask;

import com.example.myapplication.TwitterUtil;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import twitter4j.IDs;
import twitter4j.RateLimitStatus;
import twitter4j.Twitter;
import twitter4j.TwitterException;

@NoArgsConstructor
public class TwitterGetFollowerIdsTask extends AsyncTask<Object,Integer,List<Long> > {
    private Twitter twitter;
    @Getter
    private TwitterException twitterException = null;
    private static RateLimitStatus rateLimitStatus = null;
    public TwitterGetFollowerIdsTask(Twitter twitter)
    {
        this.twitter = twitter;
    }

    @Override
    protected List<Long>  doInBackground(Object[] object) {
        List<Long> followerIds = new ArrayList();
        long cursor = IDs.START;
        IDs iDs = null;
        do try {
            iDs = twitter.getFollowersIDs(cursor);
            for(long id : iDs.getIDs())
            {
                followerIds.add(id);
            }
            cursor = iDs.getNextCursor();
            rateLimitStatus = iDs.getRateLimitStatus();
            TwitterUtil.waitRate(rateLimitStatus);
        } catch (TwitterException e) {
            e.printStackTrace();
            twitterException = e;
        } while (iDs.hasNext());
        return followerIds;
    }
}
