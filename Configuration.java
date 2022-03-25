//import twitter4j.*;
//import twitter4j.Twitter;
//import twitter4j.conf.ConfigurationBuilder;
//
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.*;
//
//public class Configuration {
//
//    public static ConfigurationBuilder getConfigurationBuilder() {
//
//        ConfigurationBuilder cb = new ConfigurationBuilder();
//
//        cb.setDebugEnabled(true)
//                .setOAuthConsumerKey("oauth.consumerKey")
//                .setOAuthConsumerSecret("oauth.consumerSecret")
//                .setOAuthAccessToken("oauth.accessToken")
//                .setOAuthAccessTokenSecret("oauth.accessTokenSecret");
//
//        return cb;
//    }
//
//    public static void main(String[] args) throws TwitterException {
//
//        ConfigurationBuilder cb = new ConfigurationBuilder();
//
//        cb.setDebugEnabled(true)
//                .setOAuthConsumerKey("gTWIzVqaTvmjacNljrRQgaUIX")
//                .setOAuthConsumerSecret("05gAN6pxHE0h4JvwsvTxa4crr8qGHxz6yQHMhkjOMcXswHABIJ")
//                .setOAuthAccessToken("1500840083271962637-FtvxEI2m3gdBiddNEBrTVfc6xvIHNh")
//                .setOAuthAccessTokenSecret("oTrFpROQkeDoPfpSRNIp6NeqOVQW9rHYOXXV5jLyl5hjA");
//        TwitterFactory tf = new TwitterFactory(cb.build());
//
////        TwitterFactory tf = new TwitterFactory(Configuration.getConfigurationBuilder().build());
//        Twitter twitter = tf.getInstance();
//        TwitterListener tl = new TwitterListener();
//        TwitterStreamRunner tsr = new TwitterStreamRunner(cb, tl);
//        tsr.start();
//
//        try {
//            Query query = new Query("#GetVaccinated");
//            query.setSinceId(myLastId);
//
//            query.setSinceId(sinceId);
//
//            query.setMaxId(maxId);
//
//            query.setLang(lang);
//
//            query.setCount(count);
//            QueryResult result;
//            result = twitter.search(query);
//            List<Status> tweets = result.getTweets();
//            for (Status s : tweets) {
//                Tweet t = new Tweet(s.getId(),s.getUser().getScreenName(), s.getText(), s.getRetweetCount(), s.getCreatedAt());
//                User users = new User(s.getUser().getId(), s.getUser().getName(), s.getUser().getLocation(), s.getUser().getDescription(),s.getUser().getFollowersCount());
//
//                System.out.println(t.getTimestamp());
//                System.out.println(users);
//            }
//
//            try
//            {
//                String filename= "VaxData/Users.txt";
//                Scanner scan = new Scanner(filename);
//                ArrayList<Integer> list = new ArrayList<Integer>();
//                while (scan.hasNext()){
//                    if(scan.hasNextLong()){
//                        list.add((int) scan.nextLong());
//                    }
//                }
//                System.out.println(list.toString());
//                FileWriter fw = new FileWriter(filename,true); //the true will append the new data
//                for (Status s : tweets) {
//                    User users = new User(s.getUser().getId(), s.getUser().getName(), s.getUser().getLocation(), s.getUser().getDescription(),s.getUser().getFollowersCount());
//                    fw.write(users.toString());//appends the string to the file
//                }
//                fw.close();
//            }
//            catch(IOException ioe)
//            {
//                System.err.println("IOException: " + ioe.getMessage());
//            }
//
//            System.exit(0);
//        } catch (TwitterException te) {
//            te.printStackTrace();
//            System.out.println("Failed to search tweets: " + te.getMessage());
//            System.exit(-1);
//        }
//
//        List<Status> status = twitter.getHomeTimeline();
//
//        for(Status s:statuses) {
//            Tweet tweet = new Tweet(s.getId(),s.getUser().getScreenName(), s.getText(), s.getRetweetCount(), s.getCreatedAt());
//            User users = new User(s.getUser().getId(), s.getUser().getName(), s.getUser().getLocation(), s.getUser().getDescription(),s.getUser().getFollowersCount());
//            // System.out.println(s.getUser().getName() + " " + s.getText());
//
//            System.out.println(users.getNum_followers());
//            System.out.println(tweet);
//        }
//
//    }
//
//
//}

import twitter4j.*;
        import twitter4j.conf.ConfigurationBuilder;
        import twitter4j.auth.AccessToken;

        import java.io.BufferedWriter;
        import java.io.File;
        import java.io.FileWriter;
        import java.io.PrintWriter;
        import java.io.FileWriter;
import java.util.*;

public class Configuration {

    public static ConfigurationBuilder getConfigurationBuilder() {

        ConfigurationBuilder cb = new ConfigurationBuilder();

        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("oauth.consumerKey")
                .setOAuthConsumerSecret("oauth.consumerSecret")
                .setOAuthAccessToken("oauth.accessToken")
                .setOAuthAccessTokenSecret("oauth.accessTokenSecret");

        return cb;
    }

    public static void main(String[] args) throws TwitterException {

        ConfigurationBuilder cb = new ConfigurationBuilder();

        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("gTWIzVqaTvmjacNljrRQgaUIX")
                .setOAuthConsumerSecret("05gAN6pxHE0h4JvwsvTxa4crr8qGHxz6yQHMhkjOMcXswHABIJ")
                .setOAuthAccessToken("1500840083271962637-FtvxEI2m3gdBiddNEBrTVfc6xvIHNh")
                .setOAuthAccessTokenSecret("oTrFpROQkeDoPfpSRNIp6NeqOVQW9rHYOXXV5jLyl5hjA");
        TwitterFactory tf = new TwitterFactory(cb.build());

        // TwitterFactory tf = new
        // TwitterFactory(Configuration.getConfigurationBuilder().build());
        //
        List<String> hashtags = Arrays.asList("#Vaccinated", "#Covid-19", "#VaccineMandate", "#CovidHoax",
                "#FuckVaccines", "#Vaxxed", "#MicrochipVaccine", "#GatesVaccine", "#NoVaccine", "#GetVaccinated",
                "#Booster");
        twitter4j.Twitter twitter = tf.getInstance();
        Query query = new Query(hashtags);
        QueryResult result = twitter.search(query);

        List<Status> status = result.getTweets();
        File Corpus = new File("test.txt");
        for (Status s : status) {
            Tweet tweet = new Tweet(s.getId(), s.getUser().getScreenName(), s.getText(), s.getRetweetCount(),
                    s.getCreatedAt());
            try (FileWriter fw = new FileWriter("corpus.txt", true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.println(tweet.toString());
            } catch (Exception e) {
                // TODO: handle exception
            }
            System.out.println(tweet.toString());
        }

        //Stream tweets

        TwitterListener tl = new TwitterListener();
        TwitterStreamRunner tsr = new TwitterStreamRunner(cb, tl);
        tsr.start();

    }

}