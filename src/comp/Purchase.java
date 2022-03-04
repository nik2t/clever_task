package comp;

public class Purchase extends Product{

    protected int quantity;
    protected double totalPrice;
    public Purchase(int id, String name, double price, boolean specialOffer, int quantity, double totalPrice) {
        super(id, name, price, specialOffer);
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
