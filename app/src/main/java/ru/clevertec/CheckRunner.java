package ru.clevertec;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class CheckRunner {


    public static void main(String[] args) throws MyException, FileNotFoundException {

        //args = new String[]{"4-5", "2-6", "3-8", "12-1", "card-1234"};   //initial data options
        args = new String[]{"../app/resources/test.txt"};
        String info = "../app/resources/info.txt";

        double cardInfo = 0;
        Products products = new Products();
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(0.03, "1234"));
        cards.add(new Card(0.07, "1423"));
        cards.add(new Card(0.1, "1010"));
        ArrayList<Purchase> purchases = new ArrayList<Purchase>();


        ArrayList<String[]> strList = firstReader(info);
        for (String[] strL : strList) {
            boolean bCounter = false;
            if (strL[3].equals("+")) {
                bCounter = true;
            }
            products.productsAdd(Integer.parseInt(strL[0]), strL[1], Double.valueOf(strL[2]), bCounter);
        }

        ArrayList<String[]> strListTest = firstReader(args[0]);

        /*for (Card card : cards) {
            if (card.cardNumber.equals(subStr[1])) {
                cardInfo = card.getDiscount();
            }
        }*/
        for (String[] strL : strListTest) {
            for(String[] strCount : strList) {
                if(strL[0].equals(strCount[0]) && strL[1].equals(strCount[1]) && strL[2].equals(strCount[2])){
                    boolean bCounter = false;
                    if (strCount[3].equals("+")) {
                        bCounter = true;
                    }
                    purchases.add(new Purchase(Integer.parseInt(strL[0]), strL[1], Double.valueOf(strL[2]), bCounter,
                                                    Integer.parseInt(strL[3]),  Integer.parseInt(strL[3]) * Double.valueOf(strL[2])));
                }
            }
        }

        Check check = new Check(purchases, cardInfo);
        check.printCheck();
        check.printCheckFile();


    }

    public static ArrayList<String[]> firstReader(String file) throws FileNotFoundException {

        String str;
        ArrayList<String[]> list = new ArrayList<String[]>();
        FileReader reader = new FileReader(file);
        Scanner scanner = new Scanner(reader);
        while (scanner.hasNextLine()) {
            str = scanner.nextLine();
            String delimeter = ";";
            list.add(str.split(delimeter));
        }
        return list;
    }
}