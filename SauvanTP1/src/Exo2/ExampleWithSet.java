package Exo2;





/**
 * Class used to test the class Stocks with a Set.
 * 
 * @author bsauvan
 */
public class ExampleWithSet {
    public static void main(String[] args) {
        Product book1 = new Book("book1", "The Java Programming Language",
            "Ken Arnold, James Gosling, David Holmes", "Addison-Wesley Professional", 35);
        Product book2 = new Book("book1", "The Java Programming Language",
            "Ken Arnold, James Gosling, David Holmes", "Addison-Wesley Professional", 35);
        Stocks stocks = Stocks.getInstance();
        stocks.addProductStock(book1, 10);
        stocks.addProductStock(book2, 20);

        /*
         * Normalement un Set ne contient pas 2 �l�ments qui sont �gaux au sens de equals. Donc
         * l'execution montre un fonctionnement anormal en ne s'apercevant pas qu'un �l�ment �gal a
         * d�j� �t� ajout� dans le Set, en ajoutant 2 �l�ments �gaux dedans.
         * 
         * Ce fonctionnement anormal vient de ce que le Set est impl�ment� avec la classe HashSet
         * qui range les valeurs dans une table de hachage dont les cl�s sont les hashCode des
         * �l�ments. Lorsqu'un �lement est ajout� dans le Set, celui-ci commence par v�rifier qu'il
         * ne contient pas d�j� un �l�ment �gal. Pour cela, il ne cherche l'�l�ment que dans le seau
         * (bucket) des cl�s qui ont la m�me valeur de hashCode. Ainsi, le Set ne s'apercevra pas
         * qu'il contient 2 �l�ments �gaux s'ils n'ont pas la m�me valeur de hashCode.
         * 
         * Ceci ne devrait jamais arriver car dans la sp�cification de la m�thode hashCode il est
         * �crit :
         * "If two objects are equal according to the equals(Object) method, then calling the hashCode method on each of the two objects must produce the same integer result."
         * 
         * Pour r�soudre ce probl�me, il faut impl�menter la m�thode hashCode dans
         * Stocks.ProductStock comme cela est fait dans cette correction.
         */

        System.out.println("Parcours des stocks:");
        for (Stocks.ProductStock productStock : stocks.getProductStocks()) {
            System.out.println(productStock);
        }
    }
}
