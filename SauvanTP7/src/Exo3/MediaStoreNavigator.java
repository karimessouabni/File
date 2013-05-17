package Exo3;
/*
 * Il y a 2 fa??ons de faire avec un bouton :

Utiliser une classe qui impl??mente ActionListener et
 ??couter le bouton : Cette m??thode est toute simple. 
 Il faut avoir une classe impl??mentant ActionListener 
 et l'ajouter comme ??couteur aux boutons. Ensuite, ?? chaque clic, 
 la m??thode actionPerformed va ??tre appel??e et il faudra tester 
 le bouton qui a envoy?? l'??v??nement, ce qui n'est pas tr??s pratique 
 avec un grand nombre de boutons.
 
Utiliser une AbstractAction : Cette classe repr??sente une
 action de Swing. On peut lui donner un texte et une ic??ne 
 et on peut ??videmment lui dire que faire en cas de clic dans
  la m??thode actionPerformed. Personnellement, je pr??f??re cette
   m??thode qui permet de bien s??parer l'action du composant graphique,
    mais c'est un choix personnel.
    
 */
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultEditorKit;

import Exo3.Stocks.ProductStock;






/**
 * GUI for navigating through mediastore's stocks.
 * 
 * @author bsauvan
 */
public class MediaStoreNavigator extends JFrame {
    private static final long serialVersionUID = 1L;

    static {
        try {
            LogManager.getLogManager().readConfiguration(
                    new FileInputStream("loggers-configuration.properties"));
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    private StocksBackupManager stocksBackupManager;

    private CloserTabAction closerTabAction;

    private QuitAction quitAction;

    private JTabbedPane tabs;

    private JTextField searchTextField;

    private JPanel stocksTab;

    private JTable stocksTable;
    
    private JFileChooser fc;
    
    private CloserKeyListener closerKeyListener;

    
    /**
     * Creates a MediaStoreManager.
     */
    public MediaStoreNavigator(String path) {
        super("MediaStore");
        fc = new JFileChooser(); // une seule instance pour se souvenir du dernier r??pertoire ouvert
        //UIManager.put(fc.setApproveButtonText(), "" );
        //fc.setApproveButtonText("Ouvrire/Charger"); << gerrer dans le main avec UIManager
        SwingUtilities.updateComponentTreeUI(MediaStoreNavigator.this);
        
        //Stocks.loadStocks(MediaStoreNavigator.this.stocksBackupManager, path);
        this.stocksBackupManager = new StocksBackupManager();
        Stocks.loadStocks(this.stocksBackupManager, path);
        JOptionPane.showMessageDialog(MediaStoreNavigator.this, "Chargement initial de l???e??tat des stocks effectu?? !");
        
        // pour  que  la premie??re ouverture du JFileChooser se fera dans le repertoire du chargement initial de l'??tat des stocks
        File f = null;
        try {
			f = new File(new File(path).getCanonicalPath());
		} catch (IOException e1) {

			e1.printStackTrace();
		}
        fc.setCurrentDirectory(f);

         
        // Loads the stocks
        this.stocksBackupManager = new StocksBackupManager();
        //Stocks.loadStocks(this.stocksBackupManager);

        // Creates the action for the close tab button/menu item
        this.closerTabAction = new CloserTabAction();

        // Creates the action for the quit button/menu item
        this.quitAction = new QuitAction();

    
        // Adds the menu bar
        this.addMenuBar();

        this.tabs = new JTabbedPane();
        this.tabs.addTab("Recherche", this.makeSearchTab());
        this.tabs.setSelectedIndex(0);
        // Creates the stocks tab but does not add it to the tabs for the moment
        // It will be added by the appropriate menu item when asked by the user
        this.makeStocksTab();

        // Adds a change listener to the tabs in order to enable/disable the close tab button/menu item
        this.tabs.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (MediaStoreNavigator.this.tabs.getSelectedIndex() == 0) {
                    // Disables the close tab button/menu item for the search tab
                    MediaStoreNavigator.this.closerTabAction.setEnabled(false);
                } else {
                    // Enables the close tab button/menu item for other tabs
                    MediaStoreNavigator.this.closerTabAction.setEnabled(true);
                }
            }
        });

        this.add(this.tabs, BorderLayout.CENTER);

        JButton closeTabButton = new JButton(this.closerTabAction);

        JButton quitButton = new JButton(this.quitAction);
        

        // Adds the buttons to a box in order to be horizontally centered.
        Box buttonsHorizontalBox = Box.createHorizontalBox();
        buttonsHorizontalBox.add(Box.createHorizontalGlue());
        buttonsHorizontalBox.add(closeTabButton);
        buttonsHorizontalBox.add(Box.createHorizontalStrut(40));
        buttonsHorizontalBox.add(quitButton);
        buttonsHorizontalBox.add(Box.createHorizontalGlue());

        this.add(buttonsHorizontalBox, BorderLayout.SOUTH);

        this.setMinimumSize(new Dimension(600, 500));
        this.setResizable(false);

        // Centers the window to the screen
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        this.setLocation((screenSize.width - this.getWidth()) / 2, (screenSize.height - this.getHeight()) / 2);

        // Adds the closer key listener to all focusable components
        this.closerKeyListener = new CloserKeyListener();
        this.addCloserKeyListenerToComponents(this);
        
        
        // Saves the stocks and terminates the application when closing the window
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                MediaStoreNavigator.this.quitApplication();
            }
        });

        this.setAlwaysOnTop(true);
        this.setVisible(true);
    }

    private void addMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        JMenu menu = new JMenu("Fichier");
        menu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(menu);

        JMenu subMenu = new JMenu("Stocks");
        subMenu.setMnemonic(KeyEvent.VK_S);
        menu.add(subMenu);

        JMenuItem menuItem;

        menuItem = new JMenuItem("Afficher les stocks");
        menuItem.setMnemonic(KeyEvent.VK_A);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indexStocksTab = MediaStoreNavigator.this.tabs.indexOfTab("Stocks");

                if (indexStocksTab == -1) {
                    MediaStoreNavigator.this.tabs.addTab("Stocks", MediaStoreNavigator.this.stocksTab);
                    indexStocksTab = MediaStoreNavigator.this.tabs.getTabCount() - 1;
                }

                MediaStoreNavigator.this.tabs.setSelectedIndex(indexStocksTab);
            }
        });
        subMenu.add(menuItem);
        
        menuItem = new JMenuItem("Charger", new ImageIcon("./icons/Open.gif"));
        menuItem.setMnemonic(KeyEvent.VK_C);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        fc.setDialogTitle("Charger l'etat des stocks");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // Stocks.loadStocks(MediaStoreNavigator.this.stocksBackupManager);
            	  int returnVal = fc.showOpenDialog(MediaStoreNavigator.this);
                  if (returnVal == JFileChooser.APPROVE_OPTION) {
                      File file = fc.getSelectedFile();
                      //This is where a real application would open the file.
                    //  log.append("Opening: " + file.getName() + "." + newline);
                      String path = file.getAbsolutePath();
                      Stocks.loadStocks(MediaStoreNavigator.this.stocksBackupManager, path);
                      JOptionPane.showMessageDialog(MediaStoreNavigator.this, "Etat des stocks charge");
                  } else {
                    //  log.append("Open command cancelled by user." + newline);
                  }
       //           log.setCaretPosition(log.getDocument().getLength());
       

                
            }
        });
        subMenu.add(menuItem);
        


        menuItem = new JMenuItem("Sauvegarder",new ImageIcon("./icons/Save.gif"));
        menuItem.setMnemonic(KeyEvent.VK_S);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	fc.setDialogTitle("Sauvegarder l'etat des stocks");
            	 int returnVal = fc.showSaveDialog(MediaStoreNavigator.this);
                 if (returnVal == JFileChooser.APPROVE_OPTION) {
                     File file = fc.getSelectedFile();
                     //This is where a real application would save the file.
                     //log.append("Saving: " + file.getName() + "." + newline);
                     String path = file.getAbsolutePath();
                     Stocks.saveStocks(MediaStoreNavigator.this.stocksBackupManager, path);
                     JOptionPane.showMessageDialog(MediaStoreNavigator.this, "Etat des stocks sauvegarde");
                     
                 } else {
                	 fc.setDialogTitle("Charger l'etat des stocks");
                   //  log.append("Save command cancelled by user." + newline);
                 }
                 //log.setCaretPosition(log.getDocument().getLength());
             }

        });
        subMenu.add(menuItem);

        menu.addSeparator();
        

        menuItem = new JMenuItem(this.closerTabAction);
        menu.add(menuItem);
        menuItem = new JMenuItem(this.quitAction);
        menu.add(menuItem);

        menu = new JMenu("Edition");
        menu.setMnemonic(KeyEvent.VK_E);
        menuBar.add(menu);

        menuItem = new JMenuItem(new DefaultEditorKit.CutAction());
        menuItem.setText("Couper");
        menuItem.setMnemonic(KeyEvent.VK_C);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        menu.add(menuItem);
        menuItem = new JMenuItem(new DefaultEditorKit.CopyAction());
        menuItem.setText("Copier");
        menuItem.setMnemonic(KeyEvent.VK_O);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        menu.add(menuItem);
        menuItem = new JMenuItem(new DefaultEditorKit.PasteAction());
        menuItem.setText("Coller");
        menuItem.setMnemonic(KeyEvent.VK_L);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        menu.add(menuItem);
    }

    private JPanel makeSearchTab() {
        JPanel searchTab = new JPanel();

        searchTab.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        // Aligns components
        constraints.anchor = GridBagConstraints.BASELINE;
        // Resizes components horizontally and vertically
        constraints.fill = GridBagConstraints.BOTH;
        // Defines the external padding of the components
        constraints.insets = new Insets(0, 20, 0, 20);

        constraints.gridx = 0;
        constraints.gridy = 0;
        searchTab.add(new JLabel("Reference :"), constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        this.searchTextField = new JTextField(15);
        searchTab.add(this.searchTextField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        searchTab.add(new JButton(new SearchAction()), constraints);
        return searchTab;
    }

    /**
     * Action for the search button of the search tab. 
     * 
     * @author bsauvan
     */
    private class SearchAction extends AbstractAction {
        private static final long serialVersionUID = 1L;

        /**
         * Creates a SearchAction.
         */
        public SearchAction() {
            // Sets the text of the search button
            this.putValue(NAME, "Chercher");

            // Sets the tool tip text of the search button
            this.putValue(SHORT_DESCRIPTION, "Chercher la reference");

            // Sets the mnemonic key of the search button
            this.putValue(MNEMONIC_KEY, KeyEvent.VK_R);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            MediaStoreNavigator.this.openProductTab(MediaStoreNavigator.this.searchTextField.getText());

            // Resets the search text field
            MediaStoreNavigator.this.searchTextField.setText(null);
        }
    }

    private void makeStocksTab() {
        this.stocksTab = new JPanel();

        this.stocksTable = new JTable(new StocksTableModel());
        this.stocksTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ((e.getButton() == MouseEvent.BUTTON1) && (e.getClickCount() == 2) && !e.isConsumed()) {
                    int row = MediaStoreNavigator.this.stocksTable.rowAtPoint(e.getPoint());
                    int column = MediaStoreNavigator.this.stocksTable.columnAtPoint(e.getPoint());

                    if ((column == 0) && (row >= 0)) {
                        MediaStoreNavigator.this.openProductTab(MediaStoreNavigator.this.stocksTable
                                .getValueAt(row, column).toString());

                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(this.stocksTable);

        this.stocksTab.add(scrollPane, BorderLayout.CENTER);
    }

    private void openProductTab(String reference) {
        ProductStock productStock = Stocks.getInstance().getProductStock(reference);

        if (productStock != null) {
            Product product = productStock.getProduct();
            int indexProductTab = MediaStoreNavigator.this.tabs.indexOfTab("Details du produit " + reference);

            if (indexProductTab == -1) {
                JPanel tab = null;
                if (product instanceof Book) {
                    tab = new BookPanel((Book) product);
                } else if (product instanceof DVD) {
                    tab = new DVDPanel((DVD) product);
                } else { // The product is a lot
                    tab = new LotPanel((Lot) product);
                }

                MediaStoreNavigator.this.tabs.add("Details du produit " + reference, tab);
                indexProductTab = MediaStoreNavigator.this.tabs.getTabCount() - 1;
            }

            MediaStoreNavigator.this.tabs.setSelectedIndex(indexProductTab);
        } else {
            // There is no product with such a reference
            JOptionPane.showMessageDialog(MediaStoreNavigator.this, "Reference inconnue");
        }
    }

    /**
     * Action for the closer tab button/menu item.
     * 
     * @author bsauvan
     */
    private class CloserTabAction extends AbstractAction {
        private static final long serialVersionUID = 1L;

        /**
         * Creates a CloserTabAction.
         */
        public CloserTabAction() {
            // Sets the text of the closer tab button/menu item
            this.putValue(NAME, "Fermer l'onglet");

            // Sets the tool tip text of the closer tab button/menu item
            this.putValue(SHORT_DESCRIPTION, "Fermer l'onglet courant");

            // Sets the accelerator key of the closer tab button/menu item
            this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));

            // Sets the mnemonic key of the closer tab button/menu item
            this.putValue(MNEMONIC_KEY, KeyEvent.VK_F);

            // Disables the closer tab button/menu item by default
            this.setEnabled(false);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            MediaStoreNavigator.this.tabs.remove(MediaStoreNavigator.this.tabs.getSelectedIndex());
        }
    }

    /**
     * Action for the quit button/menu item.
     * 
     * @author bsauvan
     */
    private class QuitAction extends AbstractAction {
        private static final long serialVersionUID = 1L;

        /**
         * Creates a QuitAction.
         */
        public QuitAction() {
            // Sets the text of the quit button/menu item
            this.putValue(NAME, "Quitter");
            this.putValue(SMALL_ICON,  new ImageIcon("./icons/Exit.gif"));
            


            // Sets the tool tip text of the quit button/menu item
            this.putValue(SHORT_DESCRIPTION, "Quitter l'application");

            // Sets the accelerator key of the quit button/menu item
            //gstion a l'exterieur pour afficheer le message de confirmation de fermeture  
            //this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));

            // Sets the mnemonic key of the quit button/menu item
            this.putValue(MNEMONIC_KEY, KeyEvent.VK_Q);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void actionPerformed(ActionEvent e) {
        	Object[] options = {"Oui","Non"};
        	JOptionPane jop = new JOptionPane();   
        	int option = jop.showOptionDialog(MediaStoreNavigator.this, "Voulez-vous vraiment quitter?", "Quitter", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        	
        	if(option == JOptionPane.OK_OPTION){
        		System.exit(0);
        	}
        	
           
        }
    }

    // pour le button rouge en haut 
    private void quitApplication() {
    	Object[] options = {"Oui","Non"};
    	JOptionPane jop = new JOptionPane();   
    	int option = jop.showOptionDialog(MediaStoreNavigator.this, "Voulez-vous vraiment quitter?", "Quitter", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    	if(option == JOptionPane.OK_OPTION){
       // Stocks.saveStocks(this.stocksBackupManager, "stocks.data");
        System.exit(0);
    	}
    }
    private void addCloserKeyListenerToComponents(Container container) {
        for (Component component : container.getComponents()) {
            if (component.isFocusable()) {
                // Adds the closer key listener to the focusable component
                component.addKeyListener(this.closerKeyListener);
            }

            if (component instanceof Container) {
                // Adds the closer key listener to all sub-components of this component which is a container
                this.addCloserKeyListenerToComponents((Container) component);
            }
        }
    }


    /**
     * Key listener to listen the Ctrl+Q 'C' keyboard shortcut which terminates the application.
     * 
     * @author bsauvan
     */
    private class CloserKeyListener extends KeyAdapter {
        /**
         * {@inheritDoc}
         */
        @Override
        public void keyPressed(KeyEvent e) {
            if (((e.getModifiers() & 157) != 0) && (e.getKeyCode() == KeyEvent.VK_C)) { //157 pour cmd sous mac
            	Object[] options = {"Oui","Non"};
            	JOptionPane jop = new JOptionPane();   
            	int option = jop.showOptionDialog(MediaStoreNavigator.this, "Voulez-vous vraiment quitter?", "Quitter", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            	if(option == JOptionPane.OK_OPTION){
                System.exit(0);
            	}
            }
        }
        
    }


    public static void main(String[] args) {
    	

    	UIManager.put("FileChooser.openButtonText", "Ouvrire/Charger");
    	UIManager.put("FileChooser.openButtonToolTipText", "TEST");
    	UIManager.put("FileChooser.saveButtonText", "Ouvrire/Sauvegarder");
    	UIManager.put("FileChooser.saveButtonToolTipText", "TEST");
    	UIManager.put("FileChooser.cancelButtonText", "Annuler");
    	UIManager.put("FileChooser.cancelButtonToolTipText ", "TEST");
    	
    	//String path = args[1]; //
    	String path= "./stocks.data";
        new MediaStoreNavigator(path);
        
    }
}
