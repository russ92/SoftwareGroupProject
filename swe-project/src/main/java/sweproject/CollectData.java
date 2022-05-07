package sweproject;

import sweproject.graph.sprint2.TwitterListener;
import sweproject.graph.sprint2.TwitterStreamRunner;
import twitter4j.*;
import twitter4j.Twitter;
import twitter4j.conf.ConfigurationBuilder;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class CollectData {

    public static void configureStream() throws TwitterException, IOException {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setTweetModeExtended(true);

        //Stream tweets
        TwitterListener tl = new TwitterListener();
        TwitterStreamRunner tsr = new TwitterStreamRunner(cb, tl);

        streamTweets(tsr);
    }

    //Todo: Find a way to stop streaming tweets without closing the whole application/JAR
    public static void streamTweets(TwitterStreamRunner tsr) throws TwitterException, IOException {
        try {
            tsr.start();
        } catch (Exception e) {
            stopStreamTweets(tsr);
        }
    }
    public static void stopStreamTweets(TwitterStreamRunner tsr) {
        System.out.println("Stopping tweet streaming... ");
        tsr.shutdown();
    }

    //Todo: Collecting old tweets doesn't work
    public static void collectTweets() throws TwitterException, IOException {

        GetProperties prop = new GetProperties();

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setTweetModeExtended(true);

        //Load stored tweets and users
        System.out.println("Loading stored tweets and users... ");
        StoredData.setStoredUsers(StoredData.readStoredUsers());
        StoredData.setStoredTweets(StoredData.readStoredTweets());

        //Build Twitter instance
        System.out.println("Building twitter instance... ");
        Twitter twitter = new TwitterFactory(cb.build()).getInstance();
        String[] tags = prop.getPropertyHashtags();


        for(String hashtag: tags) {
            try {
                QueryResult result = twitter.search(new Query(hashtag));
                List<Status> tweets = result.getTweets();
                System.out.println("Searching for tweets containing: " + hashtag + "\n" +
                        "Searches completed: " + (180 - result.getRateLimitStatus().getRemaining()) + "\n" +
                        "Searches remaining: " + result.getRateLimitStatus().getRemaining());

                // Search for old tweets
                for (Status s : tweets) {

                    Tweet tweet = new Tweet(s.getId(), s.getUser().getScreenName(), s.getText(), s.getRetweetCount(),
                            s.getCreatedAt());
                    // Condition for the full text of a retweet without the tweet text finishing with '...'
                    if (s.isRetweet()) {
                        tweet.setText("RT @" + s.getRetweetedStatus().getUser().getScreenName() + ": " + s.getRetweetedStatus().getText());
                    }

                    User user = new User(s.getUser().getScreenName(), s.getUser().getLocation(),
                            s.getUser().getDescription(), s.getUser().getFollowersCount());

                    System.out.print("Tweet:\t\t\t\t" + tweet + "Tweeted by user:\t" + user + "\n");
                    writer(tweet, user);
                }

            } catch(TwitterException e){
                System.out.println("--- SEARCH FAILED ---\n" + e.getLocalizedMessage());
            }
        }

    }

    public static void writer(sweproject.Tweet tweet, sweproject.User user){

        GetProperties prop = new GetProperties();

        if(!StoredData.getStoredTweets().containsKey(tweet.getStatus_id())) {
            StoredData.getStoredTweets().put(tweet.getStatus_id(), tweet);
            try (FileWriter fw = new FileWriter(prop.getTweetFilepath(), true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.print(tweet);
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("--- ERROR WRITING TWEET ---\t\t" + e.getLocalizedMessage() + "\n");
            }
        }

        if(!StoredData.getStoredUsers().containsKey(user.getUserhandle())) {
            StoredData.getStoredUsers().put(user.getUserhandle(), user);
            try (FileWriter fw = new FileWriter(prop.getUserFilepath(), true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.print(user);
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("--- ERROR WRITING USER ---\t\t" + e.getLocalizedMessage() + "\n");
            }
        }
    }

    public static void main(String[] args) throws IOException, TwitterException {
        //collectTweets();
        Hashtable<Long, Tweet> t = StoredData.readStoredTweets();
        System.out.println("Number of tweets: " + t.size());

        Hashtable<String, User> u = StoredData.readStoredUsers();
        System.out.println("Number of users: " + u.size());

//        try (FileWriter fw2 = new FileWriter("swe-project/VaxData/vax tweets.txt", true);
//             BufferedWriter bw2 = new BufferedWriter(fw2);
//             PrintWriter out = new PrintWriter(bw2)) {
//            for(long tweet: t.keySet()) {
//                out.print(t.get(tweet));
//            }
//        } catch (Exception e) {
//            System.out.println("--- ERROR WRITING USER ---\n" + e.getLocalizedMessage());
//        }
    }
}