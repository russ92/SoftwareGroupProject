package sweproject;

import twitter4j.*;
import twitter4j.Twitter;
import twitter4j.conf.ConfigurationBuilder;

import javax.jws.Oneway;
import java.io.*;
import java.util.*;

public class Configuration {


    private static final String USER_DATA = "VaxData/vax tweets users.txt";
    private static final String TWEET_DATA = "VaxData/vax tweets.txt";
    private static final String[] HASHTAGS = {"Vaccinated", "Covid-19", "VaccineMandate", "CovidHoax",
            "FuckVaccines", "Vaxxed", "MicrochipVaccine", "GatesVaccine", "NoVaccine", "GetVaccinated",
            "Booster", "Omicron", "LongCovid", "CovidIsNotOver", "COVIDisAirborne", "WearAMask", "GetVaxed", "covidiots",
            "CovidScam", "ArrestFaucci", "VaccineSideEffects", "VaccineDeaths", "VaccineInjuries", "NoCovid",
            "GetTheDamnVaccine", "CovidIsOver", "CoronaHoax", "CoronaVirus", "OmicronBeGone", "CovidFraud",
            "SayNoToPoisonVaccines", "fuckFauci"};

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

        GetProperties prop = new GetProperties();

        ConfigurationBuilder cb = new ConfigurationBuilder();
        TwitterFactory tf = new TwitterFactory(cb.build());

//         This way of configuring TwitterFactory not working...
//         TwitterFactory tf = new TwitterFactory(Configuration.getConfigurationBuilder().build());
//         twitter4j.Twitter twitter = tf.getInstance();

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
        sweproject.TwitterListener tl = new sweproject.TwitterListener();
        sweproject.TwitterStreamRunner tsr = new sweproject.TwitterStreamRunner(cb, tl);
        try {
            tsr.start();
        } catch (Exception e) {
            tsr.shutdown();
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
}