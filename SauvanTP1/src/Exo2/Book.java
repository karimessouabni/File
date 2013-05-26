package Exo2;



/**
 * Represents a book which is an unit product.
 * 
 * @author bsauvan
 */
public class Book extends UnitProduct {
    private final String title;

    private final String author;

    private final String publisher;

    /**
     * Creates a Book.
     * 
     * @param reference The reference of the book.
     * @param title The title of the book.
     * @param author The author of the book.
     * @param publisher The publisher of the book.
     * @param unitPrice The unit price of the book.
     */
    public Book(String reference, String title, String author, String publisher, double unitPrice) {
        super(3, reference, unitPrice);
        this.title = title;
        this.author = author;
        this.publisher = publisher;
    }

    /**
     * Returns the title of the book.
     * 
     * @return The title of the book.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Returns the author of the book.
     * 
     * @return The author of the book.
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * Returns the publisher of the book.
     * 
     * @return The publisher of the book.
     */
    public String getPublisher() {
        return this.publisher;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return "Livre " + this.getReference() + ", " + this.title + " par " + this.author;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(Product product) {
        int priorityComparison = super.compareTo(product);

        if (priorityComparison == 0) {
            return this.title.compareTo(((Book) product).getTitle());
        } else {
            return priorityComparison;
        }
    }
}
