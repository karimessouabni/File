package Administration_des_Fournisseurs;


import java.io.Serializable;
import java.util.logging.Logger;



/**
 * @author karim
 *
 */
public class Fournisseur implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String mdp;
	private String mail;
	private int nbVenteDecide;
	private float taxe;
	private Classement classements; // ex : Categorie, Location, Genre, Type
	private static final Logger logger = Logger.getLogger(Fournisseur.class.getName());


	public Fournisseur(long id2, String mdp, String mail, int nbVenteDecide, float taxes, Classement c) throws Exception {
		
		this.id = id2 ;
		this.classements=c;
		if(mdp.isEmpty()) throw new Exception();
		this.mdp=mdp;
		if (Utilitaire.emailValidator(mail)) this.mail=mail;
		else throw new Exception();
		logger.info("Fournisseur :"+this.id+ " cree");
		}

	public Classement getClassements() {
		return classements;
	}

	public void setClassements(Classement classements) {
		this.classements = classements;
	}

	public long getIdentifiant() {
		return this.id;
	}

	public void setIdentifiant(int aIdentifiant) {
		this.id = aIdentifiant;
	}

	public String getMailContact() {
		return this.mail;
	}

	public void setMailContact(String aMailContact) {
		this.mail = aMailContact;
	}

	public int getNbVenteDecide() {
		return this.nbVenteDecide;
	}

	public float getTaxe() {
		return this.taxe;
	}

	public void setTaxe(float aTaxe) {
		this.taxe = aTaxe;
	}

	public void setNbVenteDecide(int aNbVenteDecide) {
		this.nbVenteDecide = aNbVenteDecide;
	}

	public Boolean demandeInscription() {
		throw new UnsupportedOperationException();
	}

	public String getMdp() {
		return this.mdp;
	}

	public void setMdp(String aMdp) {
		this.mdp = aMdp;
	}
}