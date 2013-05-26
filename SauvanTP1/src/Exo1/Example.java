package Exo1;

import Exo1.DVD.DVDKind;




/**
 * Class used as example.
 * 
 * @author bsauvan
 */
public class Example {
    public static void main(String[] args) {
        Product dvd = new DVD("dvd1", "Le seigneur des Anneaux", "Peter Jackson", DVDKind.MOVIE, 15);
        Product book = new Book("book1", "The Java Programming Language",
            "Ken Arnold, James Gosling, David Holmes", "Addison-Wesley Professional", 35);
        Product lot = new Lot("lot1", (UnitProduct) book, 10, 15);

        System.out.println("Un DVD: " + dvd.getDescription());
        System.out.println("Un lot: " + lot.getDescription());
        System.out.println();

        Invoice invoice = new Invoice("Bastien Sauvan");
        invoice.addOrder(lot, 1);
        invoice.addOrder(dvd, 2);

        System.out.println(invoice);
    }
}
