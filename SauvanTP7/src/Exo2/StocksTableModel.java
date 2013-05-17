package Exo2;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import Exo2.Stocks.ProductStock;



/**
 * Table model to display {@link Stocks} inside a {@link JTable}.
 * 
 * @author bsauvan
 */
public class StocksTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Reference";
            case 1:
                return "Nombre de produits";
            case 2:
                return "Descriptif";
            default:
                return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return Integer.class;
            case 2:
                return String.class;
            default:
                return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRowCount() {
        return Stocks.getInstance().getProductStocks().size(); // return the number of Stocks
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getColumnCount() {
        return 3; // because we got 3 columns  
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) { // to deactivate editing the reference column 
        if (columnIndex == 0) { // The reference column is not editable
            return false;
        } else {
            return true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        int i = 0;

        for (ProductStock productStock : Stocks.getInstance().getProductStocks().values()) { //browse the stock list
            if (i == rowIndex) {
                switch (columnIndex) {
                    case 0:
                        return productStock.getProduct().getReference();
                    case 1:
                        return productStock.getNbProducts();
                    case 2:
                        return productStock.getProduct().getDescription();
                    default:
                        return null;
                }
            }

            i++;
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        int i = 0;

        for (ProductStock productStock : Stocks.getInstance().getProductStocks().values()) {
            if (i == rowIndex) {
                switch (columnIndex) { // reference is unchangeable 
                    case 1:
                        int nbProducts = (int) aValue; // cast to int 

                        if (nbProducts > 0) {
                            productStock.setNbProducts(nbProducts);
                        }

                        return;
                    case 2:
                        productStock.getProduct().setDescription((String) aValue);

                        return;
                    default:
                        return;
                }
            }

            i++;
        }
    }
}
