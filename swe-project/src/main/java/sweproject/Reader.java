package sweproject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Reader {

    private static String angelsFile = "VaxData/Graphs/angels.txt";
    public String getAngelsFile(){ return angelsFile; }
    public void setAngelsFile(String file){ angelsFile = file;}

    private static String graphFile = "VaxData/provided/vax tweets.txt";
    public String getGraphFile(){ return graphFile; }
    public void setGraphFile(String file){ graphFile = file;}

    public static TwitterGraph Read_Tweets(){

        TwitterGraph tweetsGraph = new TwitterGraph();
        try{
            BufferedReader buf = new BufferedReader(new FileReader(graphFile));
            String lineJustFetched;

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
                    if (lineIn.length == 3 && lineIn[2].startsWith("RT @")) {
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

    public static List<Evangelists> Read_Angels(){

        List<Evangelists> angels = new ArrayList<>();

        try{
            BufferedReader buf = new BufferedReader(new FileReader(angelsFile));
            String lineJustFetched;
            
            while(true){
                lineJustFetched = buf.readLine();
                if(lineJustFetched == null){
                    break;
                }else{
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
        }catch(Exception e){
            e.printStackTrace();
        }
        return angels;
    }
}