package Exo5;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;



/**
 * Represents the stocks of the media store.
 * 
 * @author bsauvan
 */
public class Stocks implements Serializable {
    private static final Logger logger = Logger.getLogger(Stocks.class.getName());

    private static final long serialVersionUID = 1L;

    private final Map<String, ProductStock> productStocks;

    private static Stocks instance;

    private Stocks() {
        this.productStocks = new HashMap<>();
    }

    /**
     * Returns the unique instance of Stocks.
     * 
     * @return The unique instance of Stocks.
     */
    public static Stocks getInstance() {
        if (instance == null) {
            instance = new Stocks();

            logger.info("Nouvelle instance de Stocks cree");
        }

        return instance;
    }

    /**
     * Returns the stock of a product.
     * 
     * @param reference The reference of the product.
     * @return The stock of a product.
     */
    public ProductStock getProductStock(String reference) {
        return this.productStocks.get(reference);
    }

    /**
     * Returns the stocks of the media store.
     * 
     * @return The stocks of the media store.
     */
    public Map<String, ProductStock> getProductStocks() {
        return this.productStocks;
    }

    /**
     * Adds a number of products to the stocks.
     * 
     * @param product The product to add to the stocks.
     * @param nbProducts The number of products to add.
     */
    public void addProductStock(Product product, int nbProducts) {
        if (!this.productStocks.containsKey(product.getReference())) {
            this.productStocks.put(product.getReference(), new ProductStock(product, nbProducts));

            logger.fine("Ajout du nouveau produit " + product.getReference() + " (+" + nbProducts +
                ") aux stocks");
        } else {
            this.productStocks.get(product.getReference()).removeNbProducts(nbProducts);

            logger.fine("Mise a jour du stock du produit " + product.getReference() + " (+" + nbProducts +
                ")");
        }
    }

    /**
     * Removes a number of products of the stocks.
     * 
     * @param product The product to decrease of the stocks.
     * @param nbProducts The number of products to remove.
     */
    public void decreaseStock(Product product, int nbProducts) {
        if (this.productStocks.containsKey(product.getReference())) {
            this.productStocks.get(product.getReference()).removeNbProducts(nbProducts);

            logger.fine("Mise a jour du stock du produit " + product.getReference() + " (-" + nbProducts +
                ")");
        } else {
            logger.warning("Reference produit inconnue " + product.getReference());
        }
    }

    /**
     * Represents the stock of a product.
     * 
     * @author bsauvan
     */
    public static class ProductStock implements Serializable {
        private static final long serialVersionUID = 1L;

        private final Product product;

        private int nbProducts;

        /**
         * Creates a ProductStock.
         * 
         * @param product The product.
         * @param nbProducts The number of products available.
         */
        public ProductStock(Product product, int nbProducts) {
            this.product = product;
            this.nbProducts = nbProducts;
        }

        /**
         * Adds products to the stock.
         * 
         * @param nbProducts The number of products to add.
         */
        public void addNbProducts(int nbProducts) {
            this.nbProducts = nbProducts;
        }

        /**
         * Removes products of the stock.
         * 
         * @param nbProducts The number of products to remove.
         */
        public void removeNbProducts(int nbProducts) {
            if ((this.nbProducts - nbProducts) >= 0) {
                this.nbProducts -= nbProducts;
            } else {
                logger.severe("Tentative de retrait de plus d'examplaire du produit " +
                    this.product.getReference() + " qu'il n'y en a de disponibles (" + this.nbProducts +
                    " disponibles, " + nbProducts + " demandes)");
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int hashCode() {
            return this.product.getReference().hashCode();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean equals(Object obj) {
            if (obj instanceof ProductStock) {
                return this.product.getReference().equals(((ProductStock) obj).product.getReference());
            } else {
                return false;
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return "Il reste " + this.nbProducts + " " + this.product.getDescription();
        }
    }
}
