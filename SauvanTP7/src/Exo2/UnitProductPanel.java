package Exo2;

import java.awt.event.ActionEvent;

import javax.swing.JTextField;



/**
 * Panel to display {@link UnitProduct}'s information.
 * 
 * @author bsauvan
 */
public class UnitProductPanel extends ProductPanel {
    private static final long serialVersionUID = 1L;

    private JTextField priceTextField;

    /**
     * Creates a UnitProductPanel.
     * 
     * @param unitProduct The unitProduct to display into the panel.
     */
    public UnitProductPanel(UnitProduct unitProduct) {
        super(unitProduct);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addSpecificInformation() {
        this.priceTextField = this.addInformationLine("Prix :", String.valueOf(this.product.getPrice()));
        this.priceTextField.setEditable(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ((UnitProduct) this.product).setPrice(Double.parseDouble(this.priceTextField.getText()));
        super.actionPerformed(e);
    }
}
