import twitter4j.TwitterException;
import java.io.IOException;
import java.io.PrintStream;

public class Driver {
    private static PrintStream consolePrint;

    public static void main(String[] args) throws TwitterException {

        Twitterer twitterer = new Twitterer(consolePrint);
        String[] hashtags = { "Covid-19", "Vaccine", "CovidHoax", "Vaccinated" };
        twitterer.queryHashtags(hashtags);

    }
}