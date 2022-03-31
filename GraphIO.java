import java.io.*;
import java.util.*;

public class GraphIO {

    public void writeToFile() {
        String path = fileName(true);
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

    public void writeToHashmapMain() {
        Map<String, String> mapFromTxtFile = HashMapFromTextFile();

        for (Map.Entry<String, String> entry : mapFromTxtFile.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    public Map<String, String> writeToHashMap() {
        Map<String, String> map = new HashMap<String, String>();
        BufferedReader br = null;
        String path = fileName(false);

        try {
            File file = new File(path);
            br = new BufferedReader(new FileReader(file));
            String line = null;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                String user = parts[0].trim();
                Int numRetweets = parts[1].trim();
                if (!user.equals("") && !numRetweets.equals(""))
                    map.put(user, numRetweets);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                }
                ;
            }
        }
        return map;
    }

    // boolean passed in so that the code can differentiate between creating a new
    // file and loading a file
    public String fileName(boolean tf) {
        boolean exists = tf;
        while (exists) {
            Scanner scn = new Scanner(System.in);
            String name = "Graphs/" + scn.nextLine() + ".txt";
            File f = new File(name);
            if (f.exists()) {
                exists = true;
            } else {
                exists = false;
            }
        }
        return name;
    }
}