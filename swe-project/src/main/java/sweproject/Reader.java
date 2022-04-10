package sweproject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Reader {

    public static TwitterGraph Read_Tweets(String filename){

        TwitterGraph tweetsGraph = new TwitterGraph();
        try{
            BufferedReader buf = new BufferedReader(new FileReader(filename));
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

    public static List<Evangelists> Read_Angels(String filename){

        List<Evangelists> angels = new ArrayList<>();

        try{
            BufferedReader buf = new BufferedReader(new FileReader(filename));
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
                        int stance = Integer.parseInt(lineIn[1]);
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

    public static void main(String[] args) throws FileNotFoundException {

        String file = "swe-project\\VaxData\\vax tweets.txt";

//        System.out.println("Creating graph...");
//        TwitterGraph graph = Reader.Read_Tweets(file);
//        System.out.println("Creating list of angels...");
//        Map<String, Map<String, Integer>> mapGraph = graph.invert();
//        List<Evangelists> angels = graph.getInvertedEvangelists();
        List<Evangelists> angels = Read_Angels("swe-project\\VaxData\\Graphs\\angels.txt");
        System.out.println(angels);

//        mapGraph.forEach((K,V)->{
//            System.out.print("\n" + K + "\t");
//            V.forEach((X,Y)-> System.out.print(X+":"+Y+"\t"));
//        });

    }
}