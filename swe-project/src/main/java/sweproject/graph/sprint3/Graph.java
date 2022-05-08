package sweproject.graph.sprint3;

import sweproject.graph.sprint4.Evangelists;

import java.util.List;
import java.util.Map;

public interface Graph {

     Map<String, Map<String, Integer>> getEdges();
     Map<String, Map<String, Integer>> invert();
     List<Evangelists> getEvangelists();


}