package sweproject.graph.sprint2;

import org.springframework.stereotype.Component;
import sweproject.GetProperties;
import sweproject.Tweet;
import sweproject.User;
import twitter4j.Status;
import twitter4j.StatusAdapter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

@Component
public class TwitterListener extends StatusAdapter {

    @Override
    public void onStatus(Status s) {

        GetProperties prop = new GetProperties();

        Tweet tweet = new Tweet(s.getId(), s.getUser().getScreenName(), s.getText(), s.getRetweetCount(),
                s.getCreatedAt());
        // Condition for the full text of a retweet without the tweet text finishing with '...'
        if (s.isRetweet()) {
            tweet.setText( "RT @" + s.getRetweetedStatus().getUser().getScreenName() + ": " + s.getRetweetedStatus().getText());
        }

        User user = new User(s.getUser().getScreenName(), s.getUser().getLocation(),
                s.getUser().getDescription(), s.getUser().getFollowersCount());

        System.out.print(tweet + "" + user);

        // Write to files
        try (FileWriter fw1 = new FileWriter(prop.getTweetFilepath(), true);
             BufferedWriter bw1 = new BufferedWriter(fw1);
             PrintWriter out = new PrintWriter(bw1)) {
            out.print(tweet);
        } catch (Exception e) {
            System.out.println("--- ERROR WRITING TWEET ---\n" + e.getLocalizedMessage());
        }

        //ToDo: Catch any duplicate users before writing them to the file
        try (FileWriter fw2 = new FileWriter(prop.getUserFilepath(), true);
             BufferedWriter bw2 = new BufferedWriter(fw2);
             PrintWriter out = new PrintWriter(bw2)) {
            out.print(user);
        } catch (Exception e) {
            System.out.println("--- ERROR WRITING USER ---\n" + e.getLocalizedMessage());
        }
    }
}