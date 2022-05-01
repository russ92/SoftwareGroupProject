package sweproject.graph.sprint7;

import sweproject.GetProperties;
import java.io.*;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class WriteAnalysis {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("REFERENCES");
        GetProperties prop = new GetProperties();

        // File to be written as a graph
        String file = prop.getGraphFilepath();

        boolean incomplete = true;

        while (incomplete) {
            Scanner scn = new Scanner(System.in);
            System.out.println(
                    "1 to write the user hashtags references as a graph to a text file: (userHashtagReferenceGraph) \n" +
                            "OR 2 to write the graph that maps each user onto an aggregate representation of their " +
                            "qualities to a text file: (graphSprint7): "
            );

            int choice = scn.nextInt();

            if (choice == 1) {
                System.out.println("Creating graph...");
                Map<String, Map<String, Set<String>>> map = ReferenceAnalysis.userHashtagReferenceGraph().getEdges();
                System.out.println("Writing graph...");
                writeToFile(map);
                incomplete = false;
            } else if (choice == 2) {
                System.out.println("Creating graph...");
                Map<String, Map<String, Set<String>>> map = ReferenceAnalysis.graphSprint7().getEdges();
                System.out.println("Writing graph...");
                writeToFile(map);
                incomplete = false;
            } else {
                System.out.println("PLEASE ENTER 1 OR 2");
            }
        }
    }

    public static void writeToFile(Map<String, Map<String, Set<String>>> map) {
        String path = fileName(1);
        File graph = new File(path);
        BufferedWriter bf = null;
        try {
            System.out.println("try");
            bf = new BufferedWriter(new FileWriter(graph));
            // String to be written to file.
            StringBuilder toFile = new StringBuilder();
            map.forEach((K, V)->{
                toFile.append("\n").append(K).append("\t");
                V.forEach((X,Y)->{
                    toFile.append(X).append(":").append(Y).append("\t");
                });
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
    // int passed in so that the code can differentiate between creating a new
    // file and loading a file
    public static String fileName(int rW) {
        GetProperties prop = new GetProperties();
        String name = prop.getSprintFolderFilepath(); // CHANGE THIS PATH IN application.properties (sprint)
        if (rW == 1) {
            boolean exists = true;
            while (exists) {
                System.out.println("Writing to file...");
                Scanner scn = new Scanner(System.in);
                System.out.println("ENTER FILE NAME:");
                //ToDo : Error handling
                // String name isn't reset to null if file already exist,
                // resulting in 'ExistingName.txtNewName.txt' as file's name.
                name = name + scn.nextLine() + ".txt";
                File f = new File(name);
                if (f.exists()) {
                    System.out.println("FILE: " + name + " ALREADY EXISTS");
                    //name = "";
                } else {
                    exists = false;
                }
            }
        } else {
            boolean exists = false;
            while (!exists) {
                System.out.println("Reading file...");
                Scanner scn = new Scanner(System.in);
                System.out.println("ENTER FILE NAME:");
                name = name + scn.nextLine() + ".txt";
                System.out.println(name);
                File f = new File(name);
                if (f.exists()) {
                    exists = true;
                } else {
                    System.out.println("FILE: " + name + " DOESN'T EXISTS");
                    //name = "";
                }
            }
        }
        return name;
    }
}

