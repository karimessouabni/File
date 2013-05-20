package Administration_des_Fournisseurs;

import java.io.Serializable;


/**
 * @author karim
 *
 */
public class Produit implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String _numSerie;
	private String _nom;
	private String _categorie;
	private double _prix;
	private double _nbrAchat;
	public Fournisseur _unnamed_Fournisseur_;

	public Produit(Object aNumSerie_String, Object aNom_String, Object aCategorie_String, Object aPrix_double) {
		throw new UnsupportedOperationException();
	}

	public String getNumSerie() {
		return this._numSerie;
	}
	

	public double get_nbrAchat() {
		return _nbrAchat;
	}

	public void set_nbrAchat(double _nbrAchat) {
		this._nbrAchat = _nbrAchat;
	}

	public void setNumSerie(String aNumSerie) {
		this._numSerie = aNumSerie;
	}

	public String getNom() {
		return this._nom;
	}

	public void setNom(String aNom) {
		this._nom = aNom;
	}

	public String getCategorie() {
		return this._categorie;
	}

	public void setCategorie(String aCategorie) {
		this._categorie = aCategorie;
	}

	public double getPrix() {
		return this._prix;
	}

	public void setPrix(double aPrix) {
		this._prix = aPrix;
	}
}