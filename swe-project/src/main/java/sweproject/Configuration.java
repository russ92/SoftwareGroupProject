package sweproject;

import sweproject.graph.sprint2.TwitterListener;
import sweproject.graph.sprint2.TwitterStreamRunner;
import twitter4j.*;
import twitter4j.Twitter;
import twitter4j.conf.ConfigurationBuilder;

import java.io.*;
import java.util.*;

public class Configuration {

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

        //Build Twitter instance
        Twitter twitter = TwitterFactory.getSingleton();
        Query query = new Query(Arrays.toString(prop.getPropertyHashtags()));
        QueryResult result = twitter.search(query);

        //Search for old tweets
        List<Status> status = result.getTweets();
        for (Status s : status) {
            sweproject.Tweet tweet = new sweproject.Tweet(s.getId(), s.getUser().getScreenName(), s.getText(), s.getRetweetCount(),
                    s.getCreatedAt());
            writer(tweet);
        }

    }

    public static void writer(sweproject.Tweet tweet){
        try (FileWriter fw = new FileWriter("VaxData/vaxTweets.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(tweet.toString().replace("\n", " "));
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    // Hashtable to track the usernames in the dataset.
    private static Hashtable<Long, String> storedTweets;

    public static Hashtable<Long, String> getStoredTweets() {
        return storedTweets;
    }

    public static void setStoredTweets(Hashtable<Long, String> storedTweets) {
        Configuration.storedTweets = storedTweets;
    }

    public static Hashtable<Long, String> readStoredTweets(){
        GetProperties prop = new GetProperties();

        Hashtable<Long, String> tweets = new Hashtable<>();
        try{
            //BufferedReader buf = new BufferedReader(new FileReader("swe-project\\VaxData\\provided\\vax tweets.txt"));
            BufferedReader buf = new BufferedReader(new FileReader(prop.getTweetFilepath()));
            String lineJustFetched;

            while(true){
                lineJustFetched = buf.readLine();
                if(lineJustFetched == null){
                    break;
                }else{
                    String[] lineIn = lineJustFetched.split("\t");
                    //if (lineIn.length == 4 && lineIn[1].startsWith("@")) {
                    if (lineIn.length == 5 && lineIn[1].startsWith("@")) {
                        long id = Long.parseLong(lineIn[0]);
                        String name = lineIn[1];
                        tweets.put(id, name);
                    }
                }
            }

            buf.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return tweets;
    }

    public static void main(String[] args){
        Hashtable<Long, String> t = readStoredTweets();
        System.out.println("Number of tweets: " + t.size());

        Hashtable<String, User> u = TwitterListener.readStoredUsers();
        System.out.println("Number of users: " + u.size());

//        try (FileWriter fw2 = new FileWriter("swe-project/VaxData/vax tweets users.txt", true);
//             BufferedWriter bw2 = new BufferedWriter(fw2);
//             PrintWriter out = new PrintWriter(bw2)) {
//            for(String user: u.keySet()) {
//                out.print(u.get(user));
//            }
//        } catch (Exception e) {
//            System.out.println("--- ERROR WRITING USER ---\n" + e.getLocalizedMessage());
//        }
    }
}