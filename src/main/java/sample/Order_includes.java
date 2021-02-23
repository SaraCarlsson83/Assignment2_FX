package sample;

public class Order_includes {

    protected int id;
    protected Orders order;
    protected Shoe shoe;

    public Order_includes(int id, Orders order, Shoe shoe) {
        this.id = id;
        this.order = order;
        this.shoe = shoe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Shoe getShoe() {
        return shoe;
    }

    public void setShoe(Shoe shoe) {
        this.shoe = shoe;
    }
}
