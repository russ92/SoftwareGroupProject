package sweproject.graph.sprint5;

import sweproject.GetProperties;
import sweproject.graph.sprint4.Evangelists;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class WriteHashtags {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("HASHTAGS");
        GetProperties prop = new GetProperties();

        // File to be written as a graph
        String file = prop.getGraphFilepath();

        boolean incomplete = true;

        while (incomplete) {
            Scanner scn = new Scanner(System.in);
            System.out.println("1 to write the userToHashtag graph to a text file \n" +
                    "OR 2 to write a hashtagToUser graph to text file \n" +
                    "OR 3 to write the 100 most popular hashtags to text file \n" +
                    "OR 4 to write the hashtags with stances to text file: \n" +
                    "OR 5 to write the user stances from hashtags stances they use to text file:");

            int choice = scn.nextInt();

            if (choice == 1) {
                System.out.println("Creating graph...");
                Map<String, Map<String, Integer>> map = Hashtags.Read_Hashtags().getEdges();
                System.out.println("Writing graph...");
                writeToFile(map);
                incomplete = false;
            } else if (choice== 2) {
                System.out.println("Creating graph...");
                Map<String, Map<String, Integer>> map = Hashtags.Read_Hashtags().invert();
                System.out.println("Writing graph...");
                writeToFile(map);
                incomplete = false;
            } else if (choice == 3) {
                System.out.println("Creating graph...");
                List<Evangelists> hashtags = Hashtags.Read_Hashtags().getInvertedEvangelists();
                System.out.println("Writing hashtags...");
                writeHashtagsToFile(hashtags);
                incomplete = false;
            } else if (choice == 4) {
                Map<String, Integer> hashtags = Hashtags.assignHashtagStances();
                System.out.println("Writing hashtags...");
                writeHashtagStancesToFile(hashtags);
                incomplete = false;
            } else if (choice == 5) {
                Map<String, Integer> hashtags = Hashtags.assignUserStancesFromHashtags();
                System.out.println("Writing users...");
                writeHashtagStancesToFile(hashtags);
                incomplete = false;
            }else {
                System.out.println("PLEASE ENTER 1 OR 2 OR 3 OR 4 OR 5");
            }
        }
    }

    private static void writeHashtagsToFile(List<Evangelists> angels) {
        String path = fileName(1);
        File graph = new File(path);
        BufferedWriter bf = null;
        try {
            System.out.println("Writing to file...");
            bf = new BufferedWriter(new FileWriter(graph));
            // String to be written to file.
            StringBuilder toFile = new StringBuilder();
            for (int i = 0; i < 100; i++){
                toFile.append("\n").append(angels.get(i));
            }
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

    public static void writeHashtagStancesToFile(Map<String, Integer> map) {
        String path = fileName(1);
        File graph = new File(path);
        BufferedWriter bf = null;
        try {
            System.out.println("try");
            bf = new BufferedWriter(new FileWriter(graph));
            // String to be written to file.
            StringBuilder toFile = new StringBuilder();
            map.forEach((K, V)->{
                toFile.append("\n").append(K).append("\t").append(V);
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

    public static void writeToFile(Map<String, Map<String, Integer>> map) {
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
