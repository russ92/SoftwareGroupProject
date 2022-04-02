package sweproject;

import java.util.*;

import static java.lang.Integer.parseInt;

public class TwitterGraph {

    public static class Graph implements sweproject.Graph, sweproject.Arc {

        private final Map<String, Set<String>> retweetList = new HashMap<>();
        private final Map<String, Map<String, Integer>> weightedRetweets = new HashMap<>();

        // Inverted
        private final Map<String, Set<String>> invertedRetweetList = new HashMap<>();
        private final Map<String, Map<String, Integer>> invertedWeightedRetweets = new HashMap<>();

        public void addSourceNode(String source) {
            retweetList.computeIfAbsent(source, key -> new TreeSet<>());
        }

        public void addInvertedSourceNode(String source) {
            invertedRetweetList.computeIfAbsent(source, key -> new TreeSet<>());
        }

        public void addArc(String source, String destination, int weight) {
            addToRetweetList(new Edge(source,destination,weight));
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
        public String getEdges() {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, Set<String>> entry : retweetList.entrySet()){
                String source = entry.getKey();
                for (String destination : entry.getValue()) {
                    int weight = weightedRetweets.get(source).get(destination);
                    sb.append( destination + " : " + weight + "\n");
                }
            }

            return sb.toString();
        }

        @Override
        public void invert() {
            StringBuilder string = new StringBuilder();
            for (Map.Entry<String, Set<String>> entry : invertedRetweetList.entrySet()){
                String source = entry.getKey();
                for (String destination : entry.getValue()) {
                    int weight = invertedWeightedRetweets.get(source).get(destination);
                    System.out.println( source + " retweets " + destination + " " + weight + " times.");
                }
            }
        }

        @Override
        public String getTweetName() {
            return null;
        }

        @Override
        public Edge getVertex() {
            return null;
        }

        @Override
        public int strength() {
            return 0;
        }

        @Override
        public String doesArcExistBetween(String user1, String user2){
            if(retweetList.get(user1).contains(user2)){
                return "An arc exists from " + user1 + " to " + user2 + ".";
            }
            else return "No arc between " + user1 + " and " + user2 + ".";
        }

        @Override
        public int getNumOfRetweets(String user1, String user2){
            if(retweetList.get(user1).contains(user2)){
                return weightedRetweets.get(user1).get(user2);
            }
            else return 0;
        }

        @Override
        public String getAllUsersRetweeted(String user){
            if(retweetList.containsKey(user)){
                return retweetList.get(user).toString();
            }
            return "No retweets found.";
        }

        public static void main(String... args) {
            Graph graph = new Graph();
            graph.addArc("@source", "@Potato", 10);
            graph.addArc("@source", "@Tahoe", 69);
            graph.addArc("@source", "@Poop", 2);
            System.out.println("Tests: ");
            System.out.println(graph.getEdges());
            System.out.println(graph.doesArcExistBetween("@source", "@Potato"));
            System.out.println(graph.doesArcExistBetween("@source", "@Potat") + "\n");
            System.out.println(graph.getNumOfRetweets("@source", "@Potato"));
            System.out.println(graph.getNumOfRetweets("@source", "@Poto") + "\n");
            System.out.println(graph.getAllUsersRetweeted("@source"));
            System.out.println(graph.getAllUsersRetweeted("@Potato") + "\n");
            graph.invert();
        }
    }
}