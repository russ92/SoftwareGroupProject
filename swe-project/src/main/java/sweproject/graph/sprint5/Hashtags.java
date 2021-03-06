package sweproject.graph.sprint5;

import sweproject.GetProperties;
import sweproject.graph.sprint3.Reader;
import sweproject.graph.sprint3.TwitterGraph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
                        String text = lineIn[2].replace("\n", " ").replace("\t", " ").replace(",", " ");
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

    public static Map<String, Integer> assignHashtagStances(){
        System.out.println("Creating graph...");
        TwitterGraph graph = Read_Hashtags();

        Map<String, Map<String, Integer>> map = graph.getEdges();
        System.out.println("Getting hashtags...");

        Map<String, Integer> users = Reader.Read_Stances();

        ConcurrentHashMap<String, Integer> hashtagMap = new ConcurrentHashMap<>();

        for (String u : map.keySet()){
            for (String h : map.get(u).keySet()) {
                if(users.containsKey(u)) {
                    int stance = users.get(u);
                    if(hashtagMap.containsKey(h)) {
                        hashtagMap.put(h, hashtagMap.get(h) + stance);
                    } else {
                        hashtagMap.put(h, stance);
                    }
                }
            }
        }

        hashtagMap.replaceAll((h, v) -> v / (graph.getTotalTimesRetweeted(h)));

        return hashtagMap;
    }

    public static Map<String, Integer> assignHashStances() {

        System.out.println("Creating graph...");
        TwitterGraph hashtags = Hashtags.Read_Hashtags();
        Map<String, Map<String, Integer>> mapHashtags = hashtags.getEdges();

        System.out.println("Getting hashtag stances...");
        Map<String, Integer> stances = Reader.Read_StancesHashtags();

        System.out.println("Getting users who don't have a stance...");
        ConcurrentHashMap<String, Integer> hashMap = new ConcurrentHashMap<>();

        for (String unassigned : mapHashtags.keySet()) {
            if(!stances.containsKey(unassigned)) {
                hashMap.put(unassigned, 0);
            }
        }

        for (String u : hashMap.keySet()){
            if(mapHashtags.containsKey(u)) {
                for (String h : mapHashtags.get(u).keySet()) {
                    if(stances.containsKey(h)) {
                        int stance = stances.get(h);
                        if (hashMap.containsKey(h)) {
                            hashMap.put(u, (hashMap.get(u) + stance));
                        } else {
                            hashMap.put(u, stance);
                        }
                    }
                }
            }
        }

        hashMap.replaceAll((h, v) -> v / (hashtags.getTotalRetweets(h)));

        return hashMap;
    }
}
