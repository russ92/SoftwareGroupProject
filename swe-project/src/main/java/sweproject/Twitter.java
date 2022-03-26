package sweproject;

import sun.misc.ObjectInputFilter.Status;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Twitter {

    private twitter4j.Twitter twitter;
    private PrintStream consolePrint;
    private List<Status> statuses;

    public void Twitterer(PrintStream console) {
        twitter = TwitterFactory.getSingleton();
        consolePrint = console;
        statuses = new ArrayList<Status>();
    }

    @SuppressWarnings("unchecked")
    public void queryHashtags(String hashtags) throws TwitterException {
        Query query = new Query(hashtags);

        try {
            QueryResult result = twitter.search(query);
            int counter = 0;
            System.out.println("Tweets: " + result.getTweets().size());
            for (twitter4j.Status tweet : result.getTweets()) {
                counter++;
                System.out.println("Tweet No.: " + counter + ": @" + tweet.getUser().getName() + "Tweet \""
                        + tweet.getText() + "\"");
            }
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }
}