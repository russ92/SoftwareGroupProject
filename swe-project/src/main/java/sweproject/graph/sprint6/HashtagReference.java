package sweproject.graph.sprint6;

public class HashtagReference {
    String hashtag,split, ref;

    // Constructor
    HashtagReference(String sourceHashtag, String splitHashtag, String reference){
        this.hashtag = sourceHashtag;
        this.split = splitHashtag;
        this.ref = reference;
    }
    // Methods
    public void updateHashtag(String hashtagUpdate){
        this.hashtag = hashtagUpdate;
    }

    public void updateSplit(String splitUpdate){
        this.split = splitUpdate;
    }

    public void updateReference(String referenceUpdate){
        this.ref = referenceUpdate;
    }

    @Override
    public String toString(){
        return hashtag + " " + split + " " + ref;
    }

}
