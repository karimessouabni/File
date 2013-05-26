package Exo1;


/**
 * Represents a product of the media store.
 * 
 * @author bsauvan
 */
public abstract class Product {
    protected final String reference;

    /**
     * Creates a Product.
     * 
     * @param reference The reference of the product.
     */
    protected Product(String reference) {
        this.reference = reference;
    }

    /**
     * Returns the reference of the product.
     * 
     * @return The reference of the product.
     */
    public String getReference() {
        return reference;
    }

    /**
     * Returns the description of the product.
     * 
     * @return The description of the product.
     */
    public abstract String getDescription();

    /**
     * Returns the price of the product.
     * 
     * @return The price of the product.
     */
    public abstract double getPrice();
}
