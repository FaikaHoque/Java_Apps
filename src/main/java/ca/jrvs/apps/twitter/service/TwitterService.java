package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dto.Tweet;

import java.util.List;

public interface TwitterService {

    Tweet postTweet(String text, Double latitude, Double longitude);

    Tweet showTweet(String id, String[] fields);

    List<Tweet> deleteTweets(String[] ids);
}
