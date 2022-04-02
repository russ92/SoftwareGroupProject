package sweproject;

import java.util.*;

import static java.lang.Integer.parseInt;

public class TwitterGraph{

    static class Graph<T> {

        // We use Hashmap to store the edges in the graph
        private Map<T, List<T> > map = new HashMap<>();

        // This function adds a new vertex to the graph
        public void addVertex(T s)
        {
            map.put(s, new LinkedList<>());
        }

        // This function adds the edge
        // between source to destination
        public void addArc(T source,
                            T destination,
                            T numOfRetweets)
        {

            if (!map.containsKey(source))
                addVertex(source);

            if (!map.containsKey(destination))
                addVertex(destination);

            map.get(source).add(destination);
                map.get(destination).add(numOfRetweets);

        }

        // This function gives the count of vertices
        public void getNumberOfUsers()
        {
            System.out.println("The graph has "
                    + map.keySet().size()
                    + " users that have retweeted.");
        }

        // This function gives the count of edges
//        public int getNumOfRetweets()
//        {
//            int count = 0;
//            for (T v : map.keySet()) {
//                for (T w : map.get(v)) {
//                    for (T n : map.get(w)) {
//                        count = Integer.parseInt(String.valueOf(map.get(n)) + count);
//                    }
//                }
//            }
//            return count;
//        }

        // This function gives whether
        // a vertex is present or not.
        public void doesUserRetweet(T s)
        {
            if (map.containsKey(s)) {
                System.out.println("The user "
                        + s + " has retweeted.");
            }
            else {
                System.out.println("The user "
                        + s + " has not retweeted.");
            }
        }

        // This function gives whether an edge is present or not.
        public void hasArc(T s, T d)
        {
            if (map.get(s).contains(d)) {
                System.out.println("User "
                        + d + " retweets user " + s + ".");
            }
            else {
                System.out.println("User "
                        + d + " doesn't retweets user " + s + ".");
            }
        }

        // Prints the adjancency list of each vertex.
        @Override
        public String toString()
        {
            StringBuilder builder = new StringBuilder();

            for (T v : map.keySet()) {
                builder.append(v.toString() + ": ");
                for (T w : map.get(v)) {
                    builder.append(w.toString() + " ");
                }
                builder.append("\n");
            }

            return (builder.toString());
        }
    }

    // Driver Code
    public static class Main {

        public static void main(String args[])
        {

            // Object of graph is created.
            Graph<Integer> g = new Graph<Integer>();

            // edges are added.
            // Since the graph is bidirectional,
            // so boolean bidirectional is passed as true.
            g.addArc(0, 1, 10);
            g.addArc(0, 2, 3);
            g.addArc(0, 3, 6);
            g.addArc(0, 4, 9);
            g.addArc(0, 5, 100);
            g.addArc(0, 6, 111);

            // Printing the graph
            System.out.println("Graph:\n"
                    + g);

            // Gives the no of vertices in the graph.
            g.getNumberOfUsers();

            // Gives the no of edges in the graph.
//            g.getNumOfRetweets();

            // Tells whether the edge is present or not.
            g.hasArc(0, 4);

            // Tells whether vertex is present or not
            g.doesUserRetweet(5);
        }
    }
}