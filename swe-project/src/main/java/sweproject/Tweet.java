package sweproject;


public class Tweet{

    private long status_id;
    private String userhandle;
    private int num_retweets;

    public Tweet(long status_id, String userhandle, int num_retweets) {
        this.status_id = status_id;
        this.num_retweets = num_retweets;
    }

    public long getStatus_id() {
        return status_id;
    }

    public void setStatus_id(long status_id) {
        this.status_id = status_id;
    }

    public int getNum_retweets() {
        return num_retweets;
    }

    public void setNum_retweets(int num_retweets) {
        this.num_retweets = num_retweets;
    }

    public String getUserhandle() {
        return userhandle;
    }

    public void setUserhandle(String userhandle) {
        this.userhandle = userhandle;
    }

    @Override
    public String toString() {
        return status_id + "\t"
                + userhandle + "\t"
                + num_retweets + "\t";
    }

//    public boolean occurs(Tweet tweet) {
//        if (status_id == tweet.getStatus_id()){ return true; }
//        return false;
//    }

}
