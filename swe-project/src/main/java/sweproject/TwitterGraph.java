package sweproject;

import java.util.*;

import static java.lang.Integer.parseInt;

public class TwitterGraph {

    public static class Graph implements sweproject.Graph, sweproject.Arc {

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
//            else if(retweetList.get(user2).contains(user1)){
//                return "An arc exists from " + user2 + " to " + user1 + ".";
//            }
            else return "No arc between " + user1 + " and " + user2 + ".";
        }

        @Override
        public int getNumOfRetweets(String user1, String user2){
            if(retweetList.get(user1).contains(user2)){
                return weightedRetweets.get(user1).get(user2);
            }
//            else if(retweetList.get(user2).contains(user1)){
//                return weightedRetweets.get(user2).get(user1);
//            }
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
            graph.getEdges();
            System.out.println(graph.retweetList);
            System.out.println(graph.doesArcExistBetween("@source", "@Potato"));
            System.out.println(graph.doesArcExistBetween("@source", "@Potat"));
            System.out.println(graph.getNumOfRetweets("@source", "@Potato"));
            System.out.println(graph.getNumOfRetweets("@source", "@Poto"));
            System.out.println(graph.getAllUsersRetweeted("@source"));
            System.out.println(graph.getAllUsersRetweeted("@Potato"));
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