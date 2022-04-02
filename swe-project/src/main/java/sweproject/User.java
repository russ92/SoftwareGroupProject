package sweproject;
public class User {

    private String userhandle;
    private String location;
    private String bio;
    private int num_followers;

    public User(String userhandle, String location, String bio, int num_followers) {
        this.userhandle = userhandle.replace("\n", "").replace("\r", "");;
        this.location = location.replace("\n", "").replace("\r", "");;
        this.bio = bio.replace("\n", "").replace("\r", "");;
        this.num_followers = num_followers;
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
        return  userhandle + "\t"
                + location + "\t"
                + bio + "\t"
                + num_followers + "\n" ;
    }
}
