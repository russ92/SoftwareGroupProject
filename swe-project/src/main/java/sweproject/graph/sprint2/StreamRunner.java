package sweproject.graph.sprint2;

import twitter4j.TwitterException;

public interface StreamRunner {
    void start() throws TwitterException;

    void shutdown() throws TwitterException;
}