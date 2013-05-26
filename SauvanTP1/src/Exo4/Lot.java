package Exo4;

/**
 * Represents a lot of unit products.
 * 
 * @author bsauvan
 */
public class Lot extends Product {
    private static final long serialVersionUID = 1L;

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
        super(1, reference);
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
        return this.product;
    }

    /**
     * Returns the number of the unit products that composes the lot.
     * 
     * @return The number of the unit products that composes the lot.
     */
    public int getNbProducts() {
        return this.nbProducts;
    }

    /**
     * Returns the discount for the lot.
     * 
     * @return The discount for the lot.
     */
    public double getDiscount() {
        return this.discount;
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
        return (this.nbProducts * this.product.getPrice() * (100 - this.discount)) / 100;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(Product product) {
        int priorityComparison = super.compareTo(product);

        if (priorityComparison == 0) {
            return ((Double) this.getPrice()).compareTo(((Lot) product).getPrice());
        } else {
            return priorityComparison;
        }
    }
}
