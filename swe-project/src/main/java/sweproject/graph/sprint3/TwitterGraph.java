package sweproject.graph.sprint3;

import sweproject.graph.sprint3.Arc;
import sweproject.graph.sprint3.Edge;
import sweproject.graph.sprint3.Evangelists;
import sweproject.graph.sprint3.Graph;

import java.util.*;
public class TwitterGraph implements Graph, Arc {

    private final Map<String, Set<String>> list = new HashMap<>();
    private final Map<String, Map<String, Integer>> weightedList = new HashMap<>();

    // Inverted
    private final Map<String, Set<String>> invertedList = new HashMap<>();
    private final Map<String, Map<String, Integer>> invertedWeightedList = new HashMap<>();

    public void addSourceNode(String source) {
        list.computeIfAbsent(source, key -> new TreeSet<>());
    }

    public void addInvertedSourceNode(String source) { invertedList.computeIfAbsent(source, key -> new TreeSet<>());}

    public void addArc(String source, String destination, int weight) {
        if(weightedList.containsKey(source)) {
            if(weightedList.get(source).containsKey(destination)) {
                int count = weightedList.get(source).get(destination) + weight;
                addToRetweetList(new Edge(source, destination, count));
            }
            else addToRetweetList(new Edge(source, destination, weight));
        }
        else addToRetweetList(new Edge(source, destination, weight));
    }

    private void addToRetweetList(Edge e) {
        addSourceNode(e.source);
        list.get(e.source).add(e.destination);

        //inverted map
        addInvertedSourceNode(e.destination);
        invertedList.get(e.destination).add(e.source);

        setWeight(new Edge(e.source, e.destination, e.weight));
    }

    private void setWeight(Edge e) {
        weightedList.computeIfAbsent(e.source, key -> new HashMap<>());
        weightedList.get(e.source).put(e.destination, e.weight);

        //inverted weighted map
        invertedWeightedList.computeIfAbsent(e.destination, key -> new HashMap<>());
        invertedWeightedList.get(e.destination).put(e.source, e.weight);
    }

    @Override
    public List<Evangelists> getEvangelists(){
        List<Evangelists> angels = new ArrayList<>();
        for (String p : weightedList.keySet() ) {
            int count = 0;
            for (String c : weightedList.get(p).keySet() ) {
                count += weightedList.get(p).get(c);
            }
            angels.add(new Evangelists(p, count, 0));
        }

        angels.sort(Collections.reverseOrder());
        return angels;
    }

    //Graph methods
    @Override
    public Map<String, Map<String, Integer>> getEdges() {
        return weightedList;
    }
    //Inverted Method
    //Not sure how to simply invert the hashmap, so I just created 2 new maps instead
    @Override
    public Map<String, Map<String, Integer>> invert() {
        return invertedWeightedList;
    }

    public List<Evangelists> getInvertedEvangelists(){
        List<Evangelists> angels = new ArrayList<>();
        for (String p : invertedWeightedList.keySet() ) {
            int count = 0;
            for (String c : invertedWeightedList.get(p).keySet() ) {
                count += invertedWeightedList.get(p).get(c);
            }
            angels.add(new Evangelists(p, count, 0));
        }

        angels.sort(Collections.reverseOrder());
        return angels;
    }

    public int getTotalRetweets(String user){
        int count = 0;
        for(String u : weightedList.get(user).keySet()){
            count += weightedList.get(user).get(u);
        }
        return count;
    }

    public int getTotalTimesRetweeted(String user){
        int count = 0;
        for(String u : invertedWeightedList.get(user).keySet()){
            count += invertedWeightedList.get(user).get(u);
        }
        return count;
    }

    // 2b, 2c, 2d of Sprint 3
    @Override
    public Set getVertex(String user){
        return list.getOrDefault(user, null);
    }

    @Override
    public Boolean doesArcExist(String user1, String user2){
        return list.get(user1).contains(user2);
    }

    @Override
    public int getNumOfRetweets(String user1, String user2){
        if(list.get(user1).contains(user2)){
            return weightedList.get(user1).get(user2);
        }
        else return 0;
    }

}