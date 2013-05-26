package Exo1;



/**
 * Represents a lot of unit products.
 * 
 * @author bsauvan
 */
public class Lot extends Product {
    private final UnitProduct product;

    private final int nbProducts;

    private final double discount;

    /**
     * Creates a Lot.
     * 
     * @param reference The reference of the lot.
     * @param product The unit product that composes the lot.
     * @param nbProducts The number of the unit products that composes the lot.
     * @param discount The discount for the lot.
     */
    public Lot(String reference, UnitProduct product, int nbProducts, double discount) {
        super(reference);
        this.product = product;
        this.nbProducts = nbProducts;
        this.discount = discount;
    }

    /**
     * Returns the unit product that composes the lot.
     * 
     * @return The unit product that composes the lot.
     */
    public UnitProduct getProduct() {
        return product;
    }

    /**
     * Returns the number of the unit products that composes the lot.
     * 
     * @return The number of the unit products that composes the lot.
     */
    public int getNbProducts() {
        return nbProducts;
    }

    /**
     * Returns the discount for the lot.
     * 
     * @return The discount for the lot.
     */
    public double getDiscount() {
        return discount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return "Lot de " + this.nbProducts + " " + this.product.getDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getPrice() {
        return this.nbProducts * this.product.getPrice() * (100 - this.discount) / 100;
    }
}
