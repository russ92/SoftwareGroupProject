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

    @Override
    public void onStatus(Status s) {
        LOG.info("Twitter Tweets, log info:");
        sweproject.Tweet tweet = new sweproject.Tweet(s.getId(), s.getUser().getName(), s.getText(), s.getRetweetCount(),
                s.getCreatedAt());
        sweproject.User user = new sweproject.User(s.getUser().getId(), s.getUser().getName(), s.getUser().getLocation(),
                s.getUser().getDescription(), s.getUser().getFollowersCount());
        System.out.println("\n" + tweet);
        System.out.println(user);

        try (FileWriter fw1 = new FileWriter("VaxData/corpus.txt", true);
             BufferedWriter bw1 = new BufferedWriter(fw1);
             PrintWriter out = new PrintWriter(bw1)) {
            out.print(tweet.toString());
        } catch (Exception e) {
            // TODO: handle exception
        }

        try (FileWriter fw2 = new FileWriter("VaxData/accountData.txt", true);
             BufferedWriter bw2 = new BufferedWriter(fw2);
             PrintWriter out = new PrintWriter(bw2)) {
            out.print(user.toString());
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}