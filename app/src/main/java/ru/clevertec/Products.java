package ru.clevertec;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Products {
    @Getter
    @Setter
    protected ArrayList<Product> products ;

    public Products() {
        this.products = new ArrayList<>();
    }

    public void productsAdd( String name, double price, boolean specialOffer){
        products.add(new Product (products.size()+1, name, price, specialOffer));
    }



    public void clear(){
        this.products = null;
    }
}
