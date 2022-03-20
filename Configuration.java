package sweproject;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.auth.AccessToken;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Configuration {

    public static ConfigurationBuilder getConfigurationBuilder() {

        ConfigurationBuilder cb = new ConfigurationBuilder();

        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("oauth.consumerKey")
                .setOAuthConsumerSecret("oauth.consumerSecret")
                .setOAuthAccessToken("oauth.accessToken")
                .setOAuthAccessTokenSecret("oauth.accessTokenSecret");

        return cb;
    }

    public static void main(String[] args) throws TwitterException {

        ConfigurationBuilder cb = new ConfigurationBuilder();

        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("gTWIzVqaTvmjacNljrRQgaUIX")
                .setOAuthConsumerSecret("05gAN6pxHE0h4JvwsvTxa4crr8qGHxz6yQHMhkjOMcXswHABIJ")
                .setOAuthAccessToken("1500840083271962637-FtvxEI2m3gdBiddNEBrTVfc6xvIHNh")
                .setOAuthAccessTokenSecret("oTrFpROQkeDoPfpSRNIp6NeqOVQW9rHYOXXV5jLyl5hjA");
        TwitterFactory tf = new TwitterFactory(cb.build());

//        TwitterFactory tf = new TwitterFactory(Configuration.getConfigurationBuilder().build());
//
        twitter4j.Twitter twitter = tf.getInstance();

        List<Status> status = twitter.getHomeTimeline();

        for(Status s:status) {
            Tweet tweet = new Tweet(s.getId(),s.getUser().getScreenName(), s.getText(), s.getRetweetCount(), s.getCreatedAt());
            // System.out.println(s.getUser().getName() + " " + s.getText());

            System.out.println(tweet.toString());
        }

    }


}