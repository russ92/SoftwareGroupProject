package sweproject;

import sweproject.graph.sprint2.TwitterListener;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Locale;

public class StoredData {

    // Hashtable to track the usernames in the dataset.
    private static Hashtable<Long, Tweet> storedTweets;

    public static Hashtable<Long, Tweet> getStoredTweets() {
        return storedTweets;
    }

    public static void setStoredTweets(Hashtable<Long, Tweet> storedTweets) {
        StoredData.storedTweets = storedTweets;
    }

    public static Hashtable<Long, Tweet> readStoredTweets(){
        GetProperties prop = new GetProperties();

        Hashtable<Long, Tweet> tweets = new Hashtable<>();
        try{
            //BufferedReader buf = new BufferedReader(new FileReader("swe-project\\VaxData\\provided\\vax tweets.txt"));
            BufferedReader buf = new BufferedReader(new FileReader(prop.getTweetFilepath()));
            String lineJustFetched;

            DateFormat df = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);

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
                        Date date =  df.parse(lineIn[4]);
                        Tweet tweet = new Tweet(Long.parseLong(lineIn[0]), name, lineIn[2], Integer.parseInt(lineIn[3]), date);
                        tweets.put(id, tweet);
                    }
                }
            }

            buf.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return tweets;
    }

    // Hashtable to track the usernames in the dataset.
    private static Hashtable<String, User> storedUsers;

    public static Hashtable<String, User> getStoredUsers() {
        return storedUsers;
    }
    public static void setStoredUsers(Hashtable<String, User> storedUsers) {
        StoredData.storedUsers = storedUsers;
    }

    public static Hashtable<String, User> readStoredUsers(){
        GetProperties prop = new GetProperties();

        Hashtable<String, User> users = new Hashtable<>();
        try{
            BufferedReader buf = new BufferedReader(new FileReader(prop.getUserFilepath()));
            String lineJustFetched;

            int count = 0;
            while(true){
                lineJustFetched = buf.readLine();
                if(lineJustFetched == null){
                    break;
                }else{
                    String[] lineIn = lineJustFetched.split("\t");
                    if (lineIn.length == 4 && lineIn[0].startsWith("@")) {
                        String name = lineIn[0];
                        int followers = Integer.parseInt(lineIn[3]);
                        User user = new User(name.substring(1), lineIn[1], lineIn[2], followers);
                        users.put(name, user);
                    }
                }
            }

            buf.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return users;
    }
}
