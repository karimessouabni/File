package Exo3;



/**
 * Panel to display {@link Book}'s information.
 * 
 * @author bsauvan
 */
public class BookPanel extends UnitProductPanel {
    private static final long serialVersionUID = 1L;

    /**
     * Creates a BookPanel.
     * 
     * @param book The book to display into the panel.
     */
    public BookPanel(Book book) {
        super(book);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addSpecificInformation() {
        this.addInformationLine("Auteur :", ((Book) this.product).getAuthor());
        this.addInformationLine("Titre :", ((Book) this.product).getTitle());
        this.addInformationLine("Editeur :", ((Book) this.product).getPublisher());
        super.addSpecificInformation();
    }
}
