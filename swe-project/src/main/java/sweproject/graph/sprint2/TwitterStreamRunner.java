package sweproject.graph.sprint2;

import org.springframework.stereotype.Component;
import sweproject.GetProperties;
import sweproject.graph.sprint2.StreamRunner;
import sweproject.graph.sprint2.TwitterListener;
import twitter4j.FilterQuery;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

import javax.annotation.PreDestroy;
import java.util.Arrays;

@Component
public class TwitterStreamRunner implements StreamRunner {

    private final ConfigurationBuilder configurationBuilder;

    private final TwitterListener twitterListener;

    private TwitterStream twitterStream;

    public TwitterStreamRunner(
            ConfigurationBuilder configurationBuilder, TwitterListener twitterListener) {
        this.configurationBuilder = configurationBuilder;
        this.twitterListener = twitterListener;
    }

    @Override
    public void start() throws TwitterException {
        GetProperties prop = new GetProperties();

        twitterStream = new TwitterStreamFactory().getInstance();
        twitterStream.addListener(twitterListener);

        FilterQuery filterQuery = new FilterQuery(prop.getPropertyHashtags());
        twitterStream.filter(filterQuery);
        System.out.println("Streaming tweets from twitter using the hashtags: " + "\n" + Arrays.toString(prop.getPropertyHashtags()));
    }

    @PreDestroy
    public void shutdown() {
        if (twitterStream != null) {
            twitterStream.shutdown();
        }
    }
}