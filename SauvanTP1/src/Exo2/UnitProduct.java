package Exo2;



/**
 * Represents an unit product of the media store.
 * @author bsauvan
 */
public abstract class UnitProduct extends Product {
    protected double price;

    /**
     * Creates an UnitProduct.
     * 
     * @param priority Priority of the product to print it among others.
     * @param reference The reference of the unit product.
     * @param price The unit price of the unit product.
     */
    protected UnitProduct(int priority, String reference, double price) {
        super(priority, reference);
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
