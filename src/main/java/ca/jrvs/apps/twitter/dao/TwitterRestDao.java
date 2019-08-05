package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dto.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class TwitterRestDao implements CrdDao <Tweet, String> {

    //******URI constant*******
    private static final String BASE_API_URL = "https://api.twitter.com";
    private static final String POST_PATH = "/1.1/statuses/update.json";
    private static final String SHOW_PATH = "/1.1/statuses/show.json";
    private static final String DELETE_PATH = "/1.1/statuses/destroy";

    private static final String Query_Symbol = "?";
    private static final String Ampersand = "&";
    private static final String Equal = "=";

    //Response to the code
    private static final int HTTP_Response = 200;

    private HttpHelper httpHelper;

    //Constructor
    public TwitterRestDao(HttpHelper httpHelper) {
        this.httpHelper = httpHelper;
    }

    @Override
    public Tweet create(Tweet tweet) {

        //construct URI

        URI uri;

        try{

          uri = getPost(tweet);

        }catch (URISyntaxException | UnsupportedEncodingException e)
        {
            throw new IllegalArgumentException("Invalid tweet", e);
        }
        //Execute HTTP Request
        HttpResponse httpResponse = httpHelper.httpPost(uri);

        // retrun validated resposne
        return parseResponseBody (httpResponse, HTTP_Response);
    }

    @Override
    public Tweet findById(String id) {
        URI uri;

        try{

            uri = getShow(id);

        }catch (URISyntaxException e)
        {
            throw new IllegalArgumentException("Unable to find uri", e);
        }
        //Execute HTTP Request
        HttpResponse httpResponse = httpHelper.httpGet(uri);

        // retrun validated resposne
        return parseResponseBody (httpResponse, HTTP_Response);

    }

    @Override
    public Tweet deleteById(String id) {
        URI uri;

        try{

            uri = getDel(id);

        }catch (URISyntaxException e)
        {
            throw new IllegalArgumentException("Unable to find uri", e);
        }
        //Execute HTTP Request
        HttpResponse httpResponse = httpHelper.httpPost(uri);

        // retrun validated resposne
        return parseResponseBody(httpResponse, HTTP_Response);
    }

    //*** Buid the url to post the tweet, convert the response entity to object through JSON ****

    protected URI getPost(Tweet tweet) throws URISyntaxException, UnsupportedEncodingException{
      String text = tweet.getText();
      Double longitude = tweet.getCoordinates().getCoordinates().get(0);
      Double langitude = tweet.getCoordinates().getCoordinates().get(1);

      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(BASE_API_URL).append(POST_PATH).append(Query_Symbol);

      appendQueryParam(stringBuilder,"status", URLEncoder.encode(text, StandardCharsets.UTF_8.name()), true);
      appendQueryParam(stringBuilder,"long", longitude.toString(), false);
      appendQueryParam(stringBuilder,"lat", langitude.toString(), false);

      return new URI(stringBuilder.toString());
    }

    private void appendQueryParam(StringBuilder stringBuilder, String key, String value, boolean validate)
    {
        if(!validate){
            stringBuilder.append(Ampersand);
        }
        stringBuilder.append(key).append(Equal).append(value);
    }


    protected Tweet parseResponseBody(HttpResponse httpResponse, Integer expectedcode)
    {
        Tweet tweet = null;

        //check Response status
        int status = httpResponse.getStatusLine().getStatusCode();
        if(status!=expectedcode){
            throw new RuntimeException("Unexpected HTTP Status:" + status);
        }
        if(httpResponse.getEntity()==null)
        {
            throw new RuntimeException("Empty response body");
        }

        // Convert response entity to json string
        String jsonstring;
        try {
            jsonstring = EntityUtils.toString(httpResponse.getEntity());
        }catch (IOException e)
        {
            throw new RuntimeException("Failed to convert entity to string", e);
        }

        //Deserialize json string to object
        try{
            tweet = (Tweet) JsonUtil.toObjectFromJson(jsonstring, Tweet.class);
        }catch (IOException e){
            throw new RuntimeException("Unable to convert jsonsting into object");
        }

        return tweet;
    }

    protected URI getShow(String id) throws URISyntaxException{
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BASE_API_URL).append(SHOW_PATH).append(Query_Symbol);
        appendQueryParam(stringBuilder,"id", id, true);
        return new URI(stringBuilder.toString());
    }
    protected URI getDel(String id) throws URISyntaxException{
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BASE_API_URL).append(DELETE_PATH).append("/").append(id).append(".json");
        return new URI(stringBuilder.toString());
    }


}
