package sweproject.graph.sprint6;

import sweproject.GetProperties;
import sweproject.graph.sprint3.Edge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Lexicon {
    private final Map<String, Set<String>> list = new HashMap<>();

    public void Read_LexiconToHashmap() {
        GetProperties prop = new GetProperties();

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
                            addWord(word, ref);
                        }
                    }
                }
            }
            buf.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void addSourceWord(String word) {
        list.computeIfAbsent(word, key -> new TreeSet<>());
    }
    public void addWord(String word, String ref) {
        addToLexiconList(word, ref);
    }
    private void addToLexiconList(String word, String ref) {
        addSourceWord(word);
        list.get(word).add(ref);
    }
    public Map<String, Set<String>>  getLexicon(){
        System.out.println("Reading lexicon... ");
        Read_LexiconToHashmap();
        return list;
    }
}
