package Exo5;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager; 
import java.util.logging.Logger;

import Exo5.DVD.DVDKind;



/**
 * Class used as example.
 * 
 * @author bsauvan
 */
public class Example {
    private static final Logger logger = Logger.getLogger(Example.class.getName());

    public static void main(String[] args) {
    	// Configuration par fichier des logger 
    	try {
            LogManager.getLogManager().readConfiguration(
                    new FileInputStream("loggers-configuration.properties"));
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
        // Configuration dynamique des loggers:
        //        Logger rootLogger = Logger.getLogger("");
        //        rootLogger.getHandlers()[0].setLevel(Level.WARNING);
        //        rootLogger.setLevel(Level.ALL);
        //        try {
        //            FileHandler fh = new FileHandler("output.log");
        //            fh.setFormatter(new SimpleFormatter());
        //            rootLogger.addHandler(fh);
        //        } catch (SecurityException | IOException e) {
        //            e.printStackTrace();
        //        }

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

        stocks.decreaseStock(dvd1, 5);
        stocks.decreaseStock(lot2, 9);
        System.out.println();

        System.out.println("Parcours des stocks:");
        for (Stocks.ProductStock productStock : stocks.getProductStocks().values()) {
            System.out.println(productStock);
        }
        System.out.println();

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
            logger.severe(e.getMessage());
        }
    }
}
