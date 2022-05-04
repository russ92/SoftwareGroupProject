
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

        String[] accepting = { "Hero", "Praise", "Love", "Thank You", "Awesome", "Heroes", "Thanks", "Saviour",
                "King", "Get" };
        String[] rejecting = { "Dont", "Fuck", "Hate", "Fire", "Fake", "Stupid", "Hell", "Dictatorship", "Dictator",
                "Tyrant",
                "Evil", "Idiot" };

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

        String[] personR = { "Trump", "Cruz", "Carson", "Pence", "Paul", "Rubio", "Bush" };
        String[] personL = { "Fauci", "Biden", "Pelosi", "Schumer", "Obama", "Bernie", "Clinton", "Harris", "Warren",
                "Waters" };

        int len = 0;
        int type = 0;
        while (len != personR.length) {
            if (hashtag.contains(personR[len])) {
                type = 1;
            }
            len++;
        }

        len = 0;
        while (len != personL.length) {
            if (hashtag.contains(personL[len])) {
                type = 2;
            }
            len++;
        }

        return type;
    }

    public static String rightsResponsibilities(String hashtag) {

        String[] rights = { "Mandate", "Mandates", "Vaccine Mandate", "No Mandates", "Vaccine Mandates", "Restrictions",
                "Lockdown", "Prison",
                "Prisoner", "Prisoners", "Freedom" };
        String[] responsibilities = { "Stay At Home", "Get Vaccinated", "Wear A Mask", "Mask", "Masks", "Booster",
                "Booster Vaccine", "Social Distance",
                "Social Distancing", "Gatherings" };

        String type = "";

        for (int i = 0; i < rights.length; i++) {
            if (hashtag.contains(rights[i])) {
                type = "RIGHTS ";
            }
        }

        for (int i = 0; i < responsibilities.length; i++) {
            if (hashtag.contains(responsibilities[i])) {
                type = "RESPONSIBILITIES ";
            }
        }

        return type;
    }

    public static int location(String hashtag) {

        String[] place = { "USA", "America", "Europe", "Australia", "New Zealand", "Japan", "China", "Ireland",
                "Britain", "France", "Germany", "Spain", "Poland", "Sweeden", "Norway", "Denmark", "Canada" };

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

    public static String hashtagTarget(String hashtag) {
        String tagTarget = "";
        int acceptRejectNum = acceptingOrRejecting(hashtag);
        int individualNum = individual(hashtag);
        int locationNUm = location(hashtag);
        String rightsResponsibilities = rightsResponsibilities(hashtag);

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

        // RIGHTS AND RESPONSIBILITIES
        if (!rightsResponsibilities.isEmpty()) {
            tagTarget += rightsResponsibilities;
        }

        if (!rightsResponsibilities.isEmpty() && acceptRejectNum == 0) {
            tagTarget += "ANTI";
        }

        if (!rightsResponsibilities.isEmpty() && acceptRejectNum == 1) {
            tagTarget += "PRO";
        }
        return tagTarget;
    }

    public static void main(String[] args) {
        String s = "#FauciIsAHero";
        String tag = HashtagAnalysis.splitCamelCaseHashtag(s);
        System.out.println(tag);
        System.out.println(hashtagTarget(tag));

    }
}