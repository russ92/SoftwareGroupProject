package sweproject.graph.sprint3;

import java.util.Set;

public interface Arc {
    public Set<String> getVertex(String source);

    public Boolean doesArcExist(String s, String d);

    public int getNumOfRetweets(String s, String d);

}