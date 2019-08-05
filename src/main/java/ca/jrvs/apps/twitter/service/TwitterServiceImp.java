package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dto.Coordinates;
import ca.jrvs.apps.twitter.dto.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwitterServiceImp implements TwitterService{

    private static final int MAX_LEN_TWEET = 200;
    private static final double MAX_LAT = 90.0;
    private static final double MIN_LAT = -90.0;
    private static final double MAX_LONGI = 180.0;
    private static final double MIN_LONGI = -180.0;

    private CrdDao dao;

    public TwitterServiceImp(CrdDao dao) {
        this.dao = dao;
    }

    @Override
    public Tweet postTweet(String text, Double latitude, Double longitude) {
        //Build request tweet
        Tweet postTweet = generateTweet(text, latitude, longitude);
        try {
            Tweet response = (Tweet) dao.create(postTweet);
            printTweet(response);
        }catch (Exception e){
            throw new RuntimeException("Failed to post", e);
        }
        return postTweet;
    }

    private void printTweet(Tweet tweet)
    {
        try{
            System.out.println(JsonUtil.toPrettyJson(tweet));
        }catch (JsonProcessingException e){
            throw new RuntimeException("Unable to convert tweet obejct to string", e);
        }
    }

    protected Tweet generateTweet(String string, Double latitude, Double longitude)
    {
     if (string.toCharArray().length > MAX_LEN_TWEET || string.isEmpty())
     {
         throw new IllegalArgumentException("Not a valid tweet");
     }
     if (latitude < MIN_LAT || latitude > MAX_LAT || longitude < MIN_LONGI || longitude > MAX_LONGI)
         throw new IllegalArgumentException("Invalid Latitude or longitude");
     Tweet tweet = new Tweet();
        Coordinates coordinates = new Coordinates();
        tweet.setText(string);
        coordinates.setCoordinates(Arrays.asList(latitude,longitude));
        tweet.setCoordinates(coordinates);
        return tweet;

    }

    @Override
    public void showTweet(String id, String[] fields) {

        //validate id
        if(!validId(id))
        {
            throw new IllegalArgumentException("ID must be number");
        }
        try
        {
            Tweet response = (Tweet) dao.findById(id);
            System.out.println(JsonUtil.toPrettyJson(response));
        }catch (Exception e)
        {
            System.out.println("Failed to show the tweet");
        }

    }

    @Override
    public List<Tweet> deleteTweet(String[] ids) {
        List<Tweet> tweets = new ArrayList<>();
        for(String id : ids)
        {
            validId(id);
            Tweet tweet = (Tweet) dao.deleteById(id);
            printTweet(tweet);
        }
        return tweets;
    }
    public boolean validId(String string)
    {
        return string!=null && string.chars().noneMatch(Character-> Character < '0' || Character > '9');
    }

}
