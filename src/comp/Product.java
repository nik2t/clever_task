package comp;

public class Product {
    protected int id;
   protected String name;
   protected double price;
   protected boolean specialOffer;

    public Product(int id, String name, double price, boolean specialOffer) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.specialOffer = specialOffer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isSpecialOffer() {
        return specialOffer;
    }

    public void setSpecialOffer(boolean specialOffer) {
        this.specialOffer = specialOffer;
    }
}
