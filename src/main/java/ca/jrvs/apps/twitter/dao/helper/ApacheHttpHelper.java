package ca.jrvs.apps.twitter.dao.helper;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.net.URI;

public class ApacheHttpHelper implements HttpHelper{

    private OAuthConsumer consumer;
    private HttpClient client;

    public ApacheHttpHelper(OAuthConsumer consumer, HttpClient client) {
        this.consumer = consumer;
        this.client = client;
    }

    public ApacheHttpHelper(){

        //initialize the consumer key and token

        String consumerKey = "M5L2pCSuFWNFiBXuQAPTU9gWL";
        String consumerSecret = "KYXAJCl0mVHxiVLhpKI5VuZsldcK12ONwnm4Q74iMnsQWnJBiN";
        String accessToken = "1085915382156689410-2s4WIoZTDkf1jgtbmJKLUsw9tJRIkQ";
        String tokenSecret = "vBQvJsg9TRtj4YZAvpV6iihJBZ2H7ufwhy1BN599SNrfA";


        // intialize the consumer key and token by environment variables
       /* String consumerKey = System.getenv("consumerKey");
        String consumerSecret = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String tokenSecret = System.getenv("tokenSecret");*/

        // setup oauth
        consumer = new CommonsHttpOAuthConsumer(consumerKey,consumerSecret);
        consumer.setTokenWithSecret(accessToken,tokenSecret);
        client = new DefaultHttpClient();

    }

    // create, send/execute the http post request
    @Override
    public HttpResponse httpPost(URI uri){
        try {
            HttpPost httpPost = new HttpPost(uri);
            consumer.sign(httpPost);
            return client.execute(httpPost);
        }catch (Exception e){
            throw new RuntimeException("Failed to execute", e);
        }

    }
    //post request is sent through the StringEntity
    @Override
    public HttpResponse httpPost(URI uri, StringEntity stringEntity) {
        try {
            HttpPost httpPost = new HttpPost(uri);
            if(stringEntity!=null)
                httpPost.setEntity(stringEntity);
            consumer.sign(httpPost);
            return client.execute(httpPost);
        }catch (Exception e){
            throw new RuntimeException("Failed to execute", e);
        }
    }

    //create, send/execute the Get request
    @Override
    public HttpResponse httpGet(URI uri) {
        try {
            HttpGet httpGet = new HttpGet(uri);
            consumer.sign(httpGet);
            return client.execute(httpGet);
        } catch (Exception e) {
            throw new RuntimeException("Failed to execute", e);

        }
    }

}
