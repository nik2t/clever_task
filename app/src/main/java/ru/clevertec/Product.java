package ru.clevertec;

import lombok.Getter;
import lombok.Setter;

public class Product {
    @Getter
    @Setter
    protected int id;
    @Getter
    @Setter
    protected String name;
    @Getter
    @Setter
    protected double price;
    @Getter
    @Setter
    protected boolean specialOffer;

    public Product(int id, String name, double price, boolean specialOffer) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.specialOffer = specialOffer;
    }



}
