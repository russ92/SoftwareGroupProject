package sweproject;

import java.util.Set;

public interface Arc {
    public String getTweetName();

    public Edge getVertex();

    public int strength();

    public Boolean doesArcExist(String s, String d);

    public int getNumOfRetweets(String s, String d);

    public Set getAllUsersRetweeted(String s);
}