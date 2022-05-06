package sweproject.graph.sprint8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TextToCsv {

    public static void main(String[] args) throws Exception {

        String textFilePath = "swe-project/VaxData/Sprint6/hashtagToUser.txt";
        String csvFilePath = "swe-project/src/main/java/sweproject/graph/giphi/hashtagToUser.csv";
        convertToCSVFile(csvFilePath, textFilePath);

        textFilePath = "swe-project/VaxData/Sprint6/userToHashtag.txt";
        csvFilePath = "swe-project/src/main/java/sweproject/graph/giphi/userToHashtag.csv";
        convertToCSVFile(csvFilePath, textFilePath);
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

    private static String convertToCSV(String line) {
        String csv = "";
        line = line.replaceAll("\t", ",");
        return line;
    }
}