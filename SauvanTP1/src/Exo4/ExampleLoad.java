package Exo4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;



/**
 * Class used to load the stocks from a file.
 * 
 * @author bsauvan
 */
public class ExampleLoad {
    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("stocks.data");
                ObjectInputStream ois = new ObjectInputStream(fis);) {
            Stocks stockschargerd = (Stocks) ois.readObject();

            System.out.println("Etat des stocks charge");
            System.out.println();

            System.out.println("Parcours des stocks:");
            for (Stocks.ProductStock productStock : stockschargerd.getProductStocks().values()) {
                System.out.println(productStock);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
