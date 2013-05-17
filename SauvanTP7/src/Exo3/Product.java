package Exo3;

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

    protected String description;

    /**
     * Creates a Product.
     * 
     * @param priority Priority of the product to print it among others.
     * @param reference The reference of the product.
     * @param description The description of the product.
     */
    protected Product(int priority, String reference, String description) {
        this.priority = priority;
        this.reference = reference;
        this.description = description;
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
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description of the product.
     * 
     * @param description The description of the product.
     */
    public void setDescription(String description) {
        this.description = description;
    }

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
