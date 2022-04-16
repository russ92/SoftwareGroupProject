package sweproject.graph.sprint5;

import sweproject.GetProperties;
import sweproject.graph.sprint3.TwitterGraph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;

public class Hashtags {

    public static TwitterGraph Read_Hashtags(){
        GetProperties prop = new GetProperties();

        TwitterGraph hashtagGraph = new TwitterGraph();
        try{
            BufferedReader buf = new BufferedReader(new FileReader(prop.getGraphFilepath()));
            String lineJustFetched;

            while(true){
                lineJustFetched = buf.readLine();
                if(lineJustFetched == null){
                    break;
                }else{
                    String[] lineIn = lineJustFetched.split("\t");

                    // Using the provided data
                    // if (lineIn.length == 3 && lineIn[2].startsWith("RT @")) {

                    // Using the data we collected.
                    // if (lineIn.length == 5 && lineIn[2].startsWith("RT @")) {

                    if (lineIn.length == 3 && lineIn[2].contains("#")) {
                        String user = lineIn[1].trim();
                        String text = lineIn[2].replace("\n", "").replace("\t", " ");
                        String[] hashtags = text.split(" ");

                        for (String h : hashtags) {
                            if (h.contains("#")) {
                                hashtagGraph.addArc(user, h, 1);
                            }
                        }
                    }
                }
            }

            buf.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return hashtagGraph;
    }

    //ToDo: Assign mean stances for hashtags from user
//
//    public static Map<String, Integer> assignHashtagStances(){
//
//    }

    //ToDo:

}
