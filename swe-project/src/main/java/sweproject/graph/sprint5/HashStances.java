package sweproject.graph.sprint5;

import sweproject.graph.sprint3.Reader;
import sweproject.graph.sprint3.TwitterGraph;
import sweproject.graph.sprint4.Evangelists;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HashStances {
    public static Map<String, Integer> assignHashStances() {
        System.out.println("Creating graph...");
        TwitterGraph graph = Reader.Read_Tweets();
        Map<String, Map<String, Integer>> map = graph.getEdges();
        System.out.println("Getting angels (most influential users)...");

        List<Evangelists> angels = Reader.Read_Angels();
        ConcurrentHashMap<String, Integer> angelMap = new ConcurrentHashMap<>();

        for (Evangelists n : angels) {
            if(n.getStance()==0)
                angelMap.put(n.getAngel(), n.getStance());
        }
        
        System.out.println(angelMap.size());
        int iterations = 0;

        while (iterations < 1) {
            System.out.println("Iteration " + (iterations + 1));

            for (String a : angelMap.keySet()) {
                if (map.containsKey(a)) {
                    for (String u : map.get(a).keySet()) {
                        int stance = angelMap.get(a);
                        if (angelMap.containsKey(u)) {
                            angelMap.put(u, (angelMap.get(u) + stance) / 2);
                        } else {
                            angelMap.put(u, stance);
                        }
                    }
                }
            }
            angelMap.replaceAll((a, v) -> v / (graph.getTotalTimesRetweeted(a)));
            iterations++;
        }
        return null;
    }
}
