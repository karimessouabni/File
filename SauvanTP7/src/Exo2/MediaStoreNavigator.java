package Exo2;

/*
 * Il y a 2 façons de faire avec un bouton :

 Utiliser une classe qui implémente ActionListener et
 écouter le bouton : Cette méthode est toute simple. 
 Il faut avoir une classe implémentant ActionListener 
 et l'ajouter comme écouteur aux boutons. Ensuite, à chaque clic, 
 la méthode actionPerformed va être appelée et il faudra tester 
 le bouton qui a envoyé l'événement, ce qui n'est pas très pratique 
 avec un grand nombre de boutons.

 Utiliser une AbstractAction : Cette classe représente une
 action de Swing. On peut lui donner un texte et une icône 
 et on peut évidemment lui dire que faire en cas de clic dans
 la méthode actionPerformed. Personnellement, je préfère cette
 méthode qui permet de bien séparer l'action du composant graphique,
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
import javax.swing.AbstractCellEditor;
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
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.text.DefaultEditorKit;

import Exo2.Stocks.ProductStock;

/**
 * GUI for navigating through mediastore's stocks.
 * 
 * @author Karim Essouabni
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

	private StocksTableModel st;

	/**
	 * Creates a MediaStoreManager.
	 */
	public MediaStoreNavigator(String path) {
		super("MediaStore");
		fc = new JFileChooser(); // une seule instance pour se souvenir du
									// dernier répertoire ouvert
		// UIManager.put(fc.setApproveButtonText(), "" );
		// fc.setApproveButtonText("Ouvrire/Charger"); << gerrer dans le main
		// avec UIManager
		SwingUtilities.updateComponentTreeUI(MediaStoreNavigator.this);

		// Stocks.loadStocks(MediaStoreNavigator.this.stocksBackupManager,
		// path);

		/* Load the Stocks */
		this.stocksBackupManager = new StocksBackupManager();
		Stocks.loadStocks(this.stocksBackupManager, path);
		JOptionPane.showMessageDialog(MediaStoreNavigator.this,
				"Chargement initial de l’état des stocks effectué !");

		// pour que la première ouverture du JFileChooser se fera dans le
		// repertoire du chargement initial de l'état des stocks
		File f = null;
		try {
			f = new File(new File(path).getCanonicalPath());
		} catch (IOException e1) {

			e1.printStackTrace();
		}
		fc.setCurrentDirectory(f);

		// this.stocksBackupManager = new StocksBackupManager();
		// Stocks.loadStocks(this.stocksBackupManager);

		// Creates the action for the close tab button/menu item
		this.closerTabAction = new CloserTabAction();

		// Creates the action for the quit button/menu item
		this.quitAction = new QuitAction();

		this.st = new StocksTableModel();

		// Adds the menu bar
		this.addMenuBar();

		this.tabs = new JTabbedPane();
		this.tabs.addTab("Recherche", this.makeSearchTab());
		this.tabs.setSelectedIndex(0); // //or
										// this.tabs.setSelectedIndex(MediaStoreNavigator.this.tabs.indexOfTab("Stocks"));

		// Creates the stocks tab but does not add it to the tabs for the moment
		// It will be added by the appropriate menu item when asked by the user
		this.makeStocksTab(); // used for showing all the stocks

		// Adds a change listener to the tabs in order to enable/disable the
		// close tab button/menu item
		this.tabs.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if (MediaStoreNavigator.this.tabs.getSelectedIndex() == 0) {
					// Disables the close tab button/menu item for the search
					// tab
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
		this.setLocation((screenSize.width - this.getWidth()) / 2,
				(screenSize.height - this.getHeight()) / 2);

		// Adds the closer key listener to all focusable components
		this.closerKeyListener = new CloserKeyListener();
		this.addCloserKeyListenerToComponents(this);

		// Saves the stocks and terminates the application when closing the
		// window
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				MediaStoreNavigator.this.quitApplication();
			}
		});

		this.setAlwaysOnTop(false);
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
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,
				ActionEvent.CTRL_MASK));
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int indexStocksTab = MediaStoreNavigator.this.tabs
						.indexOfTab("Stocks"); // the tab(Stocks) opened's index
												// (if there is one of course)

				if (indexStocksTab == -1) { // if the tab is not opened yet
					MediaStoreNavigator.this.tabs.addTab("Stocks",
							MediaStoreNavigator.this.stocksTab);
					indexStocksTab = MediaStoreNavigator.this.tabs
							.getTabCount() - 1;
				}

				MediaStoreNavigator.this.tabs.setSelectedIndex(indexStocksTab);// if
																				// the
																				// tab
																				// is
																				// opened
			}
		});
		subMenu.add(menuItem);

		menuItem = new JMenuItem("Charger", new ImageIcon("./icons/Open.gif"));
		menuItem.setMnemonic(KeyEvent.VK_C);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				ActionEvent.CTRL_MASK));
		fc.setDialogTitle("Charger l'etat des stocks");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showOpenDialog(MediaStoreNavigator.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					String path = file.getAbsolutePath();
					Stocks.loadStocks(
							MediaStoreNavigator.this.stocksBackupManager, path);
					JOptionPane.showMessageDialog(MediaStoreNavigator.this,
							"Etat des stocks charge");
				}
			}
		});
		subMenu.add(menuItem);

		menuItem = new JMenuItem("Sauvegarder", new ImageIcon(
				"./icons/Save.gif"));
		menuItem.setMnemonic(KeyEvent.VK_S);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.CTRL_MASK));
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fc.setDialogTitle("Sauvegarder l'etat des stocks");
				int returnVal = fc.showSaveDialog(MediaStoreNavigator.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					String path = file.getAbsolutePath();
					Stocks.saveStocks(
							MediaStoreNavigator.this.stocksBackupManager, path);
					JOptionPane.showMessageDialog(MediaStoreNavigator.this,
							"Etat des stocks sauvegarde");

				} else {
					fc.setDialogTitle("Charger l'etat des stocks");
				}
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

		menuItem = new JMenuItem(new DefaultEditorKit.CutAction()); // the same
																	// for
																	// this.quitAction
		menuItem.setText("Couper");
		menuItem.setMnemonic(KeyEvent.VK_C);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.CTRL_MASK));
		menu.add(menuItem);

		menuItem = new JMenuItem(new DefaultEditorKit.CopyAction());
		menuItem.setText("Copier");
		menuItem.setMnemonic(KeyEvent.VK_O);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				ActionEvent.CTRL_MASK));
		menu.add(menuItem);

		menuItem = new JMenuItem(new DefaultEditorKit.PasteAction());
		menuItem.setText("Coller");
		menuItem.setMnemonic(KeyEvent.VK_L);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
				ActionEvent.CTRL_MASK));
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

			// Sets icons
			this.putValue(SMALL_ICON, new ImageIcon("./icons/Search.gif"));

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
			MediaStoreNavigator.this
					.openProductTab(MediaStoreNavigator.this.searchTextField
							.getText());

			// Resets the search text field
			MediaStoreNavigator.this.searchTextField.setText(null);
		}
	}

	private void makeStocksTab() {
		this.stocksTab = new JPanel();

		this.stocksTable = new JTable(st);
		TableColumn nbrPdCol = this.stocksTable.getColumn("Nombre de produits"); // pick
																					// up
																					// the
																					// instance
																					// of
																					// TableColumn
																					// associated

		SpinnerChooserEditor editor = new SpinnerChooserEditor();
		nbrPdCol.setCellEditor(editor);

		this.stocksTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if ((e.getButton() == MouseEvent.BUTTON1)
						&& (e.getClickCount() == 2) && !e.isConsumed()) {
					int row = MediaStoreNavigator.this.stocksTable.rowAtPoint(e
							.getPoint());
					int column = MediaStoreNavigator.this.stocksTable
							.columnAtPoint(e.getPoint());

					if ((column == 0) && (row >= 0)) { // ==0 for the reference
														// column and >=0 for
														// the Stock
						MediaStoreNavigator.this
								.openProductTab(MediaStoreNavigator.this.stocksTable
										.getValueAt(row, column).toString());

					}
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(this.stocksTable);

		this.stocksTab.add(scrollPane, BorderLayout.CENTER);
	}

	/**
	 * @param reference
	 *            it opens a Product Tab ( from the search text field or a right
	 *            double clique on a reference in the StocksTab)
	 */
	private void openProductTab(String reference) {
		ProductStock productStock = Stocks.getInstance().getProductStock(
				reference);

		if (productStock != null) {
			Product product = productStock.getProduct();
			int indexProductTab = MediaStoreNavigator.this.tabs
					.indexOfTab("Details du produit " + reference);

			if (indexProductTab == -1) { // if not opened yet => creat it
				JPanel tab = null;
				if (product instanceof Book) {
					tab = new BookPanel((Book) product);
				} else if (product instanceof DVD) {
					tab = new DVDPanel((DVD) product);
				} else { // The product is a lot
					tab = new LotPanel((Lot) product);
				}

				MediaStoreNavigator.this.tabs.add("Details du produit "
						+ reference, tab);
				indexProductTab = MediaStoreNavigator.this.tabs.getTabCount() - 1;
			}

			MediaStoreNavigator.this.tabs.setSelectedIndex(indexProductTab);
		} else {
			// There is no product with such a reference
			JOptionPane.showMessageDialog(MediaStoreNavigator.this,
					"Reference inconnue");
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

			// Sets icons
			this.putValue(SMALL_ICON, new ImageIcon("./icons/Close.gif"));

			// Sets the tool tip text of the closer tab button/menu item
			this.putValue(SHORT_DESCRIPTION, "Fermer l'onglet courant");

			// Sets the accelerator key of the closer tab button/menu item
			this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
					KeyEvent.VK_W, ActionEvent.CTRL_MASK));

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
			MediaStoreNavigator.this.tabs.remove(MediaStoreNavigator.this.tabs
					.getSelectedIndex()); // ferme l'onglet actif
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
			this.putValue(SMALL_ICON, new ImageIcon("./icons/Exit.gif"));

			// Sets the tool tip text of the quit button/menu item
			this.putValue(SHORT_DESCRIPTION, "Quitter l'application");

			// Sets the accelerator key of the quit button/menu item
			// gstion a l'exterieur pour afficheer le message de confirmation de
			// fermeture
			this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
					KeyEvent.VK_C, ActionEvent.CTRL_MASK));

			// Sets the mnemonic key of the quit button/menu item
			this.putValue(MNEMONIC_KEY, KeyEvent.VK_C);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			Object[] options = { "Oui", "Non" };
			JOptionPane jop = new JOptionPane();
			int option = jop.showOptionDialog(MediaStoreNavigator.this,
					"Voulez-vous vraiment quitter?", "Quitter",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
					null, options, options[0]);

			if (option == JOptionPane.OK_OPTION) {
				System.exit(0);
			}

		}
	}

	// for the close red button
	private void quitApplication() {
		Object[] options = { "Oui", "Non" };
		JOptionPane jop = new JOptionPane();
		int option = jop.showOptionDialog(MediaStoreNavigator.this,
				"Voulez-vous vraiment quitter?", "Quitter",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				options, options[0]);
		if (option == JOptionPane.OK_OPTION) {
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
				// Adds the closer key listener to all sub-components of this
				// component which is a container
				this.addCloserKeyListenerToComponents((Container) component);
			}
		}
	}

	/**
	 * Key listener to listen the Ctrl+Q 'C' keyboard shortcut which terminates
	 * the application.
	 * 
	 * @author bsauvan
	 */
	private class CloserKeyListener extends KeyAdapter {
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void keyPressed(KeyEvent e) {
			if (((e.getModifiers() & 157) != 0)
					&& (e.getKeyCode() == KeyEvent.VK_C)) { // 157 pour cmd sous
															// mac
				Object[] options = { "Oui", "Non" };
				JOptionPane jop = new JOptionPane();
				int option = jop
						.showOptionDialog(MediaStoreNavigator.this,
								"Voulez-vous vraiment quitter?", "Quitter",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[0]);
				if (option == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
			}
		}

	}

	private class SpinnerChooserEditor extends AbstractCellEditor implements
			TableCellEditor {

		private JSpinner spinner;

		public Component getTableCellEditorComponent(JTable table,
				Object value, boolean isSelected, int row, int column) {
			JPanel jp = new JPanel();
			jp.setLayout(new BorderLayout());

			int min = 0;
			int max = 1000;
			int step = 1;
			int initValue = (int) MediaStoreNavigator.this.st.getValueAt(row,
					column);
			SpinnerModel model = new SpinnerNumberModel(initValue, min, max,
					step);
			this.spinner = new JSpinner(model);
			jp.add(spinner, BorderLayout.CENTER);

			return jp;
		}

		public Object getCellEditorValue() {
			return this.spinner.getValue();
		}

	}

	public static void main(String[] args) {

		UIManager.put("FileChooser.openButtonText", "Ouvrire/Charger");
		UIManager.put("FileChooser.openButtonToolTipText", "TEST");
		UIManager.put("FileChooser.saveButtonText", "Ouvrire/Sauvegarder");
		UIManager.put("FileChooser.saveButtonToolTipText", "TEST");
		UIManager.put("FileChooser.cancelButtonText", "Annuler");
		UIManager.put("FileChooser.cancelButtonToolTipText ", "TEST");

		// String path = args[1]; //
		String path = "./stocks.data";
		new MediaStoreNavigator(path);

	}

}