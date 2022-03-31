package ru.clevertec;

import lombok.Getter;
import lombok.Setter;

public class Card {
    @Getter
    @Setter
    protected double discount;
    @Getter
    @Setter
    protected String cardNumber;

    public Card(double discount, String cardNumber) {
        this.discount = discount;
        this.cardNumber = cardNumber;
    }


}
