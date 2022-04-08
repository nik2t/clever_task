package ru.clevertec;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Products {
    @Getter
    @Setter
    protected CustomArrayList<Product> products ;

    public Products() {
        this.products = new CustomArrayList<>();
    }

    public void productsAdd(int id , String name, double price, boolean specialOffer){
        products.add(new Product (id, name, price, specialOffer));
    }



    public void clear(){
        this.products = null;
    }
}
