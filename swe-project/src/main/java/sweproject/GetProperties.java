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

    public String getTweetFilepath() { return myProperties.getProperty("tweets");}

    public String getUserFilepath() { return myProperties.getProperty("users");}

    public String getGraphFilepath() { return myProperties.getProperty("graph");}

    public String getAngelFilepath() {
        return myProperties.getProperty("angels");
    }

    public String getHashMapFilepath() {
        return myProperties.getProperty("hashmap");
    }

    public String getUserToHashtagFilepath() {
        return myProperties.getProperty("hashtagToUser");
    }

    public String getHashtagToUserFilepath() {
        return myProperties.getProperty("userToHashtag");
    }

    public String getPrintToFilepath() {
        return myProperties.getProperty("print");
    }

}