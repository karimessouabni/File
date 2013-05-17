package Exo3;

import java.util.logging.Logger;


/**
 * Represents a book which is an unit product.
 * 
 * @author bsauvan
 */
public class Book extends UnitProduct {
    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(Book.class.getName());

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
        super(3, reference, "Livre " + reference + ", " + title + " par " + author, unitPrice);
        this.title = title;
        this.author = author;
        this.publisher = publisher;

        logger.info(this.getDescription() + " cree");
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
    public int compareTo(Product product) {
        int priorityComparison = super.compareTo(product);

        if (priorityComparison == 0) {
            return this.title.compareTo(((Book) product).getTitle());
        } else {
            return priorityComparison;
        }
    }
}
