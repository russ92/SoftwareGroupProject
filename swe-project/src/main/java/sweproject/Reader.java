package sweproject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reader {

    public static TwitterGraph Read_Tweets(String filename) throws FileNotFoundException {

        TwitterGraph tweetsGraph = new TwitterGraph();
        try{
            BufferedReader buf = new BufferedReader(new FileReader(filename));
            String lineJustFetched = null;

            while(true){
                lineJustFetched = buf.readLine();
                if(lineJustFetched == null){
                    break;
                }else{
                    String[] lineIn = lineJustFetched.split("\t");

                    // Check to see if the tweet is a Retweet

                    // Using the provided data
                    // if (lineIn.length == 3 && lineIn[2].startsWith("RT @")) {

                    // Using the data we collected.
                    if (lineIn.length == 5 && lineIn[2].startsWith("RT @")) {
                        String user1 = lineIn[1];
                        String user2 = lineIn[2].substring(lineIn[2].indexOf("@"), lineIn[2].indexOf(":"));
                        tweetsGraph.addArc(user1, user2, 1);
                    }
                }
            }

            buf.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return tweetsGraph;
    }

    public static void main(String[] args) throws FileNotFoundException {

        String file = "swe-project\\VaxData\\vaxTweets.txt";

        System.out.println("Creating graph...");
        TwitterGraph graph = Reader.Read_Tweets(file);
        System.out.println("Creating list of angels...");
        Map<String, Map<String, Integer>> mapGraph = graph.getGraphHashMap();


        System.out.println(mapGraph);
        mapGraph.forEach((K,V)->{                        // mapofmaps entries
            System.out.print("\n" + K + " = ");
            V.forEach((X,Y)->{                     // inner Hashmap enteries
                System.out.print("["+X+" "+Y+"] ");       // print key and value of inner Hashmap
            });
        });

    }
}