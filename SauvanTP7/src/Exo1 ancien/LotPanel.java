package Exo1;

import java.awt.event.ActionEvent;

import javax.swing.JTextField;



/**
 * Panel to display {@link Lot}'s information.
 * 
 * @author bsauvan
 */
public class LotPanel extends ProductPanel {
    private static final long serialVersionUID = 1L;

    private JTextField nbProductsTextField;

    private JTextField discountTextField;

    /**
     * Creates a LotPanel.
     * 
     * @param lot The lot to display into the panel.
     */
    public LotPanel(Lot lot) {
        super(lot);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addSpecificInformation() {
        this.nbProductsTextField = this.addInformationLine("Nombre de produits :",
                String.valueOf(((Lot) this.product).getNbProducts()));
        this.nbProductsTextField.setEditable(true);
        this.discountTextField = this.addInformationLine("Reduction :",
                String.valueOf(((Lot) this.product).getDiscount()));
        this.discountTextField.setEditable(true);
        this.addInformationLine("Prix :", String.valueOf(this.product.getPrice()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ((Lot) this.product).setNbProducts(Integer.parseInt(this.nbProductsTextField.getText()));
        ((Lot) this.product).setDiscount(Double.parseDouble(this.discountTextField.getText()));
        super.actionPerformed(e);
    }
}
