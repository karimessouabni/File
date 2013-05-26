package Exo2;



/**
 * Represents a DVD which is an unit product.
 * 
 * @author bsauvan
 */
public class DVD extends UnitProduct {
    /**
     * Represents the different kind of DVD.
     * 
     * @author bsauvan
     */
    public enum DVDKind {
        MOVIE("film"), DOCUMENTARY("documentaire"), TV_SHOW("serie"), KIDS("jeunesse");

        private String kind;

        DVDKind(String kind) {
            this.kind = kind;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return this.kind;
        }
    }

    private final String name;

    private final String filmDirector;

    private final DVDKind kind;

    /**
     * Creates a DVD.
     * 
     * @param reference The reference of the DVD.
     * @param name The name of the DVD.
     * @param filmDirector The film director of the DVD.
     * @param kind The kind of the DVD.
     * @param unitPrice The unit price of the DVD.
     */
    public DVD(String reference, String name, String filmDirector, DVDKind kind, double unitPrice) {
        super(2, reference, unitPrice);
        this.name = name;
        this.filmDirector = filmDirector;
        this.kind = kind;
    }

    /**
     * Returns the name of the DVD.
     * 
     * @return The name of the DVD.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the film director of the DVD.
     * 
     * @return The film director of the DVD.
     */
    public String getFilmDirector() {
        return this.filmDirector;
    }

    /**
     * Returns the kind of the DVD.
     * 
     * @return The kind of the DVD.
     */
    public DVDKind getKind() {
        return this.kind;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return "DVD " + this.getReference() + ", " + this.name + " (" + this.kind + ") par " +
            this.filmDirector;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(Product product) {
        int priorityComparison = super.compareTo(product);

        if (priorityComparison == 0) {
            return this.name.compareTo(((DVD) product).getName());
        } else {
            return priorityComparison;
        }
    }
}
