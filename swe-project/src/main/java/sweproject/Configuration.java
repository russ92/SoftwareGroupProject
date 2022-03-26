package sweproject;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.auth.AccessToken;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
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
        String consumerKey="";
        String consumerSecret="";
        String accessToken = "";
        String accessSecret="";

        try(FileReader reader = new FileReader("config.txt")) {
            Properties properties = new Properties();
            properties.load(reader);

            consumerKey = properties.getProperty("consumerKey");
            consumerSecret = properties.getProperty("consumerSecret");
            accessToken = properties.getProperty("accessToken");
            accessSecret = properties.getProperty("accessSecret");

        } catch (Exception e) {
            //TODO: handle exception
        }

        ConfigurationBuilder cb = new ConfigurationBuilder();

        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessSecret);
        TwitterFactory tf = new TwitterFactory(cb.build());

        // TwitterFactory tf = new TwitterFactory(Configuration.getConfigurationBuilder().build());

        List<String> hashtags = Arrays.asList("#Vaccinated", "#Covid-19", "#VaccineMandate", "#CovidHoax",
                "#FuckVaccines", "#Vaxxed", "#MicrochipVaccine", "#GatesVaccine", "#NoVaccine", "#GetVaccinated",
                "#Booster");
        // twitter4j.Twitter twitter = tf.getInstance();
        Twitter twitter = TwitterFactory.getSingleton();
        Query query = new Query(hashtags.toString());
        QueryResult result = twitter.search(query);

        List<Status> status = result.getTweets();
        for (Status s : status) {
            sweproject.Tweet tweet = new sweproject.Tweet(s.getId(), s.getUser().getScreenName(), s.getText(), s.getRetweetCount(),
                    s.getCreatedAt());
            writer(tweet);
        }

        //Stream tweets

        sweproject.TwitterListener tl = new sweproject.TwitterListener();
        sweproject.TwitterStreamRunner tsr = new sweproject.TwitterStreamRunner(cb, tl);
        tsr.start();
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

}