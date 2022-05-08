package sweproject.graph.sprint7;

import sweproject.graph.sprint5.Hashtags;
import sweproject.graph.sprint6.HashtagAnalysis;
import sweproject.graph.sprint6.HashtagGraph;

import java.util.*;

public class ReferenceAnalysis {

    private static final Hashtable<String, String> opposites = new Hashtable<>();

    public static HashtagGraph psychologicalProfileGraph() {
        HashtagGraph graph = new HashtagGraph();
        Map<String, Map<String, Integer>> userToHashtagMap = Hashtags.readHashtags().getEdges();
        Map<String, Map<String, Set<String>>> hashtagSplitMap = HashtagAnalysis.hashtagSplitAsGraph().getEdges();

        initializeOpposites();

        for(String user : userToHashtagMap.keySet()){
            for(String hashtag : userToHashtagMap.get(user).keySet()) {
                Set<String> references = new TreeSet<>();
                if (hashtagSplitMap.containsKey(hashtag)) {
                    for(String split : hashtagSplitMap.get(hashtag).keySet()){
                        references.addAll(hashtagSplitMap.get(hashtag).get(split));
                    }
                    Set<String> updatedRefs = assignReferences(references);
                    for(String refs : updatedRefs) {
                            graph.addArc(user, hashtag, refs);
                    }
                } else { graph.addArc(user, hashtag, "No-Hashtag-References"); }
            }

        }
        return graph;
    }

    public static HashtagGraph userHashtagReferenceGraph() {
        HashtagGraph graph = new HashtagGraph();
        Map<String, Map<String, Integer>> userToHashtagMap = Hashtags.readHashtags().getEdges();
        Map<String, Map<String, Set<String>>> hashtagSplitMap = HashtagAnalysis.hashtagSplitAsGraph().getEdges();

        for (String user : userToHashtagMap.keySet()) {
            for (String hashtag : userToHashtagMap.get(user).keySet()) {

                if (hashtagSplitMap.containsKey(hashtag)) {
                    for (String split : hashtagSplitMap.get(hashtag).keySet()) {
                        for (String splitRefs : hashtagSplitMap.get(hashtag).get(split)) {
                            graph.addArc(user, hashtag, splitRefs);
                        }
                    }
                } else {
                    graph.addArc(user, hashtag, "No-Hashtag-References");
                }
            }

        }
        return graph;
    }

    // Decides if a hashtag is '+' or '-' and accepting or rejecting
    public static Set<String> assignReferences(Set<String> input){
        Set<String> references = new TreeSet<>();
        if(input.contains("accepting") && !(input.contains("rejecting"))){
            for(String pos : input){
                if(pos.startsWith("ref:")){
                    pos = "+" + pos;
                    references.add(pos);
                } if(opposites.containsKey(pos)) {
                    references.add(pos + ">" + opposites.get(pos));
                }
            }
        } else if (input.contains("rejecting") ) {
            for(String neg : input){
                if(neg.startsWith("ref:")){
                    neg = "-" + neg;
                    references.add(neg);
                }
                if(opposites.containsKey(neg)) {
                    if( neg.equals("rejecting") || neg.equals("negation")){
                        references.add(neg + ">" + opposites.get(neg));
                    } else {
                        references.add(opposites.get(neg) + ">" + neg);
                    }
                }
            }
        } else {
            references.add("neutral/Undetermined");
        }
        return references;
    }

    // Initials the hashtable values with their opposites
    public static void initializeOpposites(){
        opposites.put("accepting", "rejecting");
        opposites.put("rejecting", "accepting");
        opposites.put("negation", "accept");
        opposites.put("accept", "negation");
        opposites.put("solution", "problem");
        opposites.put("problem", "solution");
        opposites.put("responsibilities", "rights");
        opposites.put("rights", "responsibilities");
        opposites.put("leftwing", "rightwing");
        opposites.put("rightwing", "leftwing");
        opposites.put("distrust", "trust");
        opposites.put("trust", "distrust");
        opposites.put("personal", "social");
        opposites.put("social", "personal");
    }
}
