package Exo3;

/**
 * Represents an unit product of the media store.
 * @author bsauvan
 */
public abstract class UnitProduct extends Product {
    private static final long serialVersionUID = 1L;

    protected double price;

    /**
     * Creates an UnitProduct.
     * 
     * @param priority Priority of the product to print it among others.
     * @param reference The reference of the unit product.
     * @param description The description of the unit product.
     * @param price The unit price of the unit product.
     */
    protected UnitProduct(int priority, String reference, String description, double price) {
        super(priority, reference, description);
        this.price = price;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getPrice() {
        return this.price;
    }

    /**
     * Sets the unit price of the unit product.
     * 
     * @param price The unit price of the unit product.
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
