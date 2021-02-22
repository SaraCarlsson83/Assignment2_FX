package sample;

public class Community {

    protected int id;
    protected String communityName;

    public Community(int id, String communityName) {
        this.id = id;
        this.communityName = communityName;
    }

    public Community() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }
}
