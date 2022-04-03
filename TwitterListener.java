<<<<<<< HEAD
=======
package sweproject;

import java.util.Scanner;

>>>>>>> origin/Owen
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import twitter4j.Status;
import twitter4j.StatusAdapter;

@Component
public class TwitterListener extends StatusAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(TwitterListener.class);

    @Override
<<<<<<< HEAD
    public void onStatus(Status status){
        LOG.info("Twitter = ", status.getText());
    }
}
=======
    public void onStatus(Status s) {
        LOG.info("Twitter Tweets, log info:");
        Tweet tweet = new Tweet(s.getId(), s.getUser().getName(), s.getText(), s.getRetweetCount(),
                s.getCreatedAt());
        User user = new User(s.getUser().getId(), s.getUser().getName(), s.getUser().getLocation(),
                s.getUser().getDescription(), s.getUser().getFollowersCount());
        System.out.println("\n" + tweet);
        System.out.println(user);

        Scanner scanner = new Scanner("VaxData/corpus.txt");
        string id = s.getId();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.contains(id)) {
                System.out.println("TWEET ALREADY COLLECTED");
            } else {
                try (FileWriter fw1 = new FileWriter("VaxData/corpus.txt", true);
                        BufferedWriter bw1 = new BufferedWriter(fw1);
                        PrintWriter out = new PrintWriter(bw1)) {
                    out.println(tweet.toString());
                } catch (Exception e) {
                    // TODO: handle exception
                }

                try (FileWriter fw2 = new FileWriter("VaxData/accountData.txt", true);
                        BufferedWriter bw2 = new BufferedWriter(fw2);
                        PrintWriter out = new PrintWriter(bw2)) {
                    out.println(user.toString());
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        }
    }
}
>>>>>>> origin/Owen
