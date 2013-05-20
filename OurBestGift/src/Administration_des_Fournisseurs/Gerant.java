package Administration_des_Fournisseurs;

import java.util.Vector;
import Administration_des_Fournisseurs.Statistiques;

/**
 * @author karim
 *
 */
public class Gerant {
	private String _identifiant;
	private String _mp;
	private String _mail;
	public Vector<Statistiques> _unnamed_Statistiques_ = new Vector<Statistiques>();
	public Vector<Fournisseur> _unnamed_Fournisseur_ = new Vector<Fournisseur>();

	public Gerant(Object aId_String, Object aMp_String, Object aMail_String) {
		throw new UnsupportedOperationException();
	}

	public Boolean modifierTaxeFournisseur(Fournisseur aCompteFournisseur) {
		throw new UnsupportedOperationException();
	}

	public double visualiseTaxe(Object aFournisseur_Fournisseur) {
		throw new UnsupportedOperationException();
	}

	public String getMp() {
		return this._mp;
	}

	public void setMp(String aMp) {
		this._mp = aMp;
	}

	public String getIdentifiant() {
		return this._identifiant;
	}

	public void setIdentifiant(String aIdentifiant) {
		this._identifiant = aIdentifiant;
	}
}