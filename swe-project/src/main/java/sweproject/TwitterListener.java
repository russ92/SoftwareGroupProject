package sweproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import twitter4j.Status;
import twitter4j.StatusAdapter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

@Component
public class TwitterListener extends StatusAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(TwitterListener.class);

    private static final String USER_DATA = "VaxData/vax tweets users.txt";
    private static final String TWEET_DATA = "VaxData/vax tweets.txt";

    @Override
    public void onStatus(Status s) {
        LOG.info("Twitter Tweets, log info:");
        sweproject.Tweet tweet = new sweproject.Tweet(s.getId(), s.getUser().getScreenName(), s.getRetweetCount());
        sweproject.User user = new sweproject.User(s.getUser().getScreenName(), s.getUser().getLocation(),
                s.getUser().getDescription(), s.getUser().getFollowersCount());
        System.out.println("\n" + tweet);
        System.out.println(user);

        // Write to files
        try (FileWriter fw1 = new FileWriter(TWEET_DATA, true);
             BufferedWriter bw1 = new BufferedWriter(fw1);
             PrintWriter out = new PrintWriter(bw1)) {
            out.println(tweet);
        } catch (Exception e) {
            // TODO: handle exception
        }

        try (FileWriter fw2 = new FileWriter(USER_DATA, true);
             BufferedWriter bw2 = new BufferedWriter(fw2);
             PrintWriter out = new PrintWriter(bw2)) {
            out.println(user);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}