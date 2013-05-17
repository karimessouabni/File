package Exo3;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;
import javax.swing.text.TextAction;




/**
 * Panel to display {@link Product}'s information.
 * 
 * @author bsauvan
 */
public abstract class ProductPanel extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;

    protected Product product;

    protected GridBagConstraints constraints;

    protected JTextArea descriptionTextArea;

    private JPopupMenu popupMenu;

    /**
     * Creates a ProductPanel.
     * 
     * @param product The product to display into the panel.
     */
    protected ProductPanel(Product product) {
        this.product = product;

        this.setLayout(new GridBagLayout());

        this.constraints = new GridBagConstraints();
        // Aligns components
        this.constraints.anchor = GridBagConstraints.BASELINE;
        // Resizes components horizontally
        this.constraints.fill = GridBagConstraints.HORIZONTAL;
        // Defines the external padding of the components
        this.constraints.insets = new Insets(10, 10, 10, 10);
        // Sets gridy to -1 because it will be increased in addinformationLine
        this.constraints.gridy = -1;

        this.addInformationLine("Reference :", this.product.getReference());

        this.addSpecificInformation();

        this.constraints.gridx = 0;
        this.constraints.gridy = this.constraints.gridy + 1;
        this.add(new JLabel("Descriptif :"), this.constraints);
        // Uses a JTextArea of 3 lines to display the description
        this.descriptionTextArea = new JTextArea(this.product.getDescription());
        this.descriptionTextArea.setRows(3);
        this.descriptionTextArea.setLineWrap(true);
        this.descriptionTextArea.setWrapStyleWord(true);
        this.descriptionTextArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent event) {
                if (event.isPopupTrigger()) {
                    ProductPanel.this.popupMenu.show(event.getComponent(), event.getX(), event.getY());
                }
            }
        });
        this.constraints.gridx = 1;
        this.add(this.descriptionTextArea, this.constraints);

        this.constraints.fill = GridBagConstraints.NONE;
        this.constraints.gridx = 0;
        this.constraints.gridy = this.constraints.gridy + 1;
        this.constraints.gridwidth = 2;
        JButton updateButton = new JButton("Mettre a jour");
        updateButton.addActionListener(this);
        this.add(updateButton, this.constraints);

        this.makeTooltipMenu();
    }

    /**
     * Adds specific product information to the panel.
     */
    protected abstract void addSpecificInformation();

    /**
     * Adds an information line to the panel.
     * 
     * @param title The title of the panel.
     * @param text The text of the panel.
     * @return The text field containing the text.
     */
    protected JTextField addInformationLine(String title, String text) {
        // Adds the label to the coordinates (0, gridy+1)
        this.constraints.gridx = 0;
        this.constraints.gridy = this.constraints.gridy + 1;
        this.add(new JLabel(title), this.constraints);

        // Adds the text field to the coordinates (1, gridy+1)
        JTextField textField = new JTextField(30);
        textField.setText(text);
        textField.setEditable(false);
        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    ProductPanel.this.popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        this.constraints.gridx = 1;
        this.add(textField, this.constraints);

        return textField;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.product.setDescription(this.descriptionTextArea.getText());
    }

    private void makeTooltipMenu() {
        this.popupMenu = new JPopupMenu();

        JMenuItem menuItem;

        menuItem = new JMenuItem(new DefaultEditorKit.CutAction());
        menuItem.setText("Couper");
        menuItem.setMnemonic(KeyEvent.VK_C);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        this.popupMenu.add(menuItem);
        menuItem = new JMenuItem(new DefaultEditorKit.CopyAction());
        menuItem.setText("Copier");
        menuItem.setMnemonic(KeyEvent.VK_O);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        this.popupMenu.add(menuItem);
        menuItem = new JMenuItem(new DefaultEditorKit.PasteAction());
        menuItem.setText("Coller");
        menuItem.setMnemonic(KeyEvent.VK_L);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        this.popupMenu.add(menuItem);

        this.popupMenu.addSeparator();

        menuItem = new JMenuItem(new TextAction("Supprimer") {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                JTextComponent textComponent = (JTextComponent) ProductPanel.this.popupMenu.getInvoker();

                if (textComponent.isEditable()) {
                    textComponent.setText(textComponent.getText().substring(0,
                            textComponent.getSelectionStart()) +
                        textComponent.getText().substring(textComponent.getSelectionEnd()));
                }
            }
        });
        menuItem.setMnemonic(KeyEvent.VK_P);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
        this.popupMenu.add(menuItem);
    }
}
