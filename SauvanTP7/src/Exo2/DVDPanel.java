package Exo2;



/**
 * Panel to display {@link DVD}'s information.
 * 
 * @author bsauvan
 */
public class DVDPanel extends UnitProductPanel {
    private static final long serialVersionUID = 1L;

    /**
     * Creates a DVDPanel.
     * 
     * @param dvd The DVD to display into the panel.
     */
    public DVDPanel(DVD dvd) {
        super(dvd);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addSpecificInformation() {
        this.addInformationLine("Nom :", ((DVD) this.product).getName());
        this.addInformationLine("Realisateur :", ((DVD) this.product).getFilmDirector());
        this.addInformationLine("Type :", ((DVD) this.product).getKind().toString());
        super.addSpecificInformation();
    }
}
