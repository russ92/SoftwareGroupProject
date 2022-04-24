
public class HashtagAnalysis {

    public static String splitCamelCaseHashtag(String hashtag) {
        StringBuilder hashtagSplit = new StringBuilder();
        for (int i = 0; i < hashtag.length(); i++) {
            Character ch = hashtag.charAt(i);
            if (Character.isUpperCase(ch))
                hashtagSplit.append(" ").append(Character.toLowerCase(ch));
            else if (ch != '#')
                hashtagSplit.append(ch);
        }
        return hashtagSplit.substring(1);
    }

    public static int acceptingOrRejecting(String hashtag) {

        String[] accepting = { "Praise", "Love", "Hero" };
        String[] rejecting = { "Fuck", "Hate", "Fire", "Fake" };

        int len = 0;
        int type = 2;
        while (len != accepting.length) {
            if (hashtag.contains(rejecting[len])) {
                type = 0;
            }
            len++;
        }

        if (type != 2) {
            while (len != accepting.length) {
                if (hashtag.contains(accepting[len])) {
                    type = 1;
                }
                len++;
            }
        }
        return type;
    }

    public static void main(String[] args) {
        String s = "#FauciIsAHero";
        String tag = HashtagAnalysis.splitCamelCaseHashtag(s);
        System.out.println(tag);

        if (acceptingOrRejecting(tag) == 0) {
            System.out.println("ACCEPTING");
        } else {
            System.out.println("REJECTING");
        }

    }
}