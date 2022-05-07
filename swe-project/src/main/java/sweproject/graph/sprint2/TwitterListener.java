package sweproject.graph.sprint2;

import org.springframework.stereotype.Component;
import sweproject.GetProperties;
import sweproject.Tweet;
import sweproject.User;
import twitter4j.Status;
import twitter4j.StatusAdapter;

import java.io.*;
import java.util.Hashtable;

@Component
public class TwitterListener extends StatusAdapter {

    public int count = 1;

    @Override
    public void onStatus(Status s) {

        GetProperties prop = new GetProperties();

        Tweet tweet = new Tweet(s.getId(), s.getUser().getScreenName(), s.getText(), s.getRetweetCount(),
                s.getCreatedAt());
        // Condition for the full text of a retweet without the tweet text finishing with '...'
        if (s.isRetweet()) {
            tweet.setText( "RT @" + s.getRetweetedStatus().getUser().getScreenName() + ": " + s.getRetweetedStatus().getText());
        }

        User user = new User(s.getUser().getScreenName(), s.getUser().getLocation(),
                s.getUser().getDescription(), s.getUser().getFollowersCount());

        System.out.print("Tweet " + count + ":\t\t\t" + tweet + "Tweeted by user:\t" + user + "\n");
        count++;

        // Write to files
        try (FileWriter fw1 = new FileWriter(prop.getTweetFilepath(), true);
             BufferedWriter bw1 = new BufferedWriter(fw1);
             PrintWriter out = new PrintWriter(bw1)) {
            out.print(tweet);
        } catch (Exception e) {
            System.out.println("--- ERROR WRITING TWEET ---\n" + e.getLocalizedMessage());
        }

        //ToDo: Catch any duplicate users before writing them to the file
        //This should work
        if(!storedUsers.containsKey(user.getUserhandle())) {
            storedUsers.put(user.getUserhandle(), user);
            try (FileWriter fw2 = new FileWriter(prop.getUserFilepath(), true);
                 BufferedWriter bw2 = new BufferedWriter(fw2);
                 PrintWriter out = new PrintWriter(bw2)) {
                out.print(user);
            } catch (Exception e) {
                System.out.println("--- ERROR WRITING USER ---\n" + e.getLocalizedMessage());
            }
        }
    }

    // Hashtable to track the usernames in the dataset.
    private static Hashtable<String, User> storedUsers;

    public static Hashtable<String, User> getStoredUsers() {
        return storedUsers;
    }
    public static void setStoredUsers(Hashtable<String, User> storedUsers) {
        TwitterListener.storedUsers = storedUsers;
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

//    public static void main(String[] args){
//        Hashtable<String, Integer> t = readStoredUsers();
//        System.out.println(t.size());
//    }
}