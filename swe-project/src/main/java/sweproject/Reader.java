package sweproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Reader {
    public static HashMap Read_Tweets() throws FileNotFoundException {
        File file = new File("VaxData/vax tweets.txt");
        Scanner sc = new Scanner(file);
        sc.useDelimiter("\\s");
        int throwAway;
        HashMap<String, String> tweetData = new HashMap<>();
        while (sc.hasNextLine()) {
            throwAway = sc.nextInt();
            tweetData.put(sc.next(), sc.next());
        }
        return tweetData;
    }
}