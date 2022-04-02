package sweproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Graph {
    public String getEdges();
    public void invert();

    Map<Edge, List<Arc>> adjVertices = new HashMap <Edge, List<Arc>>();

}