package sweproject;

public class Evangelists implements Comparable<Evangelists> {
    private final String angel;
    private final Integer numRetweets;

    public Evangelists(String angel, Integer numRetweets) {
        this.angel = angel;
        this.numRetweets = numRetweets;
    }

    public String getAngel() { return angel; }
    public Integer getNumRetweets() { return numRetweets; }

    @Override
    public String toString() { return angel + "\t" + numRetweets; }

    //comparison by number
    public int compareTo(Evangelists other) {
        return numRetweets.compareTo(other.numRetweets);
    }
}