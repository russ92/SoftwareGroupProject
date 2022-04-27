package sweproject.graph.sprint4;

import sweproject.graph.sprint3.Reader;
import sweproject.graph.sprint3.TwitterGraph;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Stances {

    public static Map<String, Integer> assignStances() {
        System.out.println("Creating graph...");
        TwitterGraph graph = Reader.Read_Tweets();
        Map<String, Map<String, Integer>> map = graph.getEdges();
        System.out.println("Getting angels (most influential users)...");

        List<Evangelists> angels = Reader.Read_Angels();
        ConcurrentHashMap<String, Integer> angelMap = new ConcurrentHashMap<>();

        for (Evangelists n : angels) {
            angelMap.put(n.getAngel(), n.getStance());
        }
        System.out.println(angelMap.size());
        int iterations = 0;

        while (iterations < 15) {
            System.out.println("Iteration " + (iterations + 1));

<<<<<<< Updated upstream
            for (String a : angelMap.keySet()){
                if(map.containsKey(a)) {
                    for (String u : map.get(a).keySet()) {
                        int stance = angelMap.get(a);
<<<<<<< HEAD
                        if (angelMap.containsKey(u)) {
                            angelMap.put(u, (angelMap.get(u) + stance)/2);
                        } else {
                            angelMap.put(u, stance);
                        }
=======
                        angelMap.put(g, stance);
=======
            for (String a : angelMap.keySet()) {
                if (map.containsKey(a)) {
                    for (String u : map.get(a).keySet()) {
                        int stance = angelMap.get(a);
                        if (angelMap.containsKey(u)) {
                            angelMap.put(u, (angelMap.get(u) + stance) / 2);
                        } else {
                            angelMap.put(u, stance);
                        }
>>>>>>> Stashed changes
>>>>>>> parent of 7a66f5a (Revert "Non-Working Build!!!!!!!!!!")
                    }
                }
            }
            iterations++;
        }
        angelMap.replaceAll((a, v) -> v / (graph.getTotalTimesRetweeted(a)));

        return angelMap;
    }

    public static void analysisStances() {
        Map<String, Integer> angels = Stances.assignStances();

        int countAnti = 0;
        int countPro = 0;

        for (String n : angels.keySet()) {
            if (angels.get(n) == -1000) {
                countAnti++;
            } else if (angels.get(n) == 1000) {
                countPro++;
            }
        }

        System.out.println("Anti stances: " + countAnti + "\nPro stances: " + countPro);
    }
<<<<<<< HEAD

//    public static void main(String [] args){
//        Map<String, Integer> n = Stances.assignStances();
//        System.out.println(n + "\n" + n.size() + " size" );
//        int max = 0;
//        int count = 0;
//        int avg = 0;
//        for(String u: n.keySet()){
//            if(n.get(u) > max){
//                max = n.get(u);
//            }
//            if(n.get(u) != 0){
//                count++;
//            }
//            avg += n.get(u);
//        }
//        System.out.println("Max = " + max);
//        System.out.println("Avg = " + avg/n.size());
//        System.out.println("Count '0' = " + count);
//        System.out.println("Size: " + n.size());
//    }
=======
<<<<<<< Updated upstream
=======

    public static void Stances() {
        Map<String, Integer> n = Stances.assignStances();
        System.out.println(n + "\n" + n.size() + " size");
        int max = 0;
        int count = 0;
        int avg = 0;
        for (String u : n.keySet()) {
            if (n.get(u) > max) {
                max = n.get(u);
            }
            if (n.get(u) != 0) {
                count++;
            }
            avg += n.get(u);
        }
        System.out.println("Max = " + max);
        System.out.println("Avg = " + avg / n.size());
        System.out.println("Count '0' = " + count);
        System.out.println("Size: " + n.size());
    }
>>>>>>> Stashed changes
>>>>>>> parent of 7a66f5a (Revert "Non-Working Build!!!!!!!!!!")
}
