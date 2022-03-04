package comp;

import java.util.ArrayList;

public class Products {
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
