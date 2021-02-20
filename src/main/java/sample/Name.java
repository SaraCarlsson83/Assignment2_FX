package sample;

public class Name {

    protected int id;
    protected String shoeName;

    public Name(int id, String labelName) {
        this.id = id;
        this.shoeName = labelName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShoeName() {
        return shoeName;
    }

    public void setLabelName(String labelName) {
        this.shoeName = labelName;
    }
}
