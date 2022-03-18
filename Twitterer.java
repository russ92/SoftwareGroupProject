import twitter4j.Query;
import twitter4j.QueryResults;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterException;
import twitter4j.Paging;

import java.io.IOException;
import java.io.PrintStream;

import java.util.ArrayList;
import java.util.List;

import sun.misc.ObjectInputFilter.Status;

public class Twitter {

    private Twitter twitter;
    private PrintStream consolePrint;
    private List<Status> statuses;

    public Twitterer(PrintStream console) {
        twitter = TwitterFactory.getSingleton();
        consolePrint = console;
        statuses = new ArrayList<Status>();
    }

    @SuppressWarnings("unchecked")
    public void queryHashtags(String[] hashtags) throws TwitterException {
        Query query = new Query(hashtags);

        try {
            QueryResult result = twitter.search(query);
            int counter = 0;
            System.out.println("Tweets: " + result.getTweets().size());
            for (Status tweet : result.getTweets()) {
                counter++;
                System.out.println("Tweet No.: " + counter + ": @" + tweet.getUser().getName() + "Tweet \""
                        + tweet.getText() + "\"");
            }
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }
}