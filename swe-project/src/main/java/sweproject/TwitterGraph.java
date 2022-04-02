package sweproject;

import java.util.*;

import static java.lang.Integer.parseInt;

public class TwitterGraph {

//    public static class Arc implements sweproject.Arc{
//
//        String source, destination;
//        int weight;
//
//        Arc(String source, String destination, int weight){
//            this.source = source;
//            this.destination = destination;
//            this.weight = weight;
//        }
//
//        @Override
//        public String getTweetName() {
//            return source;
//        }
//
//        @Override
//        public Edge getVertex(){
//            return new Edge(source, destination, weight);
//        }
//        @Override
//        public int strength(){
//            return weight;
//        }
//    }

    public static class Graph implements sweproject.Graph {

        private final Map<String, Set<String>> retweetList = new HashMap<>();
        private final Map<String, Map<String, Integer>> weightedRetweets = new HashMap<>();

        public void addSourceNode(String source) {
            retweetList.computeIfAbsent(source, key -> new TreeSet<>());
        }

        public void addArc(String source, String destination, int weight) {
            addToRetweetList(new Edge(source,destination,weight));
        }

        private void addToRetweetList(Edge e) {
            addSourceNode(e.source);
            retweetList.get(e.source).add(e.destination);
            setWeight(new Edge(e.source, e.destination, e.weight));
        }

        private void setWeight(Edge e) {
            weightedRetweets.computeIfAbsent(e.source, key -> new HashMap<>());
            weightedRetweets.get(e.source).put(e.destination, e.weight);
        }

        @Override
        public void getEdges() {
            for (Map.Entry<String, Set<String>> entry : retweetList.entrySet()){
                String source = entry.getKey();
                for (String destination : entry.getValue()) {
                    int weight = weightedRetweets.get(source).get(destination);
                    System.out.println( destination + " : " + weight);
                }
            }
        }

        @Override
        public void invert() {

        }

        public static void main(String... args) {
            Graph graph = new Graph();
            graph.addArc("@source", "@Potato", 10);
            graph.addArc("@source", "@Tahoe", 69);
            graph.addArc("@source", "@Poop", 2);
            graph.getEdges();
            System.out.println(graph.retweetList);
        }
    }
}

//    public void toString() {
//        for (Map.Entry<String, Set<String>> entry : adjacentList.entrySet()) {
//            String source = entry.getKey();
//
//            System.out.print(entry.getKey() + " is retweeted by [");
//            boolean comma = false;
//
//            for (String two : entry.getValue()) {
//                if (comma)
//                    System.out.print(", ");
//
//                comma = true;
//                double weight = weights.get(source).get(two);
//                System.out.print("(" + two + " : " + weight + ")");
//            }
//
//            System.out.println(']');
//        }
//    }