package sweproject;

import sweproject.graph.sprint2.TwitterListener;
import sweproject.graph.sprint2.TwitterStreamRunner;
import sweproject.graph.sprint3.GraphIO;
import sweproject.graph.sprint4.Stances;
import sweproject.graph.sprint5.WriteHashtags;
import sweproject.graph.sprint6.WriteHashtagGraph;
import twitter4j.*;
import twitter4j.Twitter;
import twitter4j.conf.ConfigurationBuilder;

import java.io.*;
import java.util.*;

public class Main {


    public static ConfigurationBuilder getConfigurationBuilder() throws IOException {

        ConfigurationBuilder cb = new ConfigurationBuilder();

        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("oauth.consumerKey")
                .setOAuthConsumerSecret("oauth.consumerSecret")
                .setOAuthAccessToken("oauth.accessToken")
                .setOAuthAccessTokenSecret("oauth.accessTokenSecret");

        return cb;
    }

    public static void main(String[] args) throws TwitterException, IOException {

        boolean menu = true;

        while (menu) {
            Scanner scn = new Scanner(System.in);
            System.out.println("1 stream or search tweets \n" +
                    "OR 2 to work with tweet and retweet graphs\n" +
                    "OR 3 to assign stances \n" +
                    "OR 4 to write hashtags \n" +
                    "OR 5 to work with hashtags");
            int choice = scn.nextInt();

            if (choice == 1) {

                menu = false;
            } else if (choice == 2) {
                GraphIO.GraphIO();
                menu = false;
            } else if (choice == 3) {
                Stances.Stances();
                menu = false;
            } else if (choice == 4) {
                WriteHashtags.WriteHashtags();
                menu = false;
            } else if (choice == 5) {
                WriteHashtagGraph.WriteHashtagGraph();
                menu = false;
            } else {
                System.out.println("PLEASE SELECT 1 OR 2 OR 3 OR 4 OR 5");
            }
            GetProperties prop = new GetProperties();

            ConfigurationBuilder cb = new ConfigurationBuilder();
            TwitterFactory tf = new TwitterFactory(cb.build());


            //Build Twitter instance
            Twitter twitter = TwitterFactory.getSingleton();
            Query query = new Query(Arrays.toString(prop.getPropertyHashtags()));
            QueryResult result = twitter.search(query);

            //Search for old tweets
//        List<Status> status = result.getTweets();
//        for (Status s : status) {
//            sweproject.Tweet tweet = new sweproject.Tweet(s.getId(), s.getUser().getScreenName(), s.getText(), s.getRetweetCount(),
//                    s.getCreatedAt());
//            writer(tweet);
//        }


            //Stream tweets
            TwitterListener tl = new TwitterListener();
            TwitterStreamRunner tsr = new TwitterStreamRunner(cb, tl);
            try {
                tsr.start();
            } catch (Exception e) {
                tsr.shutdown();
            }
        }

        public static void writer (sweproject.Tweet tweet){
            try (FileWriter fw = new FileWriter("VaxData/vaxTweets.txt", true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.println(tweet.toString().replace("\n", " "));
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }