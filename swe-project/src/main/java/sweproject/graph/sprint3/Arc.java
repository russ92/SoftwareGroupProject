package sweproject.graph.sprint3;

import java.util.Set;

public interface Arc {
     Set<String> getVertex(String source);

     Boolean doesArcExist(String s, String d);

     int getNumOfRetweetsBetweenInvertedUsers(String s, String d);

     int getNumOfRetweetsBetweenUsers(String s, String d);

     int getTotalRetweets(String user);

     int getTotalRetweetsInverted(String user);

}