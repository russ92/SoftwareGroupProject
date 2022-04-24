package sweproject.graph.sprint6;

import sweproject.GetProperties;
import sweproject.graph.sprint5.Hashtags;

import java.io.BufferedReader;
import java.io.FileReader;

public class HashtagAnalysis {

    public static String splitCamelCaseHashtag(String hashtag){
        StringBuilder hashtagSplit = new StringBuilder();
        for(int i = 0; i < hashtag.length(); i++) {
            Character ch = hashtag.charAt(i);
            if(Character.isUpperCase(ch))
                hashtagSplit.append(" ").append(Character.toLowerCase(ch));
            else if(ch != '#')
                hashtagSplit.append(ch);
        }
        return hashtagSplit.substring(1);
    }



    public HashtagGraph Read_LexiconToHashmap() {
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
                            graph.addWord(word, ref);
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

    public static void main(String [] args){
        String s = "#FauciIsAHero";
        System.out.println(HashtagAnalysis.splitCamelCaseHashtag(s));
    }
}
