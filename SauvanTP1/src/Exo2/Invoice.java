package Exo2;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


/**
 * Represents an invoice of an order composed of {@link Product products}.
 * 
 * @author bsauvan
 */
public class Invoice implements Iterable<Invoice.Line> {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    private static int nextInvoiceNb = 1;

    private final int invoiceNb;

    private final String date;

    private final String client;

    private final List<Line> lines;

    /**
     * Creates an Invoice.
     * 
     * @param client Name of the client.
     */
    public Invoice(String client) {
        this.invoiceNb = nextInvoiceNb++;
        this.date = DATE_FORMAT.format(new Date());
        this.client = client;
        this.lines = new ArrayList<>();
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
        return this.date;
    }

    /**
     * Returns the name of the client.
     * 
     * @return The name of the client.
     */
    public String getClient() {
        return this.client;
    }

    /**
     * Adds an order to the invoice.
     * 
     * @param product Product that composes the order.
     * @param nb Number of products that composes the order.
     * @return True if the order has been successfully added, false otherwise.
     */
    public boolean addOrder(Product product, int nb) {
        boolean success = this.lines.add(new Line(product, nb));

        if (success) {
            Collections.sort(this.lines);

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

        for (Line line : this.lines) {
            totalPrice += line.getTotalPrice();
        }

        return totalPrice;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<Line> iterator() {
        return this.lines.iterator();
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
        for (Line line : this.lines) {
            sb.append("   ");
            sb.append(line.toString());
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
    public static class Line implements Comparable<Line> {
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

        /**
         * {@inheritDoc}
         */
        @Override
        public int compareTo(Line line) {
            return this.product.compareTo(line.product);
        }
    }
}
