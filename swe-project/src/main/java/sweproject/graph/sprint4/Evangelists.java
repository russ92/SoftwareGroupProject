package sweproject.graph.sprint4;

public class Evangelists implements Comparable<Evangelists> {
    private final String angel;
    private final Integer numRetweets;
    private Integer stance;

    public Evangelists(String angel, Integer numRetweets, Integer stance) {
        this.angel = angel;
        this.numRetweets = numRetweets;
        this.stance = stance;
    }

    public String getAngel() { return angel; }
    public Integer getNumRetweets() { return numRetweets; }
    public Integer getStance() { return  stance; }

    public void setStance(int stance){ this.stance = stance; }

    @Override
    public String toString() { return angel + "\t" + numRetweets + "\t" + stance; }

    //comparison by number
    public int compareTo(Evangelists other) {
        return numRetweets.compareTo(other.numRetweets);
    }
}