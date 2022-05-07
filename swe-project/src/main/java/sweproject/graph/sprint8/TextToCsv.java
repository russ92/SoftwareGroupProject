package sweproject.graph.sprint8;

import sweproject.StoredData;
import sweproject.User;
import sweproject.graph.sprint3.Reader;
import sweproject.graph.sprint3.TwitterGraph;

import java.io.*;
import java.util.Hashtable;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicInteger;

public class TextToCsv {

    public static void main(String[] args) throws Exception {

//        String textFilePath = "swe-project/VaxData/Sprint6/hashtagToUser.txt";
//        String csvFilePath = "swe-project/src/main/java/sweproject/graph/giphi/hashtagToUser.csv";
//        convertToCSVFile(csvFilePath, textFilePath);
//
//        textFilePath = "swe-project/VaxData/Sprint6/userToHashtag.txt";
//        csvFilePath = "swe-project/src/main/java/sweproject/graph/giphi/userToHashtag.csv";
//        convertToCSVFile(csvFilePath, textFilePath);

        retweetGephiCSV();
    }

    private static void convertToCSVFile(String csvFilePath, String tsvFilePath) throws IOException {

        StringTokenizer tokenizer;
        try (BufferedReader br = new BufferedReader(new FileReader(tsvFilePath));
             PrintWriter writer = new PrintWriter(new FileWriter(csvFilePath));) {

            int i = 0;
            for (String line; (line = br.readLine()) != null; ) {
                i++;
                if (i % 10000 == 0) {
                    System.out.println("Processed: " + i);
                }
                tokenizer = new StringTokenizer(line, "\t");

                String csvLine = "";
                String token;
                while (tokenizer.hasMoreTokens()) {
                    token = tokenizer.nextToken().replaceAll("\"", "'");
                    csvLine += "\"" + token + "\",";
                }

                if (csvLine.endsWith(",")) {
                    csvLine = csvLine.substring(0, csvLine.length() - 1);
                }

                writer.write(csvLine + System.getProperty("line.separator"));
            }
        }
    }

    public static void retweetGephiCSV(){
        TwitterGraph graph = Reader.Read_Tweets();
        Map<String, Map<String, Integer>> map = graph.invert();
        Hashtable<String, User> users = StoredData.readStoredUsers();

        BufferedWriter bf = null;

        try {
            System.out.println("Creating CSV Nodes...");
            bf = new BufferedWriter(new FileWriter("swe-project/VaxData/Gephi/nodesRetweetGraph.csv"));
            // String to be written to file.
            StringBuilder toFile = new StringBuilder();
            toFile.append("id").append(",").append("label").append(",").append("numFollowers").append("\n");
            for(String id: users.keySet()){
                if(map.containsKey(id)) {
                    toFile.append(id).append(",").append(id).append(",").append(users.get(id).getNum_followers()).append("\n");
                }
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

        try {
            System.out.println("Creating CSV Edges... ");
            bf = new BufferedWriter(new FileWriter("swe-project/VaxData/Gephi/edgesRetweetGraph.csv"));
            // String to be written to file.
            StringBuilder toFile = new StringBuilder();
            toFile.append("Source").append(",").append("Target").append(",").append("weight").append("\n");
            map.forEach((source, retweets)->{
                retweets.forEach((target,weight)->{
                    toFile.append(source).append(",").append(target).append(",").append(weight).append("\n");
                });
            });
            for(String id: users.keySet()){
                toFile.append(id).append(",").append(id).append(",").append(users.get(id).getNum_followers()).append("\n");
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

    private static String convertToCSV(String line) {
        String csv = "";
        line = line.replaceAll("\t", ",");
        return line;
    }
}