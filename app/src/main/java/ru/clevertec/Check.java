package ru.clevertec;

import lombok.Getter;
import lombok.Setter;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Check {
    @Getter
    @Setter
    protected ArrayList<Purchase> purchases;
    @Getter
    @Setter
    protected double discount = 0.10;
    @Getter
    @Setter
    protected int requiredQuantity = 5;
    @Getter
    @Setter
    protected double total = 0;
    @Getter
    @Setter
    protected double totalDiscount = 0;
    @Getter
    @Setter
    protected double cardInfo;
    @Getter
    @Setter
    protected double cardDiscount = 0;

    public void formation(double cardInfo){
        this.cardInfo = cardInfo;
        for(Purchase product : purchases){
            if(product.specialOffer && product.quantity >= requiredQuantity){
                    totalDiscount +=  purchases.get(purchases.indexOf(product)).totalPrice * discount;
                    purchases.get(purchases.indexOf(product)).totalPrice = purchases.get(purchases.indexOf(product)).totalPrice*(1 - discount);
            }
            total += purchases.get(purchases.indexOf(product)).totalPrice;
        }
        cardDiscount = total * cardInfo;
        total -= cardDiscount;


    }
    public Check(ArrayList<Purchase> purchases, double cardInfo) {
        this.purchases = purchases;
        formation(cardInfo);
    }
    public void printCheck(){
        DecimalFormat df = new DecimalFormat("###.##");
        System.out.println("           CASH RECEIPT          ");
        System.out.println("=================================");
        System.out.printf("%-9s %-3s %-4s   %-4s\n","Title",  "Quantity", " Price", "Total");
        System.out.println("---------------------------------");
        for(Purchase product : purchases){
            System.out.printf("%-14s %-4s $%-4s   $%-4s\n", product.name, product.quantity , df.format(product.price),df.format(product.totalPrice));
        }
        System.out.println("=================================");
        System.out.println("TOTAL:                     " + "$"+df.format(total));
        System.out.println("Promotions(10%)              " + "$"+df.format(totalDiscount));
        System.out.println("CardDiscount("+cardInfo+")          " +"$"+ df.format(cardDiscount));
        System.out.println("---------------------------------");

    }
    public String indents(String str, int counter){
        counter -= str.length();
        String strcount = "";
        for(int i = 0 ; i < counter;i++){
            strcount +=" ";
        }
        return strcount;
    }


    public void printCheckFile(){
        try(FileWriter writer = new FileWriter("../app/resources/check.txt", false))
        {
            DecimalFormat df = new DecimalFormat("###.##");
            writer.write("           CASH RECEIPT          \n");
            writer.write("=================================\n");
            String str;
            str = "Title";
            str += indents("Title", 10);
            str += "Quantity";
            str += indents("Quantity", 11);
            str += "Price";
            str += indents("Price", 7);
            str += "Total";
            str+="\n";
            writer.write(str);
            writer.write("---------------------------------\n");

            for(Purchase product : purchases){
                str = "";
                str += product.name;
                str += indents(product.name, 14);
                str += product.quantity;
                str += indents(Integer.toString(product.quantity), 8);
                str+="$";
                str += df.format(product.price);
                str += indents(df.format(product.price), 5);
                str+="$";
                str += df.format(product.totalPrice);
                str+="\n";
                writer.write(str);
            }
            writer.write("=================================\n");
            writer.write("TOTAL:                     " + "$"+df.format(total)+"\n");
            writer.write("Promotions(10%)              " + "$"+df.format(totalDiscount)+"\n");
            writer.write("CardDiscount("+cardInfo+")          " +"$"+ df.format(cardDiscount)+"\n");
            writer.write("---------------------------------");


            writer.flush();

        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }

    }


}
