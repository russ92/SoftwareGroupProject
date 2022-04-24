package sweproject.graph.sprint6;

import sweproject.GetProperties;
import sweproject.graph.sprint3.Edge;
import sweproject.graph.sprint3.Graph;
import sweproject.graph.sprint4.Evangelists;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class HashtagGraph {
//    private final Map<String, Set<String>> list = new HashMap<>();
//
//    public void addSourceWord(String word) {
//        list.computeIfAbsent(word, key -> new TreeSet<>());
//    }
//    public void addWord(String word, String ref) {
//        addToLexiconList(word, ref);
//    }
//    private void addToLexiconList(String word, String ref) {
//        addSourceWord(word);
//        list.get(word).add(ref);
//    }
//    public Map<String, Set<String>>  getLexicon(){
//        return list;
//    }

    private final Map<String, Set<String>> list = new HashMap<>();
    private final Map<String, Map<String, Set<String>>> referenceList = new HashMap<>();

    // Inverted
    private final Map<String, Set<String>> invertedList = new HashMap<>();
    private final Map<String, Map<String, Set<String>>> invertedReferenceList = new HashMap<>();

    public void addSourceNode(String source) {
        list.computeIfAbsent(source, key -> new TreeSet<>());
    }

    public void addInvertedSourceNode(String source) { invertedList.computeIfAbsent(source, key -> new TreeSet<>());}

    public void addArc(String hashtag, String split, String ref) {
        if(referenceList.containsKey(hashtag)) {
            if(referenceList.get(hashtag).containsKey(split)) {
                addToReferenceList(new HashtagReference(hashtag, split, ref));
            }
            else addToReferenceList(new HashtagReference(hashtag, split, ref));
        }
        else addToReferenceList(new HashtagReference(hashtag, split, ref));
    }

    private void addToReferenceList(HashtagReference e) {
        addSourceNode(e.hashtag);
        list.get(e.hashtag).add(e.split);

        //inverted map
        addInvertedSourceNode(e.split);
        invertedList.get(e.split).add(e.hashtag);

        setReference(new HashtagReference(e.hashtag, e.split, e.ref));
    }

    private void setReference(HashtagReference e) {
        referenceList.computeIfAbsent(e.hashtag, key -> new HashMap<>());
        referenceList.get(e.hashtag).put(e.split, Collections.singleton(e.ref));

        //inverted weighted map
        invertedReferenceList.computeIfAbsent(e.split, key -> new HashMap<>());
        invertedReferenceList.get(e.split).put(e.hashtag, Collections.singleton(e.ref));
    }

    public  Map<String, Map<String, Set<String>>> getEdges() {
        return referenceList;
    }

    public  Map<String, Map<String, Set<String>>>  invert() {
        return invertedReferenceList;
    }

}
