package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.service.TwitterService;

public class TwitterCLIRunner {
    private TwitterService twitterService;

    public TwitterCLIRunner(TwitterService twitterService) {
        this.twitterService = twitterService;
    }
    public void run(String[] args)
    {
        if(args.length<2)
        {
            throw new RuntimeException("USAGE: TwitterCLI post|show|deleteTweet");
        }
        switch(args[0].toLowerCase())
        {
            case "post":
                String[] var = args[2].split(":");
                twitterService.postTweet(args[1],Double.parseDouble(var[0]),Double.parseDouble(var[1]));
                break;
            case "show":
                twitterService.showTweet(args[1],args[2].split(","));
                break;
            case "delete":
                twitterService.deleteTweet(args[1].split(","));
                break;
            default:
                System.out.println("USAGE: TwitterCLI post|show|delete");
                break;
        }
    }

}
