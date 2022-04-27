package sweproject.graph.sprint3;

import sweproject.GetProperties;
import sweproject.graph.sprint4.Evangelists;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Reader {

    public static TwitterGraph Read_Tweets() {
        GetProperties prop = new GetProperties();

        TwitterGraph tweetsGraph = new TwitterGraph();
        try {
            BufferedReader buf = new BufferedReader(new FileReader(prop.getGraphFilepath()));
            String lineJustFetched;

            while (true) {
                lineJustFetched = buf.readLine();
                if (lineJustFetched == null) {
                    break;
                } else {
                    String[] lineIn = lineJustFetched.split("\t");

                    // Check to see if the tweet is a Retweet

                    // Using the provided data
                    // if (lineIn.length == 3 && lineIn[2].startsWith("RT @")) {

                    // Using the data we collected.
                    // if (lineIn.length == 5 && lineIn[2].startsWith("RT @")) {

                    if (lineIn.length == 3 && lineIn[2].startsWith("RT @")) {
                        String user1 = lineIn[1];
                        String user2 = lineIn[2].substring(lineIn[2].indexOf("@"), lineIn[2].indexOf(":"));
                        tweetsGraph.addArc(user1, user2, 1);
                    }
                }
            }

            buf.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tweetsGraph;
    }

    public static List<Evangelists> Read_Angels() {
        GetProperties prop = new GetProperties();
        List<Evangelists> angels = new ArrayList<>();

        try {
            BufferedReader buf = new BufferedReader(new FileReader(prop.getAngelFilepath()));
            String lineJustFetched;

            while (true) {
                lineJustFetched = buf.readLine();
                if (lineJustFetched == null) {
                    break;
                } else {
                    String[] lineIn = lineJustFetched.split("\t");

                    if (lineIn.length == 3 && lineIn[0].startsWith("@")) {
                        String source = lineIn[0];
                        int numRetweets = Integer.parseInt(lineIn[1]);
                        int stance = Integer.parseInt(lineIn[2]);
                        angels.add(new Evangelists(source, numRetweets, stance));
                    }
                }
            }

            buf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return angels;
    }

    public static TwitterGraph Read_HashMap() {
        GetProperties prop = new GetProperties();
        TwitterGraph graph = new TwitterGraph();

        try {
            BufferedReader buf = new BufferedReader(new FileReader(prop.getHashMapFilepath()));
            String lineJustFetched = null;

            while (true) {
                lineJustFetched = buf.readLine();
                if (lineJustFetched == null) {
                    break;
                } else {
                    String[] lineIn = lineJustFetched.split("\t");
                    String source = lineIn[0];

                    for (int i = 1; i < lineIn.length; i++) {
                        String[] retweet = lineIn[i].split(":");
                        int numRetweets = Integer.parseInt(retweet[1]);
                        String destination = retweet[0];

                        graph.addArc(source, destination, numRetweets);

                    }
                }
            }

            buf.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return graph;
    }
<<<<<<< HEAD
=======
<<<<<<< Updated upstream
=======
>>>>>>> parent of 7a66f5a (Revert "Non-Working Build!!!!!!!!!!")


    public static Map<String, Integer> Read_Stances() {
        GetProperties prop = new GetProperties();
        Map<String, Integer> stances = new HashMap<>();

<<<<<<< HEAD
        try{
            BufferedReader buf = new BufferedReader(new FileReader(prop.getStancesFilepath()));
            String lineJustFetched = null;

            while(true){
                lineJustFetched = buf.readLine();
                if(lineJustFetched == null){
                    break;
                }else{
                    String[] lineIn = lineJustFetched.split("\t");
                    if(lineIn.length == 3) {
=======
        try {
            BufferedReader buf = new BufferedReader(new FileReader(prop.getStancesFilepath()));
            String lineJustFetched = null;

            while (true) {
                lineJustFetched = buf.readLine();
                if (lineJustFetched == null) {
                    break;
                } else {
                    String[] lineIn = lineJustFetched.split("\t");
                    if (lineIn.length == 3) {
>>>>>>> parent of 7a66f5a (Revert "Non-Working Build!!!!!!!!!!")
                        String user = lineIn[0];
                        int stance = Integer.parseInt(lineIn[2]);
                        stances.put(user, stance);
                    }
                }
            }

            buf.close();

<<<<<<< HEAD
        }catch(Exception e){
=======
        } catch (Exception e) {
>>>>>>> parent of 7a66f5a (Revert "Non-Working Build!!!!!!!!!!")
            e.printStackTrace();
        }
        return stances;
    }
<<<<<<< HEAD
=======

>>>>>>> parent of 7a66f5a (Revert "Non-Working Build!!!!!!!!!!")
    public static Map<String, Integer> Read_StancesHashtags() {
        GetProperties prop = new GetProperties();
        Map<String, Integer> stances = new HashMap<>();

<<<<<<< HEAD
        try{
            BufferedReader buf = new BufferedReader(new FileReader(prop.getHashtagFilepath()));
            String lineJustFetched = null;

            while(true){
                lineJustFetched = buf.readLine();
                if(lineJustFetched == null){
                    break;
                }else{
                    String[] lineIn = lineJustFetched.split("\t");
                    if(lineIn.length == 2) {
=======
        try {
            BufferedReader buf = new BufferedReader(new FileReader(prop.getHashtagFilepath()));
            String lineJustFetched = null;

            while (true) {
                lineJustFetched = buf.readLine();
                if (lineJustFetched == null) {
                    break;
                } else {
                    String[] lineIn = lineJustFetched.split("\t");
                    if (lineIn.length == 2) {
>>>>>>> parent of 7a66f5a (Revert "Non-Working Build!!!!!!!!!!")
                        String hashtag = lineIn[0];
                        int stance = Integer.parseInt(lineIn[1]);
                        stances.put(hashtag, stance);
                    }
                }
            }

            buf.close();

<<<<<<< HEAD
        }catch(Exception e){
=======
        } catch (Exception e) {
>>>>>>> parent of 7a66f5a (Revert "Non-Working Build!!!!!!!!!!")
            e.printStackTrace();
        }
        return stances;
    }
<<<<<<< HEAD
=======
>>>>>>> Stashed changes
>>>>>>> parent of 7a66f5a (Revert "Non-Working Build!!!!!!!!!!")
}