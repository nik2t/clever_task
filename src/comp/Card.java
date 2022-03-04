package comp;

public class Card {
    protected double discount;
    protected String CardNumber;

    public Card(double discount, String cardNumber) {
        this.discount = discount;
        CardNumber = cardNumber;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String cardNumber) {
        CardNumber = cardNumber;
    }
}
