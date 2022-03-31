import java.io.*;
import java.util.*;

public class GraphIO {

    public void writeToFile() {
        String path = fileName();
        File graph = new File(path);
        BufferedWriter bf = null;

        try {
            bf = new BufferWriter(new FileWriter(graph));
            for (Map.Entry<String, Int> entry : map.entrySet()) {
                bf.write(entry.getKey() + ":" + entry.getValue());
                bf.newLine();
            }
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

    public void writeToHashmap() {
        // read text file to HashMap
        Map<String, String> mapFromTxtFile = HashMapFromTextFile();

        // iterate over HashMap entries
        for (Map.Entry<String, String> entry : mapFromTxtFile.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    public String fileName() {
        Scanner scn = new Scanner(System.in);
        String name = "Graphs/" + scn.nextLine() + ".txt";
        return name;
    }
}