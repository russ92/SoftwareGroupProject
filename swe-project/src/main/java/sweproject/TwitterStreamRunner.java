package sweproject;

import org.springframework.stereotype.Component;
import twitter4j.FilterQuery;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

import javax.annotation.PreDestroy;
import java.util.Arrays;

@Component
public class TwitterStreamRunner implements StreamRunner {

    private static final String[] HASHTAGS = {
            "Vaccinated",
            "Covid-19",
            "VaccineMandate",
            "CovidHoax",
            "FuckVaccines",
            "Vaxxed",
            "MicrochipVaccine",
            "GatesVaccine",
            "NoVaccine",
            "GetVaccinated",
            "Booster",
            "Omicron",
            "LongCovid",
            "CovidIsNotOver",
            "COVIDisAirborne",
            "WearAMask",
            "GetVaxed",
            "covidiots",
            "CovidScam",
            "ArrestFaucci",
            "VaccineSideEffects",
            "VaccineDeaths",
            "VaccineInjuries",
            "NoCovid",
            "GetTheDamnVaccine",
            "CovidIsOver",
            "CoronaHoax",
            "CoronaVirus",
            "OmicronBeGone",
            "CovidFraud",
            "SayNoToPoisonVaccines",
            "fuckFauci"
    };

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
        twitterStream = new TwitterStreamFactory().getInstance();
        twitterStream.addListener(twitterListener);

        FilterQuery filterQuery = new FilterQuery(HASHTAGS);
        twitterStream.filter(filterQuery);
        System.out.println("Streaming tweets from twitter using the hashtags: " + "\n" + Arrays.toString(HASHTAGS));
    }

    @PreDestroy
    public void shutdown() {
        if (twitterStream != null) {
            twitterStream.shutdown();
        }
    }
}