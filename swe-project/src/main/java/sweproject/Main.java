package sweproject;

import sweproject.graph.sprint3.GraphIO;
import sweproject.graph.sprint5.WriteHashtags;
import sweproject.graph.sprint6.WriteHashtagGraph;
import twitter4j.TwitterException;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, TwitterException {

        boolean incomplete = true;

        while (incomplete) {
            Scanner scn = new Scanner(System.in);
            System.out.println("Press 1 to exit program \n" +
                    "OR 2 to access sprint 1&2 \n" +
                    "OR 3 to access sprint 3 \n" +
                    "OR 4 to access sprint 4&5 \n" +
                    "OR 6 to access sprint 6 \n" +
                    "OR 7 to access sprint 7:");

            int choice = scn.nextInt();

            if (choice == 1) {
                incomplete = false;
            } else if (choice == 2) {
                Configuration.configuration();
            } else if (choice == 3) {
                GraphIO.graphIO();
            } else if (choice == 4) {
                WriteHashtags.writeHashtags();
            } else if (choice == 6) {
                WriteHashtagGraph.writeHashtagGraph();
            }
        }
    }
}
