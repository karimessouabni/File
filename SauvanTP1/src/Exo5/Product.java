package Exo5;

import java.io.Serializable;


/**
 * Represents a product of the media store.
 * 
 * @author bsauvan
 */
public abstract class Product implements Comparable<Product>, Serializable {
    private static final long serialVersionUID = 1L;

    protected final Integer priority;

    protected final String reference;

    /**
     * Creates a Product.
     * 
     * @param priority Priority of the product to print it among others.
     * @param reference The reference of the product.
     */
    protected Product(int priority, String reference) {
        this.priority = priority;
        this.reference = reference;
    }

    /**
     * Returns the reference of the product.
     * 
     * @return The reference of the product.
     */
    public String getReference() {
        return this.reference;
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

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(Product product) {
        return this.priority.compareTo(product.priority);
    }
}
