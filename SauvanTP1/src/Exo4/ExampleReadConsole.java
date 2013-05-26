package Exo4;

import java.util.InputMismatchException;
import java.util.Scanner;

import Exo4.DVD.DVDKind;



/**
 * Class used to read new products from the standard input.
 * 
 * @author bsauvan
 */
public class ExampleReadConsole {
    public static Scanner scanner;

    public static Stocks stocks;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        stocks = Stocks.getInstance();

        while (true) { // boucle infine  
            System.out.println("Quel type de produit voulez-vous ajouter: DVD ou livre?");
            String kind = scanner.nextLine();

            if (kind.equalsIgnoreCase("DVD")) {
                Product dvd = readDVDFromStandardInput();
                int nbProducts = readNbProductsFromStandardInput();

                stocks.addProductStock(dvd, nbProducts);
                System.out.println("DVD cree.");
            } else if (kind.equalsIgnoreCase("livre")) {
                Product livre = readBookFromStandardInput();
                int nbProducts = readNbProductsFromStandardInput();

                stocks.addProductStock(livre, nbProducts);
                System.out.println("Livre cree.");
            } else {
                System.out.println("Veuillez entrer DVD ou livre");
            }

            System.out.println("Voulez-vous continuer (oui/non)?");
            String again = scanner.nextLine();
            if (again.equalsIgnoreCase("non")) {
                break;
            }
        }

        scanner.close();

        System.out.println("Liste de tous les produits en stock:");
        for (Stocks.ProductStock productStock : stocks.getProductStocks().values()) {
            System.out.println(productStock);
        }
    }

    private static Product readDVDFromStandardInput() {
        String reference = readReferenceFromStandardInput();

        System.out.println("Entrer un nom:");
        String name = scanner.nextLine();

        System.out.println("Entrer un realisateur:");
        String filmDirector = scanner.nextLine();

        DVDKind kind = null;
        while (kind == null) {
            System.out.println("Quel est le genre: movie, documentary, tv_show ou kids?");
            try {
                kind = DVDKind.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Veuillez entrer un des choix propos�s");
            }
        }

        double unitPrice = readUnitPriceFromStandardInput();

        return new DVD(reference, name, filmDirector, kind, unitPrice);
    }

    private static Product readBookFromStandardInput() {
        String reference = readReferenceFromStandardInput();

        System.out.println("Entrer un titre:");
        String title = scanner.nextLine();

        System.out.println("Entrer un auteur:");
        String author = scanner.nextLine();

        System.out.println("Entrer un editeur:");
        String publisher = scanner.nextLine();

        double unitPrice = readUnitPriceFromStandardInput();

        return new Book(reference, title, author, publisher, unitPrice);
    }

    private static String readReferenceFromStandardInput() {
        String reference = null;

        while (reference == null) {
            System.out.println("Entrer une reference:");

            reference = scanner.nextLine();

            if (stocks.getProductStock(reference) != null) {
                System.out.println("Cette reference existe deja");
                reference = null;
            }
        }

        return reference;
    }

    private static double readUnitPriceFromStandardInput() {
        double unitPrice = 0;

        while (unitPrice <= 0) {
            System.out.println("Entrer le prix unitaire:");

            try {
                unitPrice = scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Veuillez entrer un nombre superieur a 0");
                scanner.next();
                continue;
            }

            if (unitPrice <= 0) {
                System.out.println("Veuillez entrer un nombre superieur a 0");
            }
        }

        return unitPrice;
    }

    private static int readNbProductsFromStandardInput() {
        int nbProducts = 0;

        while (nbProducts <= 0) {
            System.out.println("Combien d'exemplaires sont disponibles?");

            try {
                nbProducts = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Veuillez entrer un nombre entier superieur a 0");
                scanner.next();
                continue;
            }

            if (nbProducts <= 0) {
                System.out.println("Veuillez entrer un nombre entier superieur a 0");
            }
        }

        // Il faut vider la ligne apres lecture d'un type autre que String pour pouvoir relire une ligne apres
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }

        return nbProducts;
    }
}
