package Exo3;



/**
 * Represents an exception which is raised when trying to remove an order from an invoice with an invalid index.
 * 
 * @author bsauvan
 */
public class NoSuchOrderIndexException extends Exception {
    private static final long serialVersionUID = 1L;

    private int orderIndex;

    /**
     * Creates a NoSuchOrderIndexException.
     */
    public NoSuchOrderIndexException() {
        this("Numero de commande invalide");
    }

    /**
     * Creates a NoSuchOrderIndexException.
     * 
     * @param message The message of the exception.
     */
    public NoSuchOrderIndexException(String message) {
        super(message);
    }

    /**
     * Creates a NoSuchOrderIndexException.
     * 
     * @param orderIndex The order index which raised the exception.
     */
    public NoSuchOrderIndexException(int orderIndex) {
        this("Numero de commande invalide", orderIndex);
    }

    /**
     * Creates a NoSuchOrderIndexException.
     * 
     * @param cause The original exception.
     */
    public NoSuchOrderIndexException(Throwable cause) {
        this("Numero de commande invalide", cause);
    }

    /**
     * Creates a NoSuchOrderIndexException.
     * 
     * @param message The message of the exception.
     * @param orderIndex The order index which raised the exception.
     */
    public NoSuchOrderIndexException(String message, int orderIndex) {
        super(message);
        this.orderIndex = orderIndex;
    }

    /**
     * Creates a NoSuchOrderIndexException.
     * 
     * @param message The message of the exception.
     * @param cause The original exception.
     */
    public NoSuchOrderIndexException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a NoSuchOrderIndexException.
     * 
     * @param orderIndex The order index which raised the exception.
     * @param cause The original exception.
     */
    public NoSuchOrderIndexException(int orderIndex, Throwable cause) {
        this("Numero de commande invalide", orderIndex, cause);
    }

    /**
     * Creates a NoSuchOrderIndexException.
     * 
     * @param message The message of the exception.
     * @param orderIndex The order index which raised the exception.
     * @param cause The original exception.
     */
    public NoSuchOrderIndexException(String message, int orderIndex, Throwable cause) {
        super(message, cause);
        this.orderIndex = orderIndex;
    }

    /**
     * Returns the order index which raised the exception.
     * 
     * @return The order index which raised the exception.
     */
    public int getOrderIndex() {
        return this.orderIndex;
    }
}
