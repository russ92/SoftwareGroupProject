package sweproject.graph.sprint6;

import sweproject.GetProperties;
import sweproject.graph.sprint4.Evangelists;
import sweproject.graph.sprint5.Hashtags;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

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

        for(String h : hashtags){

            if(splitCamelCaseHashtag(h).length > 1 && !checkIfUppercase(h)){
                String[] split = splitCamelCaseHashtag(h);
                for(String s: split){
                    graph.addArc(h, s, "");
                }
            }

        }
        return graph;
    }

    public static void main(String [] args){
        String s = "#FauciIsAHero";
//        System.out.println(HashtagAnalysis.splitCamelCaseHashtag(s));
//        List<String> hashtags = HashtagAnalysis.Read_HashtagsToList();
//        int count = 0;
//        for(String h: hashtags) {
//            String split = HashtagAnalysis.splitCamelCaseHashtag(h);
//            System.out.println(split);
//            count++;
//        }
//        System.out.println(count);

        System.out.println(HashtagAnalysis.hashtagSplitAsGraph().getEdges().size());
    }
}
