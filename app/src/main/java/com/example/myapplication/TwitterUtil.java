package com.example.myapplication;

import java.util.Date;

import twitter4j.RateLimitStatus;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterUtil {
    public static Twitter getTwitterInstance() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("myB1VAGVSRpZVWFfZ1neLqGEr")
                .setOAuthConsumerSecret("3BlywS2qV6WN5bhgXAaAIvKf9S5ZHc6qJcMh0uOMNCFiY1RSx1")
                .setOAuthAccessToken("251211966-zWJPempRpdDtVXyhhJXEFga7PKXT7YJaDc6JWE6H")
                .setOAuthAccessTokenSecret("Aw7tnE7tdaOQvmTVQSCfKLwiziLvzYEtwPdjIa9JwoJyg");

        TwitterFactory tf = new TwitterFactory(cb.build());
        return  tf.getInstance();
    }
    public static void waitRate(@org.jetbrains.annotations.NotNull RateLimitStatus rateLimitStatus)
    {
        if(rateLimitStatus.getRemaining() < 1)
        {
            Date resetDate = new Date (rateLimitStatus.getResetTimeInSeconds() * 1000L);
            Date nowDate = new Date();
            try {
                Thread.sleep(resetDate.getTime() - nowDate.getTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    public static boolean isRare(RateLimitStatus rateLimitStatus)
    {
        Date resetDate = new Date (rateLimitStatus.getResetTimeInSeconds() * 1000L);
        Date nowDate = new Date();
        return  (rateLimitStatus.getRemaining() > 0 || nowDate.after(resetDate));
    }
}
