package Entity;

public class OrderInput {

    private String [] orderitem;

    private int[] quantity;

    public OrderInput() {
    }

    public OrderInput(String[] orderitem, int[] quantity) {
        this.orderitem = orderitem;
        this.quantity = quantity;
    }

    public String[] getOrderitem() {
        return orderitem;
    }

    public void setOrderitem(String[] orderitem) {
        this.orderitem = orderitem;
    }

    public int[] getQuantity() {
        return quantity;
    }

    public void setQuantity(int[] quantity) {
        this.quantity = quantity;
    }
}
