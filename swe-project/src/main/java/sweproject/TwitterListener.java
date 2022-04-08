package sweproject;

import org.springframework.stereotype.Component;
import twitter4j.Status;
import twitter4j.StatusAdapter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

@Component
public class TwitterListener extends StatusAdapter {

    private static final String USER_DATA = "swe-project/VaxData/vax tweets users.txt";
    private static final String TWEET_DATA = "swe-project/VaxData/vax tweets.txt";

    @Override
    public void onStatus(Status s) {

        Tweet tweet = new Tweet(s.getId(), s.getUser().getScreenName(), s.getText(), s.getRetweetCount(),
                s.getCreatedAt());
        System.out.println("\n" + tweet);

//        User user = new sweproject.User(s.getUser().getScreenName(), s.getGeoLocation().toString(),
//                s.getUser().getDescription(), s.getUser().getFollowersCount());
//        System.out.println(user);

        // Write to files
        try (FileWriter fw1 = new FileWriter(TWEET_DATA, true);
             BufferedWriter bw1 = new BufferedWriter(fw1);
             PrintWriter out = new PrintWriter(bw1)) {
            out.print(tweet);
        } catch (Exception e) {
            // TODO: handle exception
        }

//        try (FileWriter fw2 = new FileWriter(USER_DATA, true);
//             BufferedWriter bw2 = new BufferedWriter(fw2);
//             PrintWriter out = new PrintWriter(bw2)) {
//            out.print(user);
//        } catch (Exception e) {
//            // TODO: handle exception
//        }
    }
}