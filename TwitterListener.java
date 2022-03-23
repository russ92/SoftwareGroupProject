package sweproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import twitter4j.Status;
import twitter4j.StatusAdapter;

@Component
public class TwitterListener extends StatusAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(TwitterListener.class);

    @Override
    public void onStatus(Status s){
        LOG.info("Twitter Tweets, log info:");
        Tweet tweet = new Tweet(s.getId(), s.getUser().getName(), s.getText(), s.getRetweetCount(),
                s.getCreatedAt());
        User user = new User(s.getUser().getId(), s.getUser().getName(), s.getUser().getLocation(), s.getUser().getDescription(), s.getUser().getFollowersCount());
        System.out.println("\n" + tweet);
        System.out.println(user);
    }
}
