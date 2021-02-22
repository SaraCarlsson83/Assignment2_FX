package sample;

public class Divide_into_category {

    protected int id;
    protected Shoe shoe;
    protected Category category;

    public Divide_into_category(int id, Shoe shoe, Category category) {
        this.id = id;
        this.shoe = shoe;
        this.category = category;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
