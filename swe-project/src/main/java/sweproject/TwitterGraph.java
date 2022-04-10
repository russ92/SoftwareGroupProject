package sweproject;

import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.Integer.parseInt;

public class TwitterGraph implements sweproject.Graph, sweproject.Arc {

    private final Map<String, Set<String>> retweetList = new HashMap<>();
    private final Map<String, Map<String, Integer>> weightedRetweets = new HashMap<>();

    // Inverted
    private final Map<String, Set<String>> invertedRetweetList = new HashMap<>();
    private final Map<String, Map<String, Integer>> invertedWeightedRetweets = new HashMap<>();

    public void addSourceNode(String source) {
        retweetList.computeIfAbsent(source, key -> new TreeSet<>());
    }

    public void addInvertedSourceNode(String source) { invertedRetweetList.computeIfAbsent(source, key -> new TreeSet<>());}

    public void addArc(String source, String destination, int weight) {
        if(weightedRetweets.containsKey(source)) {
            if(weightedRetweets.get(source).containsKey(destination)) {
                int count = weightedRetweets.get(source).get(destination) + weight;
                addToRetweetList(new Edge(source, destination, count));
            }
            else addToRetweetList(new Edge(source, destination, weight));
        }
        else addToRetweetList(new Edge(source, destination, weight));
    }

    private void addToRetweetList(Edge e) {
        addSourceNode(e.source);
        retweetList.get(e.source).add(e.destination);

        //inverted map
        addInvertedSourceNode(e.destination);
        invertedRetweetList.get(e.destination).add(e.source);

        setWeight(new Edge(e.source, e.destination, e.weight));
    }

    private void setWeight(Edge e) {
        weightedRetweets.computeIfAbsent(e.source, key -> new HashMap<>());
        weightedRetweets.get(e.source).put(e.destination, e.weight);

        //inverted weighted map
        invertedWeightedRetweets.computeIfAbsent(e.destination, key -> new HashMap<>());
        invertedWeightedRetweets.get(e.destination).put(e.source, e.weight);
    }

    @Override
    public List<Evangelists> getEvangelists(){
        List<Evangelists> angels = new ArrayList<>();
        for (String p : weightedRetweets.keySet() ) {
            int count = 0;
            for (String c : weightedRetweets.get(p).keySet() ) {
                count += weightedRetweets.get(p).get(c);
            }
            angels.add(new Evangelists(p, count, 0));
        }

        angels.sort(Collections.reverseOrder());
        return angels;
    }

    //Graph methods
    @Override
    public Map<String, Map<String, Integer>> getEdges() {
        return weightedRetweets;
    }
    //Inverted Method
    //Not sure how to simply invert the hashmap, so I just created 2 new maps instead
    @Override
    public Map<String, Map<String, Integer>> invert() {
        return invertedWeightedRetweets;
    }

    public List<Evangelists> getInvertedEvangelists(){
        List<Evangelists> angels = new ArrayList<>();
        for (String p : invertedWeightedRetweets.keySet() ) {
            int count = 0;
            for (String c : invertedWeightedRetweets.get(p).keySet() ) {
                count += invertedWeightedRetweets.get(p).get(c);
            }
            angels.add(new Evangelists(p, count, 0));
        }

        angels.sort(Collections.reverseOrder());
        return angels;
    }

    public int getTotalRetweets(String user){
        int count = 0;
        for(String u : weightedRetweets.get(user).keySet()){
            count += weightedRetweets.get(user).get(u);
        }
        return count;
    }

    public int getTotalTimesRetweeted(String user){
        int count = 0;
        for(String u : invertedWeightedRetweets.get(user).keySet()){
            count += invertedWeightedRetweets.get(user).get(u);
        }
        return count;
    }

    // 2b, 2c, 2d of Sprint 3
    @Override
    public Set getVertex(String user){
        return retweetList.getOrDefault(user, null);
    }

    @Override
    public Boolean doesArcExist(String user1, String user2){
        return retweetList.get(user1).contains(user2);
    }

    @Override
    public int getNumOfRetweets(String user1, String user2){
        if(retweetList.get(user1).contains(user2)){
            return weightedRetweets.get(user1).get(user2);
        }
        else return 0;
    }

}