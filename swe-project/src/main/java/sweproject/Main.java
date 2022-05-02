package sweproject;

import sweproject.graph.sprint3.GraphIO;
import sweproject.graph.sprint5.WriteHashtags;
import sweproject.graph.sprint6.WriteHashtagGraph;
import sweproject.graph.sprint7.WriteAnalysis;
import twitter4j.TwitterException;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, TwitterException {

        boolean incomplete = true;

        while (incomplete) {
            Scanner scn = new Scanner(System.in);
            System.out.println("Press 0 to exit program \n" +
                    "OR 1 to access sprint 1   (Collect Old Tweets)\n" +
                    "OR 2 to access sprint 2   (Stream Tweets) \n" +
                    "OR 3 to access sprint 3   (Retweet Graph) \n" +
                    "OR 4 to access sprint 4/5 (Evangelists & Hashtag Stances) \n" +
                    "OR 6 to access sprint 6   (Hashtag Breakdown/Gists)\n" +
                    "OR 7 to access sprint 7   (User Psychological Profiles) \n" +
                    "OR 8 to access sprint 8   (Gephi/Graph Visualization)");

            int choice = scn.nextInt();

            if (choice == 0) {
                incomplete = false;
            } else if (choice == 1) {
                Configuration.collectTweets();
            } else if (choice == 2) {
                Configuration.configureStream();
            } else if (choice == 3) {
                GraphIO.graphIO();
            } else if (choice == 4) {
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
