package Administration_des_Fournisseurs;
import java.io.Serializable;
import java.util.logging.Logger;

/**
 * @author karim
 *
 */
public class Administrateur  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long _identifiant;
	private String _mp;
	private String _mail;
	private static final Logger logger = Logger.getLogger(Administrateur.class.getName());

	public Administrateur(long aIdentifiant, String aMp_String, String aMail_String) throws Exception {
		
	
		this._identifiant = aIdentifiant ;  
		if(aMp_String.isEmpty()) throw new Exception();
		this._mp=aMp_String;
		if (Utilitaire.emailValidator(aMail_String)) this._mail=aMail_String;
		else throw new Exception();
		logger.info("Administrateru :"+this._identifiant+ " cree");
		
	}

	
	public long getIdentifiant() {
		return this._identifiant;
	}

	public void setIdentifiant(long aIdentifiant) {
		this._identifiant = aIdentifiant;
	}

	public String getMp() {
		return this._mp;
	}

	public void setMp(String aMp) throws Exception {
		if (Utilitaire.emailValidator(aMp)) this._mail=aMp;
		throw new Exception();
	}

	public String getMail() {
		return this._mail;
	}

	public void setMail(String aMail) {
		
		this._mail = aMail;
	}

	
	/**
	 * This function allows you to ban a provider (Fournisseur)
	 * The provider's password will be saved into a Map be before changing it to this Administrator Password 
	 * 
	 * Pour l'envoi de mail une libraire ( jar ) a été ajouter au projet 
	 * @param aFournisseur_CompteFournisseur
	 * @return void
	 */
	public void bannirCompte(Fournisseur f) {
		Gestion g =  Gestion.getInstance();
		g.addToBanList(f);
		f.setMdp(this._mp);
		// informer le fournissuer du reactivation de son compte par mail
		String subject = "OurBestGift";
		String text = "your account has been deactivated...";
		Mailing.sendMail(f.getMailContact(), this._mail,subject , text);
		
	}

	public void restituerCompte(Fournisseur f){
		Gestion g =  Gestion.getInstance();
		if(g.get_listeBannir().containsKey(f.getIdentifiant())){
			
			//restitution de l'ancien mot de pass
			f.setMdp(g.get_listeBannir().get(f.getIdentifiant()));
			
			// effacer  le fournisseur de la liste des fournisseurs bannis
			g.get_listeBannir().remove(f.getIdentifiant());

			// informer le fournissuer du reactivation de son compte par mail
			String subject = "OurBestGift";
			String text = "Your account has been activated. if you can't remember your password here it is : "+f.getMdp()+"";
			Mailing.sendMail(f.getMailContact(), this._mail,subject , text);
			
		}
		
		
	}
	public void getAttribute() {
		throw new UnsupportedOperationException();
	}

	public void setAttribute(Object aAttribute) {
		throw new UnsupportedOperationException();
	}
	

	 
	 
	
	
	
}
