package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dto.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static ca.jrvs.apps.twitter.util.TweetUtil.*;

public class TwitterServiceImp implements TwitterService {

    private CrdDao dao;

    public TwitterServiceImp(CrdDao dao){
        this.dao = dao;
    }

    @Override
    public Tweet postTweet(String text, Double latitude, Double longitude) {

        Tweet postTweet = buildTweet(text, longitude, latitude);
        validatePostTweet(postTweet);
        try
        {
            Tweet responseTweet = (Tweet) dao.create(postTweet);
            printTweet(responseTweet);
        }catch (Exception e){
            throw new RuntimeException("Failed to post tweet");
        }
        return postTweet;
    }

    @Override
    public Tweet showTweet(String id, String[] fields) {
        Tweet tweet;
        if(!validId.test(id)){
            throw new IllegalArgumentException("ID must be number");
        }
        try{
            tweet = (Tweet) dao.findById(id);
            printTweet(selectFields(tweet, fields));
        }catch (IOException e){
            throw new RuntimeException("Failed to show tweet");
        }
        return tweet;
    }

    @Override
    public List<Tweet> deleteTweets(String[] ids) {
        List<Tweet> tweets = new ArrayList<>();
        for (String id : ids){
            validId.test(id);
            Tweet tweet = (Tweet) dao.deleteById(id);
            printTweet(tweet);
            tweets.add(tweet);
        }
        return tweets;
    }
    private void printTweet(Tweet tweet){
        try{
            System.out.println(JsonUtil.toPrettyJson(tweet));
        }catch (JsonProcessingException e){
            throw new RuntimeException("Unable to handle");
        }
    }
}
