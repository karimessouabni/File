package Exo3;

import Exo3.DVD.DVDKind;




/**
 * Class used as example.
 * 
 * @author bsauvan
 */
public class Example {
    public static void main(String[] args) {
        Product dvd1 = new DVD("dvd1", "Le seigneur des Anneaux", "Peter Jackson", DVDKind.MOVIE, 15);
        Product dvd2 = new DVD("dvd2", "Les 2 tours", "Peter Jackson", DVDKind.MOVIE, 15);
        Product dvd3 = new DVD("dvd3", "Le retour du Roi", "Peter Jackson", DVDKind.MOVIE, 15);
        Product book1 = new Book("book1", "The Java Programming Language",
            "Ken Arnold, James Gosling, David Holmes", "Addison-Wesley Professional", 35);
        Product book2 = new Book("book2", "Head First Java", "Kathy Sierra, Bert Bates", "O'Reilly Media", 20);
        Product book3 = new Book("book3", "The elements of Java style", "Scott Ambler, Alan Vermeulen",
            "Cambridge University Press", 20);
        Product book4 = new Book("book4", "Bitter Java", "Bruce Tate", "Manning Publications", 15);
        Product lot1 = new Lot("lot1", (UnitProduct) book3, 10, 15);
        Product lot2 = new Lot("lot2", (UnitProduct) book4, 20, 25);

        Invoice invoice = new Invoice("Bastien Sauvan");
        invoice.addOrder(dvd1, 1);
        invoice.addOrder(lot1, 2);
        invoice.addOrder(dvd2, 1);
        invoice.addOrder(book2, 15);
        invoice.addOrder(lot2, 3);
        invoice.addOrder(dvd3, 1);
        invoice.addOrder(book1, 10);

        System.out.println(invoice);
        System.out.println();

        try {
            invoice.removeOrder(8);
        } catch (NoSuchOrderIndexException e) {
            System.err.println(e.getMessage());
            System.err.println("Exception levee pour l'index " + e.getOrderIndex());
            e.printStackTrace();
        }
    }
}
