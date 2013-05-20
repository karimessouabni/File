package Administration_des_Fournisseurs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Logger;


/**
 * @author karim
 *
 */
public class Classement implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id ;
	private String nom; // ex categorie, localité, genre, type
	private ArrayList<String> sousclassement; // ex : pour categorie:[music , art, livre]
	private static final Logger logger = Logger.getLogger(Classement.class.getName());

	public Classement(long id, String nom, ArrayList<String> sousclassement) {
		this.id=id;
		this.nom=nom;
		this.sousclassement=sousclassement;
		logger.info("Classement:"+this.id+ " cree");
	}

	public ArrayList<String> getSousClassementList() {
		return this.sousclassement;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String aNom) {
		this.nom = aNom;
	}

	public void setSousClassementList(ArrayList<String> sousclassements) {
		this.sousclassement = sousclassements;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}