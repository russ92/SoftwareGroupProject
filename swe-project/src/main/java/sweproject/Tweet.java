package sweproject;

import java.util.Date;

public class Tweet{

    private long status_id;
    private String userhandle;
    private String text;
    private int num_retweets;
    private Date timestamp;

    public Tweet(long status_id, String userhandle, String text, int num_retweets, Date timestamp) {
        this.status_id = status_id;
        this.userhandle = "@" + userhandle.replace("\n", "").replace("\r", "");
        this.text = text.replace("\n", "").replace("\r", "");
        this.num_retweets = num_retweets;
        this.timestamp = timestamp;
    }

    public long getStatus_id() {
        return status_id;
    }

    public void setStatus_id(long status_id) {
        this.status_id = status_id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getNum_retweets() {
        return num_retweets;
    }

    public void setNum_retweets(int num_retweets) {
        this.num_retweets = num_retweets;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
                + text + "\t"
                + num_retweets + "\t"
                + timestamp;
    }

//    public boolean occurs(Tweet tweet) {
//        if (status_id == tweet.getStatus_id()){ return true; }
//        return false;
//    }

}
