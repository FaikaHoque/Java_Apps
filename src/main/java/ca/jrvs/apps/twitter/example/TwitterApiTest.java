package ca.jrvs.apps.twitter.example;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


import java.util.Arrays;


public class TwitterApiTest {

    private static String CONSUMER_KEY = "M5L2pCSuFWNFiBXuQAPTU9gWL";
    private static String CONSUMER_SECRET = "KYXAJCl0mVHxiVLhpKI5VuZsldcK12ONwnm4Q74iMnsQWnJBiN";
    private static String ACCESS_TOKEN = "1085915382156689410-2s4WIoZTDkf1jgtbmJKLUsw9tJRIkQ";
    private static String TOKEN_SECRET = "vBQvJsg9TRtj4YZAvpV6iihJBZ2H7ufwhy1BN599SNrfA";

    public static void main(String[] args) throws Exception {

        OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
        consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);

        HttpGet request = new HttpGet("https://api.twitter.com/1.1/users/search.json?q=realDonaldTrump");

        consumer.sign(request);
        System.out.println("Http Request Headers:");

        Arrays.stream(request.getAllHeaders()).forEach(System.out::println);
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = httpClient.execute(request);

        System.out.println(EntityUtils.toString(response.getEntity()));
    }

}
