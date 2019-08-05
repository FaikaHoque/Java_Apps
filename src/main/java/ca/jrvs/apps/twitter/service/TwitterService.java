package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dto.Tweet;

import java.util.List;

public interface TwitterService {

    /**
     * Post a Tweet along with geo location
     * Print Tweet Json which returned by Twitter REST API
     *
     * @param text tweet text
     * @param latitude geo latitude
     * @param longitude geo longtitude
     * @return Tweet object which is returned by the tweet API
     *
     * @throws IllegalArgumentException if text exceed maximum number of charactertistics
     * or lan/long
     */

    Tweet postTweet(String text, Double latitude, Double longitude);

    /**
     * search a Tweet by id and print Tweet object which returned by tweeter REST API
     * @param id tweet id
     * @param fields print Tweet fields from this param
     * @throws IllegalArgumentException if id or field param empty
     */
    void showTweet(String id, String[] fields);

    /**
     * Delete tweet by id
     * Print Tweet object which is returned by Twitter REST API
     * @param ids tweet IDs which will be deleted
     *
     */
    List<Tweet> deleteTweet(String[] ids);

}
