package sweproject;

import javax.sql.rowset.spi.SyncResolver;

public interface Arc {
    public String getTweetName();

    public Edge getVertex();

    public int strength();

    public String doesArcExistBetween(String s, String d);

    public int getNumOfRetweets(String s, String d);

    public String getAllUsersRetweeted(String s);
}