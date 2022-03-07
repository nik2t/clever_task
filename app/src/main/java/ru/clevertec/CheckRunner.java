package ru.clevertec;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CheckRunner {

    public static void main(String[] args) throws MyException, FileNotFoundException {

        //args = new String[]{"4-5", "2-6", "3-8", "12-1", "card-1234"};   //initial data options
        args = new String[]{"test.txt"};

        Products products = new Products();
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(0.03, "1234"));
        cards.add(new Card(0.07, "1423"));
        cards.add(new Card(0.1, "1010"));
        ArrayList<Purchase> purchases = new ArrayList<Purchase>();
        String name; double price;
        name ="Sprite"; price=4.8;
        products.productsAdd(name, price, true);
        name ="Fanta"; price=6.3;
        products.productsAdd(name, price, false);
        name ="Rosse pasta"; price=7.25;
        products.productsAdd(name, price, true);
        name ="Jacobs coffee"; price=0.70;
        products.productsAdd(name, price, false);
        name ="Dirol"; price=4.3;
        products.productsAdd(name, price, false);
        name ="Orbit mint"; price=3.1;
        products.productsAdd(name, price, false);
        name ="Milk"; price=5.3;
        products.productsAdd(name, price, false);
        name ="Cheese"; price=6.45;
        products.productsAdd(name, price, false);
        name ="Cucumber"; price=1.5;
        products.productsAdd(name, price, true);
        name ="Tomatoes"; price=1.3;
        products.productsAdd(name, price, false);
        name ="Sauce"; price=10.1;
        products.productsAdd(name, price, false);
        name ="Red pepper"; price=0.9;
        products.productsAdd(name, price, false);
        name ="Chips"; price=4;
        products.productsAdd(name, price, true);
        name ="Sausages"; price=6.25;
        products.productsAdd(name, price, true);
        name ="Olive oil"; price=16;
        products.productsAdd(name, price, true);

        double cardInfo = 0;
        if(args.length == 0){
            throw new MyException("No required data!");
        }
        String str = args[0];

        if(str.indexOf("-") == -1) {
            FileReader reader = new FileReader(args[0]);

            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine()) {
                str = scanner.nextLine();
                String delimeter = " ";
                args = str.split(delimeter);

            }
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        for (String arg : args) {
            int counterId = 0;
            int counterQuantity = 0;
            String[] subStr;
            String delimeter = "-";
            subStr = arg.split(delimeter);

            if (subStr[0].equals("card")) {
                for (Card card : cards) {
                    if (card.cardNumber.equals(subStr[1])) {
                        cardInfo = card.getDiscount();
                    }
                }
            } else {
                try {
                    counterId = Integer.parseInt(subStr[0]);
                    counterQuantity = Integer.parseInt(subStr[1]);
                }catch (Exception e) {
                    e.printStackTrace();
                }
                for (Product product : products.products) {

                    if (product.id == counterId) {
                        purchases.add(new Purchase(product.id, product.name, product.price,
                                product.specialOffer, counterQuantity, product.price * counterQuantity));
                    }
                }
            }
        }
        Check check = new Check(purchases, cardInfo);
        check.printCheck();
        check.printCheckFile();


    }
}
