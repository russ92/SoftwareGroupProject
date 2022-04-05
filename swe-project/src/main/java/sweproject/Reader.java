package sweproject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Reader {

    public static TwitterGraph Read_Tweets() throws FileNotFoundException {

        TwitterGraph tweetsGraph = new TwitterGraph();
        try{
            BufferedReader buf = new BufferedReader(new FileReader("swe-project/VaxData/vax tweets.txt"));
            String lineJustFetched = null;

            while(true){
                lineJustFetched = buf.readLine();
                if(lineJustFetched == null){
                    break;
                }else{
                    String[] lineIn = lineJustFetched.split("\t");

                    // Check to see if the tweet is a Retweet
                    if(lineIn[2].contains("RT ")){
                        String user1 = lineIn[1];
                        String user2 = lineIn[2].substring(lineIn[2].indexOf("@"), lineIn[2].indexOf(":"));

                        // Check to see if the user1 node already exists
                        if(tweetsGraph.weightedRetweets.containsKey(user1)) {
                            // Check to see if the user1 and user2 already have an edge, if so increment
                            if(tweetsGraph.doesArcExist(user1, user2)) {
                                int count = tweetsGraph.weightedRetweets.get(user1).get(user2) + 1;
                                tweetsGraph.addArc(user1,user2,count);
                            }
                        }
                        else {
                            tweetsGraph.addArc(user1, user2, 1);
                        }
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

        System.out.println(Read_Tweets().getEdges());

    }
}