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

    public static Int acceptingOrRejecting(String hashtag){

        String[] accepting = {"Praise", "Love",}
        String[] rejecting = {"Fuck", "Hate", "Fire", "Fake"}

        int len = 0;
        while (len != accepting.length()) {
            if (hasthtag.contains(accepting[len])) {
                return 0;
            }
        }

        while (len != rejecting.length()) {
            if (hasthtag.contains(accepting[len])) {
                return 1;
            }
        }
    }

    public static void main(String [] args){
        String s = "#FauciIsAHero";
        System.out.println(HashtagAnalysis.splitCamelCaseHashtag(s));
        acceptingOrRejecting(splitCamelCaseHashtag(s))
    }
}
