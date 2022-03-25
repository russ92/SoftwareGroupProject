public class User {

    private long user_id;
    private String userhandle;
    private String location;
    private String bio;
    private int num_followers;

    public User(long user_id, String userhandle, String location, String bio, int num_followers) {
        this.user_id = user_id;
        this.userhandle = userhandle.replace("\n", "").replace("\r", "");;
        this.location = location.replace("\n", "").replace("\r", "");;
        this.bio = bio.replace("\n", "").replace("\r", "");;
        this.num_followers = num_followers;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUserhandle() {
        return userhandle;
    }

    public void setUserhandle(String userhandle) {
        this.userhandle = userhandle;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getNum_followers() {
        return num_followers;
    }

    public void setNum_followers(int num_followers) {
        this.num_followers = num_followers;
    }

    @Override
    public String toString() {
        return user_id + "\t"
                + userhandle + "\t"
                + location + "\t"
                + bio + "\t"
                + num_followers + "\n" ;
    }
}
