package sweproject.graph.sprint5;

import sweproject.GetProperties;
import sweproject.graph.sprint4.TwitterGraph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Hashtags {

    public static TwitterGraph Read_Hashtags(){
        GetProperties prop = new GetProperties();

        TwitterGraph tweetsGraph = new TwitterGraph();
        try{
            BufferedReader buf = new BufferedReader(new FileReader(prop.getGraphFilepath()));
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
                    // if (lineIn.length == 5 && lineIn[2].startsWith("RT @")) {

                    if (lineIn.length == 3 && lineIn[2].contains("#")) {
                        String tweetHashtags = lineIn[2].substring(lineIn[2].indexOf("#"));
                        String[] hashtags = tweetHashtags.split(" ");
                        System.out.println(Arrays.toString(hashtags));
                        String user = lineIn[1];
                            for (String hashtag : hashtags) {
                                System.out.println(hashtag);
                                tweetsGraph.addArc(user, hashtag.trim(), 1);
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

    public static void main (String[] args){
        System.out.println(Hashtags.Read_Hashtags().getEdges());
    }
}
