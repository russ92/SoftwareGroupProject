package sweproject.graph.sprint5;

public class Hashtag  implements Comparable<Hashtag> {
    private final String hashtag;
    private Integer stance;

    public Hashtag(String hashtag, Integer stance) {
        this.hashtag = hashtag;
        this.stance = stance;
    }



    public String getHashtag() { return hashtag; }
    public Integer getStance() { return  stance; }

    public void setStance(int stance){ this.stance = stance; }

    @Override
    public String toString() { return hashtag + "\t" + stance; }

    //comparison by number
    public int compareTo(Hashtag other) {
        return stance.compareTo(other.stance);
    }
}
