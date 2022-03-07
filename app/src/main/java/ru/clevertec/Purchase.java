package ru.clevertec;

import lombok.Getter;
import lombok.Setter;

public class Purchase extends Product{

    @Getter
    @Setter
    protected int quantity;
    @Getter
    @Setter
    protected double totalPrice;
    public Purchase(int id, String name, double price, boolean specialOffer, int quantity, double totalPrice) {
        super(id, name, price, specialOffer);
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }


}
