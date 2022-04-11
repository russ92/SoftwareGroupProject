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
            String file = "VaxData/provided/vax tweets.txt";

            boolean incomplete = true;

            while (incomplete) {
                Scanner scn = new Scanner(System.in);
                System.out.println("1 to write to the graph to a text file \n" +
                                    "OR 2 to read a graph in from text file \n" +
                                    "OR 3 to print top 100 angels to a file \n" +
                                    "OR 4 to print top 100 retweeted angels to a file \n" +
                                    "OR 5 to print stances to a file:");
                if (scn.nextInt() == 1) {
                    System.out.println("Creating graph...");
                    Map<String, Map<String, Integer>> map = Reader.Read_Tweets().getEdges();
                    System.out.println("Writing graph...");
                    writeToFile(map);
                    incomplete = false;
                } else if (scn.nextInt() == 2) {
                    TwitterGraph g = writeToHashMap();
                    incomplete = false;
                    System.out.println(g.getEdges());
                    if(g.doesArcExist("@DealRael", "@JamesMelville")) System.out.println(g.getNumOfRetweets("@DealRael","@JamesMelville"));
                } else if (scn.nextInt() == 3) {
                    System.out.println("Creating graph...");
                    TwitterGraph graph = Reader.Read_Tweets();
                    System.out.println("Creating list of angels...");
                    List<Evangelists> angels = graph.getEvangelists();
                    writeAngelsToFile(angels);
                    incomplete = false;
                } else if (scn.nextInt() == 4) {
                    System.out.println("Creating graph...");
                    TwitterGraph graph = Reader.Read_Tweets();
                    System.out.println("Creating list of retweet angels...");
                    List<Evangelists> angels = graph.getInvertedEvangelists();
                    writeAngelsToFile(angels);
                    incomplete = false;
//                } else if (scn.nextInt() == 5) {
//                    System.out.println("Creating graph...");
//                    TwitterGraph graph = Reader.Read_Tweets();
//                    System.out.println("Creating list of retweet angels...");
//                    List<Evangelists> angels = Stances.assignStances();
//                    writeStancesToFile(angels);
//                    incomplete = false;
                } else if (scn.nextInt() == 5) {
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

//    private static void writeStancesToFile(List<Evangelists> angels) {
//        String path = fileName(1);
//        File graph = new File(path);
//        BufferedWriter bf = null;
//        try {
//            System.out.println("Writing to file...");
//            bf = new BufferedWriter(new FileWriter(graph));
//            // String to be written to file.
//            StringBuilder toFile = new StringBuilder();
//            for (Evangelists angel : angels) {
//                toFile.append("\n").append(angel);
//            }
//            bf.write(toFile.toString().trim());
//            bf.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                assert bf != null;
//                bf.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

    private static void writeAngelsToFile(List<Evangelists> angels) {
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
                    toFile.append(X).append(":").append(Y).append("\t");       // print key and value of inner Hashmap
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

//    public static void writeToHashMapMain(String file) throws FileNotFoundException {
//        Map<String, Map<String, Integer>> mapFromTxtFile = Reader.Read_Tweets(file).getGraphHashMap();
//        for (Map.Entry<String, Map<String, Integer>> entry : mapFromTxtFile.entrySet()) {
//            System.out.println(entry.getKey() + " : " + entry.getValue());
//        }
//    }

    public static TwitterGraph writeToHashMap() {
        TwitterGraph graph = new TwitterGraph();
        String path = fileName(2);
        File file = new File(path);

        try{
            BufferedReader buf = new BufferedReader(new FileReader(file));
            String lineJustFetched = null;

            while(true){
                lineJustFetched = buf.readLine();
                if(lineJustFetched == null){
                    break;
                }else{
                    String[] lineIn = lineJustFetched.split("\t");
                    String source = lineIn[0];

                    for(int i = 1; i<lineIn.length; i++ ){
                        String[] retweet = lineIn[i].split(":");
                        int numRetweets = Integer.parseInt(retweet[1]);
                        String destination = retweet[0];

                        graph.addArc(source, destination, numRetweets);

                    }
                }
            }

            buf.close();

        }catch(Exception e){
            e.printStackTrace();
        }
        return graph;
    }

    // int passed in so that the code can differentiate between creating a new
    // file and loading a file
    public static String fileName(int rW) {
        String name = "swe-project\\VaxData\\Graphs\\"; // CHANGE THIS PATH AS NEEDED. MUST BE EXACT.
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