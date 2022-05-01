package sweproject.graph.sprint7;

import sweproject.graph.sprint5.Hashtags;
import sweproject.graph.sprint6.HashtagAnalysis;
import sweproject.graph.sprint6.HashtagGraph;

import java.util.*;

public class ReferenceAnalysis {

    public static HashtagGraph userHashtagReferenceGraph() {
        HashtagGraph graph = new HashtagGraph();
        Map<String, Map<String, Integer>> userToHashtagMap = Hashtags.Read_Hashtags().getEdges();
        Map<String, Map<String, Set<String>>> hashtagSplitMap = HashtagAnalysis.hashtagSplitAsGraph().getEdges();

        for(String user : userToHashtagMap.keySet()){
            for(String hashtag : userToHashtagMap.get(user).keySet()) {

                if (hashtagSplitMap.containsKey(hashtag)) {
                    for(String split : hashtagSplitMap.get(hashtag).keySet()){
                        for(String splitRefs : hashtagSplitMap.get(hashtag).get(split)) {
                            graph.addArc(user, hashtag, splitRefs);
                        }
                    }
                } else { graph.addArc(user, hashtag, "No-Hashtag-References"); }
            }

        }
        return graph;
    }

    public static  void main(String [] args){
        Map<String, Map<String, Set<String>>> hashtagSplitMap = ReferenceAnalysis.userHashtagReferenceGraph().getEdges();
        System.out.println(hashtagSplitMap);
    }

}
