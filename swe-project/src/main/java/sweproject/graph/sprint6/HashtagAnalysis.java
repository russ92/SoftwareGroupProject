package sweproject.graph.sprint6;

import sweproject.GetProperties;
import sweproject.graph.sprint4.Evangelists;
import sweproject.graph.sprint5.Hashtags;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class HashtagAnalysis {

    public static String[] splitCamelCaseHashtag(String hashtag){
        StringBuilder hashtagSplit = new StringBuilder();
        for(int i = 0; i < hashtag.length(); i++) {
            Character ch = hashtag.charAt(i);
            if(Character.isUpperCase(ch))
                hashtagSplit.append(" ").append(Character.toLowerCase(ch));
            else if(ch != '#')
                hashtagSplit.append(ch);
        }
        String h = hashtagSplit.toString().trim();
        return h.split(" ");
    }

    public static Boolean checkIfUppercase(String hashtag){
        int count = 0;
        for(int i = 0; i < hashtag.length(); i++) {
            char ch = hashtag.charAt(i);
            if(Character.isUpperCase(ch) || (ch >= '0' && ch <= '9') )
                count++;
        }
        return count == hashtag.length()-1;
    }

    public static List<String> Read_HashtagsToList(){
        GetProperties prop = new GetProperties();
        List<String> hashtags = new ArrayList<>();

        try{
            BufferedReader buf = new BufferedReader(new FileReader(prop.getHashtagFilepath()));
            String lineJustFetched;

            while(true){
                lineJustFetched = buf.readLine();
                if(lineJustFetched == null){
                    break;
                }else{
                    String[] lineIn = lineJustFetched.split("\t");

                    if (lineIn.length == 2 && lineIn[0].startsWith("#")) {
                        String hashtag = lineIn[0];
                        hashtags.add(hashtag);
                    }
                }
            }

            buf.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return hashtags;
    }

    public static HashtagGraph Read_LexiconToHashmap() {
        GetProperties prop = new GetProperties();
        HashtagGraph graph = new HashtagGraph();

        try{
            BufferedReader buf = new BufferedReader(new FileReader(prop.getLexiconFilepath()));
            String lineJustFetched = null;

            while(true){
                lineJustFetched = buf.readLine();
                if(lineJustFetched == null){
                    break;
                }else{
                    String[] lineIn = lineJustFetched.split(" ");
                    if(lineIn.length > 1) {
                        String word = lineIn[1];
                        for(int i = 2; i < lineIn.length; i++) {
                            String ref = lineIn[i].replace("[", "").replace(",", "").replace("]", "").trim();
                            graph.addArc("Given-Lexicon",word, ref);
                        }
                    }
                }
            }
            buf.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return graph;
    }

    public static HashtagGraph hashtagSplitAsGraph() {
        HashtagGraph graph = new HashtagGraph();
        List<String> hashtags = Read_HashtagsToList();
        Map<String, Map<String, Set<String>>> lexiconMap = HashtagAnalysis.Read_LexiconToHashmap().getEdges();

        for(String h : hashtags){

            if(splitCamelCaseHashtag(h).length > 1 && !checkIfUppercase(h)){
                String[] split = splitCamelCaseHashtag(h);
                for(String s: split){
                    for(String l : lexiconMap.get("Given-Lexicon").keySet()) {
                        Set<String> ref = lexiconMap.get("Given-Lexicon").get(l);
                        if(s.equals(l)){
                            for(String sref : ref) {
                                graph.addArc(h, s, sref);
                                String gist = hashtagGist(sref);
                                if (!Objects.equals(gist, "")){
                                    graph.addArc(h, s, gist.toLowerCase(Locale.ROOT));
                                }
                            }
                        }
                    }
                }
            }

        }
        return graph;
    }

    public static int acceptingOrRejecting(String hashtag) {

        String[] accepting = { "Hero", "Praise", "Love", "Thank You", "Awesome", "Heroes", "Thanks", "Saviour",
                "King", "Get" };
        String[] rejecting = { "Dont", "Fuck", "Hate", "Fire", "Fake", "Stupid", "Hell", "Dictatorship", "Dictator",
                "Tyrant",
                "Evil", "Idiot" };

        int len = 0;
        int type = 2;
        while (len != rejecting.length) {
            if (hashtag.toLowerCase(Locale.ROOT).contains(rejecting[len])) {
                type = 0;
            }
            len++;
        }

        len = 0;
        if (type == 2) {
            while (len != accepting.length) {
                if (hashtag.toLowerCase(Locale.ROOT).contains(accepting[len])) {
                    type = 1;
                }
                len++;
            }
        }
        return type;
    }

    public static int individual(String hashtag) {

        String[] personR = { "Trump", "Cruz", "Carson", "Pence", "Paul", "Rubio", "Bush" };
        String[] personL = { "Fauci", "Biden", "Pelosi", "Schumer", "Obama", "Bernie", "Clinton", "Harris", "Warren",
                "Waters" };

        int len = 0;
        int type = 0;
        while (len != personR.length) {
            if (hashtag.toLowerCase(Locale.ROOT).contains(personR[len])) {
                type = 1;
            }
            len++;
        }

        len = 0;
        while (len != personL.length) {
            if (hashtag.toLowerCase(Locale.ROOT).contains(personL[len])) {
                type = 2;
            }
            len++;
        }

        return type;
    }

    public static int location(String hashtag) {

        String[] place = { "USA", "America", "Europe", "Australia", "New Zealand", "Japan", "China", "Ireland",
                "Britain", "France", "Germany", "Spain", "Poland", "Sweeden", "Norway", "Denmark", "Canada" };

        int len = 0;
        int type = 0;
        while (len != place.length) {
            if (hashtag.toLowerCase(Locale.ROOT).contains(place[len])) {
                type = 1;
            }
            len++;
        }

        return type;
    }

    public static String hashtagGist(String hashtag) {
        String tagTarget = "";
        int acceptRejectNum = acceptingOrRejecting(hashtag);
        int individualNum = individual(hashtag);
        int locationNUm = location(hashtag);

        // ACCEPTING OR REJECTING
        if (acceptRejectNum == 0) {
            tagTarget += "REJECTING ";
        }
        if (acceptRejectNum == 1) {
            tagTarget += "ACCEPTING ";
        }

        // INDIVIDUAL
        if (individualNum == 1 || individualNum == 2) {
            tagTarget += "INDIVIDUAL ";
        }

        // GEOGRAPHICAL
        if (locationNUm == 1) {
            tagTarget += "LOCATION ";
        }

        // POLITICS
        if (acceptRejectNum == 1 && individualNum == 1) {
            tagTarget += "RIGHT-WING ";
        }
        if (acceptRejectNum == 0 && individualNum == 2) {
            tagTarget += "RIGHT-WING ";
        }

        if (acceptRejectNum == 1 && individualNum == 2) {
            tagTarget += "LEFT-WING ";
        }
        if (acceptRejectNum == 0 && individualNum == 1) {
            tagTarget += "LEFT-WING ";
        }

        return tagTarget;
    }
}
