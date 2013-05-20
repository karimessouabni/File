package Administration_des_Fournisseurs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

//import Copain_d_achat.Historique;
//import Bonnes_affaires.Client;
//import Caprice_en_mobilité.CapriceCentrale;

/**
 * @author karim 
 * Cette classe est non fonctionnelle jusqu'à l'importaion des
 * packages concernés des autres groupes.
 */
public class Statistiques implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * A importer : Bonnes_affaires.Client, Copain_d_achat.Historique et
	 * Caprice_en_mobilité.CapriceCentrale; et puis on procede au remplacement
	 * du type Objet apres le type qui convient
	 * */
	public Object historique;
	public Object client; 
	public Object capriceCentrale;
	public Gerant gerant;

	public Double calculStatProduit(Fournisseur statOfFounis, List<Fournisseur> fList, Object Produit) {

		Double nbv=0.0;
        for (Fournisseur fournisseur : fList) {
        	if (fournisseur.equals(statOfFounis))
        		nbv+=fournisseur.getNbVenteDecide();
        }
		return nbv;
	}

	public Double calculStat(Produit pduit , List<Produit> pList) {
			
		Double nbrachat=0.0;
	     for (Produit produit : pList) {
	        	if (produit.equals(pduit))
	        		nbrachat+=produit.get_nbrAchat();
	        }
	     return nbrachat;
	}

}