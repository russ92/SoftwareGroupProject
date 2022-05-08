package sweproject.graph.sprint7;

import sweproject.GetProperties;
import sweproject.graph.sprint3.Reader;
import sweproject.graph.sprint3.TwitterGraph;
import sweproject.graph.sprint6.HashtagGraph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class DatasetProbabilities {

//    public static HashtagGraph readHashtagReferenceGraph(){
//        GetProperties prop = new GetProperties();
//        HashtagGraph graph = new HashtagGraph();
//
//        try{
//            BufferedReader buf = new BufferedReader(new FileReader(prop.getHashtagReferenceFilepath()));
//            String lineJustFetched = null;
//
//            while(true){
//                lineJustFetched = buf.readLine();
//                if(lineJustFetched == null){
//                    break;
//                }else{
//                    String[] lineIn = lineJustFetched.split("\t");
//                    String user = lineIn[0];
//
//                    for(int i = 1; i<lineIn.length; i++ ){
//                        int split = lineIn[1].indexOf(":");
//                        String[] line = lineIn[i].split("\t");
//                        Set<String> references = Collections.singleton(line[1]);
//                        String hashtag = line[0];
//
//                        for(String ref : references) {
//                            graph.addArc(user, hashtag, ref.replace("[", "").replace("]", ""));
//                        }
//
//                    }
//                }
//            }
//
//            buf.close();
//
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return graph;
//    }

    public static void probabilitiesDriver(){

        HashtagGraph graph = ReferenceAnalysis.psychologicalProfileGraph();
        List<Integer> userStances = Reader.readUserStances();

        boolean incomplete = true;
        while (incomplete) {
            Scanner scn = new Scanner(System.in);
            System.out.println(
                    "Enter two datasets to analysis them in the form 'choice1 choice2' eg 'anti -ref:fauci' (single-space) \n" +
                            "OR 'end' to conclude analysis: "
            );

            String choice = scn.next().trim();

            if (!choice.equals("end")) {

            } else {
                incomplete = false;
            }
        }

    }

    public static int stance(List<Integer> userStances, String stance){
        int anti = 0;
        int pro = 0;
        for(int i: userStances){
            if(i < 0){
                anti++;
            } else if(i > 0){
                pro++;
            }
        }
        if(Objects.equals(stance, "anti")){
            return anti;
        } else return pro;
    }

}
