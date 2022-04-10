package sweproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Stances {

    public static List<Evangelists> assignStances(){
        System.out.println("Creating graph...");
        TwitterGraph graph = Reader.Read_Tweets();
        Map<String, Map<String, Integer>> map = graph.getEdges();
        System.out.println("Getting angels (most influential users)...");
        List<Evangelists> angels = Reader.Read_Angels();

        List<Evangelists> everyoneElse = new ArrayList<>();
        int iterations = 0;

        while (iterations <= 1) {
            for (Evangelists a : angels) {
                ArrayList<String> names = new ArrayList<>();
                for (Evangelists n : angels) {
                    names.add(n.getAngel());
                }
                if (map.containsKey(a.getAngel())) {
                    for (String g : map.get(a.getAngel()).keySet()) {
                        int numRetweets = graph.getTotalTimesRetweeted(g);
                        int stance = a.getStance();
                        Evangelists newAngel = new Evangelists(g, numRetweets, stance);

                        if (!names.contains(g)) {
                            System.out.println("Adding new user...\t" + newAngel);
                            everyoneElse.add(newAngel);
                        }
                    }

                }
            }

            angels.addAll(everyoneElse);
            iterations++;
        }

        return angels;
    }

    public static void main(String [] args){
        System.out.println(Stances.assignStances());
    }
}
