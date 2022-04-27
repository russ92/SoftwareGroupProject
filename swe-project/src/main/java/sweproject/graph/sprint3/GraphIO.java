package sweproject.graph.sprint3;

import sweproject.GetProperties;
import sweproject.graph.sprint4.Evangelists;
import sweproject.graph.sprint4.Stances;

import java.io.*;
import java.util.*;
import java.util.Map;
import java.util.Scanner;

public class GraphIO {

    public static void GraphIO() throws FileNotFoundException {
        System.out.println("GIT");

        boolean incomplete = true;

        while (incomplete) {
            Scanner scn = new Scanner(System.in);
            System.out.println("1 to write the graph to a text file \n" +
                    "OR 2 to read a graph in from text file \n" +
                    "OR 3 to print top 100 angels to a file \n" +
                    "OR 4 to print top 100 retweeted angels to a file \n" +
                    "OR 5 to print stances of users to a file:");

            int choice = scn.nextInt();

            if (choice == 1) {
                System.out.println("Creating graph...");
                Map<String, Map<String, Integer>> map = Reader.Read_Tweets().getEdges();
                System.out.println("Writing graph...");
                writeToFile(map);
                incomplete = false;
            } else if (choice == 2) {
                TwitterGraph g = Reader.Read_HashMap();
                incomplete = false;
                System.out.println(g.getEdges());
            } else if (choice == 3) {
                System.out.println("Creating graph...");
                TwitterGraph graph = Reader.Read_Tweets();
                System.out.println("Creating list of angels...");
                List<Evangelists> angels = graph.getEvangelists();
                writeAngelsToFile(angels);
                incomplete = false;
            } else if (choice == 4) {
                System.out.println("Creating graph...");
                TwitterGraph graph = Reader.Read_HashMap();
                System.out.println("Creating list of retweet angels...");
                List<Evangelists> angels = graph.getInvertedEvangelists();
                writeAngelsToFile(angels);
                incomplete = false;
            } else if (choice == 5) {
                System.out.println("Assigning stances to users...");
                Map<String, Integer> angels = Stances.assignStances();
                writeHashMapStancesToFile(angels);
                incomplete = false;
            } else {
                System.out.println("PLEASE SELECT 1 OR 2 OR 3 OR 4 OR 5");
            }
        }
    }

    public static void writeHashMapStancesToFile(Map<String, Integer> map) {
        String path = fileName(1);
        File graph = new File(path);
        BufferedWriter bf = null;
        try {
            System.out.println("try");
            bf = new BufferedWriter(new FileWriter(graph));
            // String to be written to file.
            StringBuilder toFile = new StringBuilder();
            map.forEach((K, V) -> {
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

    private static void writeAngelsToFile(List<Evangelists> angels) {
        String path = fileName(1);
        File graph = new File(path);
        BufferedWriter bf = null;
        try {
            System.out.println("Writing to file...");
            bf = new BufferedWriter(new FileWriter(graph));
            // String to be written to file.
            StringBuilder toFile = new StringBuilder();
            for (int i = 0; i < 100; i++) {
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

    public static void writeToFile(Map<String, Map<String, Integer>> map) {
        String path = fileName(1);
        File graph = new File(path);
        BufferedWriter bf = null;
        try {
            System.out.println("try");
            bf = new BufferedWriter(new FileWriter(graph));
            // String to be written to file.
            StringBuilder toFile = new StringBuilder();
            map.forEach((K, V) -> {
                toFile.append("\n").append(K).append("\t");
                V.forEach((X, Y) -> {
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
        String name = prop.getPrintToFilepath(); // CHANGE THIS PATH IN application.properties
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