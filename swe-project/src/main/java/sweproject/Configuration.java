package sweproject;

import sweproject.graph.sprint2.TwitterListener;
import sweproject.graph.sprint2.TwitterStreamRunner;
import twitter4j.*;
import twitter4j.Twitter;
import twitter4j.conf.ConfigurationBuilder;

import java.io.*;
import java.util.*;

public class Configuration {

    public static void configureStream() throws TwitterException, IOException {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setTweetModeExtended(true);

        //Stream tweets
        TwitterListener tl = new TwitterListener();
        TwitterStreamRunner tsr = new TwitterStreamRunner(cb, tl);

        streamTweets(tsr);
    }

    //Todo: Find a way to stop streaming tweets without closing the whole application/JAR
    public static void streamTweets(TwitterStreamRunner tsr) throws TwitterException, IOException {
        try {
            tsr.start();
        } catch (Exception e) {
            stopStreamTweets(tsr);
        }
    }
    public static void stopStreamTweets(TwitterStreamRunner tsr) {
        System.out.println("Stopping tweet streaming... ");
        tsr.shutdown();
    }

    //Todo: Collecting old tweets doesn't work
    public static void collectTweets() throws TwitterException, IOException {

        GetProperties prop = new GetProperties();

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setTweetModeExtended(true);

        //Build Twitter instance
        Twitter twitter = TwitterFactory.getSingleton();
        Query query = new Query(Arrays.toString(prop.getPropertyHashtags()));
        QueryResult result = twitter.search(query);

        //Search for old tweets
        List<Status> status = result.getTweets();
        for (Status s : status) {
            sweproject.Tweet tweet = new sweproject.Tweet(s.getId(), s.getUser().getScreenName(), s.getText(), s.getRetweetCount(),
                    s.getCreatedAt());
            writer(tweet);
        }

    }

    public static void writer(sweproject.Tweet tweet){
        try (FileWriter fw = new FileWriter("VaxData/vaxTweets.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(tweet.toString().replace("\n", " "));
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}