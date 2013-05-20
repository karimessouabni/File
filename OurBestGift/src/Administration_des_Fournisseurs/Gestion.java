package Administration_des_Fournisseurs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;





/**
 * @author karim
 *
 */
public class Gestion {
	private final Map<Integer, String>  _listeBannir;
	


	//public BD _unnamed_BD_;
	 private static Gestion instance;
	 
	 
	 
	    private Gestion() {
	    	this._listeBannir = new HashMap<Integer, String>();
	    }
	    
	    
	    /**
	     * Returns the unique instance of Stocks.
	     * 
	     * @return The unique instance of Stocks.
	     */
	    public static Gestion getInstance() {
	        if (instance == null) {
	            instance = new Gestion();
	        }

	        return instance;
	    }
	    


		public Map<Integer, String> get_listeBannir() {
			return _listeBannir;
		}
		
	public Arrays getFournisseurList() {
		throw new UnsupportedOperationException();
	}

	public Arrays getClassementsListe() {
		throw new UnsupportedOperationException();
	}

	public void setFournisseurList(Arrays aFournisseurList) {
		throw new UnsupportedOperationException();
	}

	public void setClassementsListe(Arrays aClassementsListe) {
		throw new UnsupportedOperationException();
	}

	public Classement createNewClassement() {
		throw new UnsupportedOperationException();
	}

	public Boolean ajouterFournisseur(Object aFournisseur_CompteFournisseur) {
		throw new UnsupportedOperationException();
	}

	public Boolean supprimerFournisseur(Object aFournisseur_CompteFournisseur) {
		throw new UnsupportedOperationException();
	}

	public void addToBanList(Fournisseur f) {
		this._listeBannir.put((int) f.getIdentifiant(), f.getMdp());
	}

	public void removeFromBanList(Object aFournisseur) {
		throw new UnsupportedOperationException();
	}

	public Boolean inscriptionFournisseur(Object aFournisseur_Fournisseur) {
		throw new UnsupportedOperationException();
	}
}