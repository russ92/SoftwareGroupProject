package sweproject.graph.sprint6;

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

    public static void main(String [] args){
        String s = "#FauciIsAHero";
        System.out.println(HashtagAnalysis.splitCamelCaseHashtag(s));
    }
}
