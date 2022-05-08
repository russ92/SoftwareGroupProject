package sweproject;

import sweproject.graph.sprint3.GraphIO;
import sweproject.graph.sprint5.WriteHashtags;
import sweproject.graph.sprint6.WriteHashtagGraph;
import sweproject.graph.sprint7.WriteAnalysis;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        boolean incomplete = true;

        while (incomplete) {
            Scanner scn = new Scanner(System.in);
            System.out.println("Press 0 to exit program \n" +
                    "OR 1 to access sprint 1   (Collect Old Tweets)\n" +
                    "OR 2 to access sprint 2   (Stream Tweets) \n" +
                    "OR 3 to access sprint 3/4 (Retweet Graph & Evangelists) \n" +
                    "OR 5 to access sprint 5   (Hashtag Stances) \n" +
                    "OR 6 to access sprint 6   (Hashtag Breakdown/Gists)\n" +
                    "OR 7 to access sprint 7   (User Psychological Profiles) \n" +
                    "OR 8 to access sprint 8   (Gephi/Graph Visualization)");

            int choice = scn.nextInt();

            if (choice == 0) {
                incomplete = false;
            } else if (choice == 1) {
                CollectData.collectTweets();
            } else if (choice == 2) {
                CollectData.configureStream();
                incomplete = false;
            } else if (choice == 3) {
                GraphIO.graphIO();
            } else if (choice == 5) {
                WriteHashtags.writeHashtags();
            } else if (choice == 6) {
                WriteHashtagGraph.writeHashtagGraph();
            }else if (choice == 7) {
                WriteAnalysis.psychologicalProfiles();
            }else if (choice == 8) {
                incomplete = false;
            }
        }
    }
}
