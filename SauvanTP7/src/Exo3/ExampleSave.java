package Exo3;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import Exo3.DVD.DVDKind;



/**
 * Class used to save the stocks in a file.
 * 
 * @author bsauvan
 */
public class ExampleSave {
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

        Stocks stocks = Stocks.getInstance();
        stocks.addProductStock(dvd1, 50);
        stocks.addProductStock(dvd2, 30);
        stocks.addProductStock(dvd3, 60);
        stocks.addProductStock(book1, 20);
        stocks.addProductStock(book2, 25);
        stocks.addProductStock(book3, 20);
        stocks.addProductStock(book4, 10);
        stocks.addProductStock(lot1, 5);
        stocks.addProductStock(lot2, 8);

        System.out.println("Parcours des stocks:");
        for (Stocks.ProductStock productStock : stocks.getProductStocks().values()) {
        	System.out.println(productStock);
        }
        System.out.println();

        try (FileOutputStream fos = new FileOutputStream("stocks.data");
                ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(stocks);

            System.out.println("Etat des stocks sauvegarde");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
