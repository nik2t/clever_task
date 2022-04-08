package ru.clevertec;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CheckRunner {

    private static String curdNumb;
    private static double cardInfo;

    public static void main(String[] args) throws MyException, IOException {


        args = new String[]{"../app/resources/test.txt"};
        String info = "../app/resources/info.txt";
        cleanInvalidData();

        Products products = new Products();
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(0.03, "1234"));
        cards.add(new Card(0.07, "1423"));
        cards.add(new Card(0.1, "1010"));
        ArrayList<Purchase> purchases = new ArrayList<Purchase>();


        ArrayList<String[]> strList = firstReader(info, 0);
        for (String[] strL : strList) {
            boolean bCounter = false;
            if (strL[3].equals("+")) {
                bCounter = true;
            }
            products.productsAdd(Integer.parseInt(strL[0]), strL[1], Double.valueOf(strL[2]), bCounter);
        }

        ArrayList<String[]> strListTest = firstReader(args[0], 1);

        for (Card card : cards) {
            if (card.cardNumber.equals(curdNumb)) {
                cardInfo = card.getDiscount();
            }
        }
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


        String[] arr = new String[]{"dream", "pool", "red", null, "pleases"};
        //Integer[] arr = new Integer[]{1000, 930, 123, 68, 14};
        CustomArrayList<String> list = new CustomArrayList<>(arr);
        CustomArrayIterator<String> iterator = list.iterator();
        for(String elem : list){
            System.out.println(elem);
        }
        System.out.println("_________________");
        list.add("hello");
        list.remove(3);
        list.set(2, "HELSIIDJ");
        System.out.println("hello -> index: " + list.find("hello"));



        for(String elem : list){
            System.out.println(elem);
        }

    }

    public static ArrayList<String[]> firstReader(String file, int counter) throws FileNotFoundException {

        String str;
        ArrayList<String[]> list = new ArrayList<String[]>();
        FileReader reader = new FileReader(file);
        Scanner scanner = new Scanner(reader);
        while (scanner.hasNextLine()) {
            str = scanner.nextLine();
            if (regexValidation(str, counter)){
                list.add(str.split(";"));
            }
        }
        return list;
    }

    public static boolean regexValidation(String str, int counter) {
        try (FileWriter writer = new FileWriter("../app/resources/invalidData.txt", true)) {
            if(counter == 0) {
                if (str.matches("^([1-9][0-9]?|100);[A-Z][a-z]{2,};([1-9][0-9]?|100)\\.[0-9]{2};(\\+|\\-)$")) { return true; }
                else{ writer.write("(info.txt):"+str+"\n"); }
            }
            else{
                if (str.matches("^([1-9][0-9]?|100);[A-Z][a-z]{2,};([1-9][0-9]?|100)\\.[0-9]{2};([1-9]|1[0-9]|20)$")) { return true; }
                else{ writer.write("(test.txt):"+str+"\n"); }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(str.matches("card-[0-9]{4}")){
            curdNumb = str.split("-")[1];
        }
        return false;
    }

    public static void cleanInvalidData() throws IOException {
        FileWriter writer = null;
        try {
            writer = new FileWriter("../app/resources/invalidData.txt", false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.write("");
    }

}

