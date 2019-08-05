package ca.jrvs.apps.twitter.util;

import ca.jrvs.apps.twitter.dto.Coordinates;
import ca.jrvs.apps.twitter.dto.Tweet;

import java.util.Arrays;
import java.util.function.Predicate;

public class TweetUtil {
    private final static Double MAX_LAT = 90d;
    private final static Double MIN_LAT = -90d;
    private final static Double MAX_LON = 180d;
    private final static Double MIN_LON = -180d;

    private final static Integer MAX_TWEET_CHAR = 140;

    public static Predicate<String> validId = (id) -> !StringUtil.isEmpty(id) && id.chars().
            noneMatch(c -> c < '0' || c > '9');

    public static void validatePostTweet(Tweet tweet)
    {
        String text = tweet.getText();
        Double longitude = tweet.getCoordinates().getCoordinates().get(0);
        Double latitude = tweet.getCoordinates().getCoordinates().get(1);
        validatePostTweet(text, longitude, latitude);
    }
    public static void validatePostTweet(String text, Double longitude, Double latitude) {
        if (StringUtil.isEmpty(text) || text.toCharArray().length > MAX_TWEET_CHAR) {
            throw new IllegalArgumentException("Invalid Tweet");
        }
        if (latitude < MIN_LAT || latitude > MAX_LAT || longitude < MIN_LON
                || longitude > MAX_LON) {
            throw new IllegalArgumentException("Invalid latitude or longitude value");
        }
    }
    public static Tweet buildTweet(String text, Double longitude, Double latitude){
        Tweet tweet = new Tweet();
        tweet.setText(text);
        Coordinates coordinates = new Coordinates();
        coordinates.setCoordinates(Arrays.asList(longitude, latitude));
        tweet.setCoordinates(coordinates);
        return tweet;
    }

}
