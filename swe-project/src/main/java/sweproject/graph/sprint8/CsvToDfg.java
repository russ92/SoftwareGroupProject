package sweproject.graph.sprint8;
import java.io. * ;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CsvToDfg {

    public static void csvToDfg(){
        try {
            hashtagToUser();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            userToHashtag();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void hashtagToUser() throws Exception {
        String dfgFilePath = "swe-project/VaxData/Sprint8/hashtagToUser.dfg";
        String text = "",key ="",header="nodedef>name",nodeHeader="edgedef>node1,node2";
        int i = 0;
        PrintWriter writer = new PrintWriter(new FileWriter(dfgFilePath));
        HashMap<Integer, String> node = new HashMap<Integer, String>();
        HashMap<String, String> edge = new HashMap<String, String>();

        Scanner sc = new Scanner(new File("swe-project/src/main/java/sweproject/graph/giphi/hashtagToUser.csv"));
        sc.useDelimiter(",");

        edge.put(key,text);

        writer.write(header + "\n");
        text = sc.next();

        while (sc.hasNext()) {
            if(sc.toString().contains("#")) {
                key = sc.toString();
            }
            writer.append(text + "\n");
            text = sc.next();
            edge.put(key,text);
            //System.out.print(sc.next());
         }

        writer.append(nodeHeader + "\n");

        for(Map.Entry<String,String> entry : edge.entrySet()){
            writer.append(edge.get(key)+ "," + edge.get(text) + "\n");
        }

        writer.close();
        sc.close();
        //closes the scanner
    }
    public static void userToHashtag() throws Exception {
        String dfgFilePath = "swe-project/VaxData/Sprint8/userToHashtag.dfg";
        String text = "",key ="",header="nodedef>name",nodeHeader="edgedef>node1,node2";
        int i = 0;
        PrintWriter writer = new PrintWriter(new FileWriter(dfgFilePath));
        HashMap<Integer, String> node = new HashMap<Integer, String>();
        HashMap<String, String> edge = new HashMap<String, String>();

        Scanner sc = new Scanner(new File("swe-project/src/main/java/sweproject/graph/giphi/userToHashtag.csv"));
        sc.useDelimiter(",");

        edge.put(key,text);

        writer.write(header + "\n");
        text = sc.next();

        while (sc.hasNext()) {
            if(sc.toString().contains("@")) {
                key = sc.toString();
            }
            writer.append(text + "\n");
            text = sc.next();
            edge.put(key,text);
            //System.out.print(sc.next());
        }

        writer.append(nodeHeader + "\n");

        for(Map.Entry<String,String> entry : edge.entrySet()){
            writer.append(edge.get(key)+ "," + edge.get(text) + "\n");
        }

        writer.close();
        sc.close();
        //closes the scanner
    }
}
