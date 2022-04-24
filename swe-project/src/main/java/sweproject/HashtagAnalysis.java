
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

        String[] accepting = { "Hero", "Praise", "Love" };
        String[] rejecting = { "Fuck", "Hate", "Fire", "Fake" };

        int len = 0;
        int type = 2;
        while (len != rejecting.length) {
            if (hashtag.contains(rejecting[len])) {
                type = 0;
            }
            len++;
        }

        len = 0;
        if (type == 2) {
            while (len != accepting.length) {
                if (hashtag.contains(accepting[len])) {
                    type = 1;
                }
                len++;
            }
        }
        return type;
    }

    public static int individual(String hashtag) {

        String[] person = { "Fauci", "Trump", "Biden", "Pelosi", "Schumer", "Cruz" };

        int len = 0;
        int type = 0;
        while (len != person.length) {
            if (hashtag.contains(person[len])) {
                type = 1;
            }
            len++;
        }

        return type;
    }

    public static int location(String hashtag) {

        String[] place = { "USA", "America", "Europe", "Australia", "New Zealand", "Japan", "China", "Ireland",
                "Britain", "France" };

        int len = 0;
        int type = 0;
        while (len != place.length) {
            if (hashtag.contains(place[len])) {
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

        if (acceptRejectNum == 0) {
            tagTarget += "REJECTING ";
        }
        if (acceptRejectNum == 1) {
            tagTarget += "ACCEPTING ";
        }

        if (individualNum == 1) {
            tagTarget += "INDIVIDUAL";
        }

        if (locationNUm == 1) {
            tagTarget += "LOCATION";
        }

        return tagTarget;
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