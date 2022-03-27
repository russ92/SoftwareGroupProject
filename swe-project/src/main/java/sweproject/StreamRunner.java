package sweproject;

import twitter4j.TwitterException;

public interface StreamRunner {
    void start() throws TwitterException;
    void shutdown() throws TwitterException;
}
