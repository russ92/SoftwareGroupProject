package sweproject;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import javax.annotation.PreDestroy;
import java.lang.reflect.Array;
import java.util.Arrays;


@Component
public class TwitterStreamRunner implements StreamRunner {

    private static final String[] HASHTAGS = {"Vaccinated", "Covid-19", "VaccineMandate", "CovidHoax",
            "FuckVaccines", "Vaxxed", "MicrochipVaccine", "GatesVaccine", "NoVaccine", "GetVaccinated",
            "Booster"};

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(TwitterListener.class);

    private final ConfigurationBuilder configurationBuilder;

    private final TwitterListener twitterListener;

    private TwitterStream twitterStream;

    public TwitterStreamRunner(ConfigurationBuilder configurationBuilder, TwitterListener twitterListener) {
        this.configurationBuilder = configurationBuilder;
        this.twitterListener = twitterListener;
    }

    @Override
    public void start() throws TwitterException{
        twitterStream = new TwitterStreamFactory().getInstance();
        twitterStream.addListener(twitterListener);

        FilterQuery filterQuery = new FilterQuery(HASHTAGS);
        twitterStream.filter(filterQuery);
        LOG.info("Stream using hashtags: ", Arrays.toString(HASHTAGS));

    }

    @PreDestroy
    public void shutdown(){
        if(twitterStream!=null){
            LOG.info("Shutdown");
            twitterStream.shutdown();
        }
    }
}
