package sweproject;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Stances {

    public static Map<String, Integer> assignStances(){
        System.out.println("Creating graph...");
        TwitterGraph graph = Reader.Read_Tweets();
        Map<String, Map<String, Integer>> map = graph.getEdges();
        System.out.println("Getting angels (most influential users)...");

        List<Evangelists> angels = Reader.Read_Angels();
        ConcurrentHashMap<String, Integer> angelMap = new ConcurrentHashMap<>();

        for (Evangelists n : angels) {
            angelMap.put(n.getAngel(), n.getStance());
        }

        int iterations = 0;

        while (iterations < 15) {
            System.out.println("Iteration " + (iterations+1));

            for (String a : angelMap.keySet()){
                if (map.containsKey(a)){
                    for (String g : map.get(a).keySet()) {
                        int stance = angelMap.get(a);

                        angelMap.put(g, stance);
                        System.out.println(g + "\t" + stance);
                    }

                }
            }
            iterations++;
        }

        return angelMap;
    }

    public static void analysisStances(){
//        Reader reader = new Reader();
//        reader.setAngelsFile("swe-project/VaxData/Graphs/assignedStances.txt");
//        List<Evangelists> angels = reader.Read_Angels();

        Map<String, Integer> angels = Stances.assignStances();

        int countAnti = 0;
        int countPro = 0;

        for (String n : angels.keySet()) {
            if(angels.get(n) == -1000){
                countAnti++;
            } else if(angels.get(n) == 1000){
                countPro++;
            }
        }

        System.out.println("Anti stances: " + countAnti + "\nPro stances: " + countPro);
    }
}
