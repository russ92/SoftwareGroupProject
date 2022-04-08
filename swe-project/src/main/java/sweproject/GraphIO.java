package sweproject;

import java.io.*;
import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GraphIO {

        public static void main(String[] args) throws FileNotFoundException {
            System.out.println("GIT");

            // File to be written as a graph
            String file = "swe-project/VaxData/provided/vax tweets.txt";

            int loop = 1;

            while (loop == 1) {
                Scanner scn = new Scanner(System.in);
                System.out.println("1 to write to text file \n" +
                                    "OR 2 to read from text file \n" +
                                    "OR 3 to print first 100 angels to a file:");
                if (scn.nextInt() == 1) {
                    System.out.println("Creating graph...");
                    HashMap<String, Map<String, Integer>> map = Reader.Read_Tweets(file).getEdges();
                    System.out.println("Writing graph...");
                    writeToFile(map);
                    loop = 0;
                } else if (scn.nextInt() == 2) {
                    writeToHashMapMain(file);
                    loop = 0;
                } else if (scn.nextInt() == 3) {
                    System.out.println("Creating graph...");
                    TwitterGraph graph = Reader.Read_Tweets(file);
                    System.out.println("Creating list of angels...");
                    List<Evangelists> angels = graph.getEvangelists();
                    writeAngelsToFile(angels);
                    loop = 0;
                } else {
                    System.out.println("PLEASE SELECT 1 OR 2 OR 3");
                }
            }
        }

    private static void writeAngelsToFile(List<Evangelists> angels) {
//        for(Evangelists e: angels) {
//            if (e.getNumRetweets()>100) {
//                System.out.println(e);
//            }
//        }

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
                bf.close();
            } catch (Exception e) {
            }
        }
    }

    public static void writeToFile(HashMap<String, Map<String, Integer>> map) {
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
                    toFile.append(X).append(":").append(Y).append("\t");       // print key and value of inner Hashmap
                });
            });
            bf.write(toFile.toString().trim());
            bf.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bf.close();
            } catch (Exception e) {
            }
        }
    }

    public static void writeToHashMapMain(String file) throws FileNotFoundException {
        Map<String, Map<String, Integer>> mapFromTxtFile = Reader.Read_Tweets(file).getEdges();

        for (Map.Entry<String, Map<String, Integer>> entry : mapFromTxtFile.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

//    public static Map<String, Map<String, Integer>> writeToHashMap() {
//        Map<String, Map<String, Integer>> map = new HashMap<String, Map<String, Integer>>();
//        BufferedReader br = null;
//        String path = fileName(2);
//
//        try {
//            File file = new File(path);
//            br = new BufferedReader(new FileReader(file));
//            String line = null;
//            while ((line = br.readLine()) != null) {
//                String[] parts = line.split(":");
//                String user = parts[0].trim();
//                String numRetweetsStr = parts[1].trim();
//                int numRetweets = Integer.parseInt(numRetweetsStr);
//
//                if (!user.equals("")) {
//                    map.put(user, numRetweets);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (Exception e) {
//                }
//            }
//        }
//        return map;
//    }

    // int passed in so that the code can differentiate between creating a new
    // file and loading a file
    public static String fileName(int rW) {
        int readOrWrite = rW;
        String name = "swe-project\\VaxData\\Graphs\\"; // CHANGE THIS PATH AS NEEDED. MUST BE EXACT.
        if (readOrWrite == 1) {
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
                    exists = true;
                    System.out.println("FILE ALREADY EXISTS");
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
                    exists = false;
                    System.out.println("FILE DOES NOT EXIST");
                }
            }
        }
    return name;
}
}