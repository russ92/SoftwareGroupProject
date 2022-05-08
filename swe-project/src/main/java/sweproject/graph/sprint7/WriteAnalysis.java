package sweproject.graph.sprint7;

import sweproject.graph.sprint6.WriteHashtagGraph;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class WriteAnalysis {

    public static void psychologicalProfiles() {
        System.out.println("USER PSYCHOLOGICAL PROFILES");
        boolean incomplete = true;

        while (incomplete) {
            Scanner scn = new Scanner(System.in);
            System.out.println(
                    "1 to write the user hashtags references as a graph to a text file: (userHashtagReferenceGraph) \n" +
                            "OR 2 to write the graph that maps each user onto an aggregate representation of their " +
                            "qualities to a text file: (Psychological Profiles) \n" +
                            "OR 3 for dataset probability analysis: "
            );

            int choice = scn.nextInt();

            if (choice == 1) {
                System.out.println("Creating graph...");
                Map<String, Map<String, Set<String>>> map = ReferenceAnalysis.userHashtagReferenceGraph().getEdges();
                System.out.println("Writing graph...");
                WriteHashtagGraph.writeToFile(map);
                incomplete = false;
            } else if (choice == 2) {
                System.out.println("Creating graph...");
                Map<String, Map<String, Set<String>>> map = ReferenceAnalysis.psychologicalProfileGraph().getEdges();
                System.out.println("Writing graph...");
                WriteHashtagGraph.writeToFile(map);
                incomplete = false;
            } else if (choice == 3) {
                System.out.println("Dataset Analysis");
                DatasetProbabilities.probabilitiesDriver();
                System.out.println("Ending...");
                incomplete = false;
            } else {
                System.out.println("PLEASE ENTER 1 OR 2");
            }
        }
    }
}

