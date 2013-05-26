package Exo3;



import java.util.HashMap;
import java.util.Map;




/**
 * Represents the stocks of the media store.
 * 
 * @author bsauvan
 */
public class Stocks {
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
        }

        return instance;
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
        } else {
            this.productStocks.get(product.getReference()).removeNbProducts(nbProducts);
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
        }
    }

    /**
     * Represents the stock of a product.
     * 
     * @author bsauvan
     */
    public static class ProductStock {
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
