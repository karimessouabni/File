package Exo1;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;




/**
 * Represents an invoice of an order composed of {@link Product products}.
 * 
 * @author bsauvan
 */
public class Invoice {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    private static final int MAX_LINES = 20;

    private static int nextInvoiceNb = 1;

    private final int invoiceNb;

    private final String date;

    private final String client;

    private final Line[] lines;

    private int currentIndex;

    /**
     * Creates an Invoice.
     * 
     * @param client Name of the client.
     */
    public Invoice(String client) {
        this.invoiceNb = nextInvoiceNb++;
        this.date = DATE_FORMAT.format(new Date());
        this.client = client;
        this.lines = new Line[MAX_LINES];
        this.currentIndex = 0;
    }

    /**
     * Returns the invoice number.
     * 
     * @return The invoice number.
     */
    public int getInvoiceNb() {
        return this.invoiceNb;
    }

    /**
     * Returns the invoice date.
     * 
     * @return The invoice date.
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns the name of the client.
     * 
     * @return The name of the client.
     */
    public String getClient() {
        return client;
    }

    /**
     * Adds an order to the invoice.
     * 
     * @param product Product that composes the order.
     * @param nb Number of products that composes the order.
     * @return True if the order has been successfully added, false otherwise.
     */
    public boolean addOrder(Product product, int nb) {
        if (this.currentIndex < this.lines.length) {
            this.lines[this.currentIndex] = new Line(product, nb);
            this.currentIndex++;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the total price of the invoice.
     * 
     * @return The total price of the invoice.
     */
    public double getTotalPrice() {
        double totalPrice = 0;

        for (int i = 0; i < this.currentIndex; i++) {
            totalPrice += this.lines[i].getTotalPrice();
        }

        return totalPrice;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Facture No ");
        sb.append(this.invoiceNb);
        sb.append(" en date du ");
        sb.append(this.date);
        sb.append(" pour ");
        sb.append(this.client);
        sb.append("\n");
        for (int i = 0; i < this.currentIndex; i++) {
            sb.append("   ");
            sb.append(this.lines[i].toString());
            sb.append("\n");
        }
        sb.append("Le prix total est de ");
        sb.append(this.getTotalPrice());
        sb.append(" Euros");

        return sb.toString();
    }

    /**
     * Represents a line (An order) of the invoice.
     * 
     * @author bsauvan
     */
    public static class Line {
        private final Product product;

        private final int nb;

        /**
         * Creates a Line.
         * 
         * @param product Product that composes the order.
         * @param nb Number of products that composes the order.
         */
        public Line(Product product, int nb) {
            this.product = product;
            this.nb = nb;
        }

        /**
         * Returns the total price for the order.
         * 
         * @return The total price for the order.
         */
        public double getTotalPrice() {
            return this.nb * this.product.getPrice();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return this.nb + " x " + this.product.getDescription() + " a " + this.product.getPrice() +
                " Euros l'unite";
        }
    }
}
