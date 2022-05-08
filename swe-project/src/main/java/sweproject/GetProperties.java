package sweproject;

import java.io.*;
import java.util.Arrays;
import java.util.Properties;

public class GetProperties {

    private Properties myProperties;

    public GetProperties() {
        try {

            myProperties = new Properties();
            myProperties.load(getClass().getResourceAsStream("/application.properties"));

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    public String[] getPropertyHashtags() {
        String hashtags = myProperties.getProperty("hashtags");
        return hashtags.split(",");
    }

    public String getTweetFilepath() { return myProperties.getProperty("collectTweets");}

    public String getUserFilepath() { return myProperties.getProperty("collectUsers");}

    public String getGraphFilepath() { return myProperties.getProperty("retweetGraph");}

    public String getAngelFilepath() {
        return myProperties.getProperty("listAngels");
    }

    public String getHashMapFilepath() {
        return myProperties.getProperty("retweetHashmap");
    }

    public String getStancesFilepath() {
        return myProperties.getProperty("stanceGraph");
    }

    public String getUserToHashtagFilepath() {
        return myProperties.getProperty("hashtagToUserGraph");
    }

    public String getHashtagToUserFilepath() {
        return myProperties.getProperty("userToHashtagGraph");
    }

    public String getSprintFolderFilepath() {
        return myProperties.getProperty("sprintOutput");
    }

    public String getPrintToFilepath() {
        return myProperties.getProperty("printOutput");
    }

    public String getLexiconFilepath() { return myProperties.getProperty("lexicon"); }

    public String getHashtagFilepath() { return myProperties.getProperty("hashtagStances"); }

    public String getHashtagReferenceFilepath() { return myProperties.getProperty("profileReferences"); }

    public String getUserStancesFilepath() { return myProperties.getProperty("userStances"); }
}