package sample;

public class Shoe_color {

    protected int id;
    protected Shoe shoe;
    protected Color color;

    public Shoe_color(int id, Shoe shoe, Color color) {
        this.id = id;
        this.shoe = shoe;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Shoe getShoe() {
        return shoe;
    }

    public void setShoe(Shoe shoe) {
        this.shoe = shoe;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
