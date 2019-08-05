package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterRestDao;
import ca.jrvs.apps.twitter.dao.helper.ApacheHttpHelper;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.service.TwitterServiceImp;

public class TwitterCLIApp {
    public static void main(String[] args) {

        //create components
        HttpHelper httpHelper = new ApacheHttpHelper();
        CrdDao dao = new TwitterRestDao(httpHelper);
        TwitterService twitterService = new TwitterServiceImp(dao);

        //create Runner
        TwitterCLIRunner twitterCLIRunner = new TwitterCLIRunner(twitterService);

        //run Applications
        twitterCLIRunner.run(args);
    }

}
