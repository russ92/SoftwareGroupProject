package sweproject.graph.sprint6;

import sweproject.graph.sprint3.GraphIO;

import java.io.*;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class WriteHashtagGraph {

    public static void writeHashtagGraph(){
        System.out.println("LEXICON");

        boolean incomplete = true;

        while (incomplete) {
            Scanner scn = new Scanner(System.in);
            System.out.println("1 to write the given lexicon graph to a text file \n" +
                    "OR 2 to write the lexicon of hashtags collected as a graph to text file:");

            int choice = scn.nextInt();

            if (choice == 1) {
                System.out.println("Creating graph...");
                Map<String, Map<String, Set<String>>> map = HashtagAnalysis.readLexiconToHashmap().getEdges();
                System.out.println("Writing graph...");
                writeToFile(map);
                incomplete = false;
            } else if (choice== 2) {
                System.out.println("Creating graph...");
                Map<String, Map<String, Set<String>>> map = HashtagAnalysis.hashtagSplitAsGraph().getEdges();
                System.out.println("Writing graph...");
                writeToFile(map);
                incomplete = false;
            }else {
                System.out.println("PLEASE ENTER 1 OR 2");
            }
        }
    }


    public static void writeToFile(Map<String, Map<String, Set<String>>> map) {
        String path = GraphIO.fileName(1);
        File graph = new File(path);
        BufferedWriter bf = null;
        try {
            System.out.println("try");
            bf = new BufferedWriter(new FileWriter(graph));
            // String to be written to file.
            StringBuilder toFile = new StringBuilder();
            map.forEach((K, V)->{
                toFile.append("\n").append(K).append("\t");
                V.forEach((X,Y)-> toFile.append(X).append(":").append(Y).append("\t"));
            });
            bf.write(toFile.toString().trim());
            bf.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert bf != null;
                bf.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
