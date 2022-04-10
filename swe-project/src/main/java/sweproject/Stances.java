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
        int iterations = 1;

        while (iterations < 12) {
            System.out.println(angels.size());
            for (Evangelists a : angels) {
                ArrayList<String> names = new ArrayList<>();

                // List of angel names whose stances won't be changed.
                for (Evangelists n : angels) {
                    names.add(n.getAngel());
                }

                if (map.containsKey(a.getAngel())) {
                    for (String g : map.get(a.getAngel()).keySet()) {
                        int numRetweets = graph.getTotalTimesRetweeted(g);
                        int stance = a.getStance();
                        Evangelists newAngel = new Evangelists(g, numRetweets, stance);

                        // Check if newAngel already has a stance
                        if (!names.contains(g) && !everyoneElse.contains(newAngel)) {
                            // System.out.println(newAngel);
                            everyoneElse.add(newAngel);
                        }
                    }

                }
            }

            System.out.println("Iteration " + iterations);

            // Remove duplicates from the list of angels
            List<Evangelists> everyoneElseCopy = new ArrayList<>(everyoneElse);
            everyoneElseCopy.removeAll(angels);
            angels.addAll(everyoneElseCopy);
            iterations++;
        }

        return angels;
    }

    public static void main(String [] args){
        System.out.println(Stances.assignStances().size());

        // Output for 12 iterations
//        Creating graph...
//        Getting angels (most influential users)...
//        100
//        Iteration 1
//        1587
//        Iteration 2
//        5236
//        Iteration 3
//        12723
//        Iteration 4
//        20784
//        Iteration 5
//        25790
//        Iteration 6
//        29428
//        Iteration 7
//        30689
//        Iteration 8
//        31166
//        Iteration 9
//        31301
//        Iteration 10
//        31328
//        Iteration 11
//        31376
//        Iteration 12
//        31378
    }
}
