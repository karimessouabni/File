package Administration_des_Fournisseurs;

import java.io.Serializable;

/**
 * @author karim
 *
 */
public class Membre implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String _identifiant;
	private String _mdp;
	private String _mail;
	private String _nom;
	private String _prenom;
	private int _age;
	public Gestion _unnamed_Gestion_;
}