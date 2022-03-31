import java.io.BufferedWriter;
import java.io.IOException;

public class GraphIO {

    public void writeToFile() {
        String path = nameFile();
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

    public String nameFile() {
        Scanner scn = new Scanner(System.in);
        String name = "Graphs/" + scn.nextLine() + ".txt";
        return name;
    }
}