package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Administration_des_Fournisseurs.Classement;
import Administration_des_Fournisseurs.Fournisseur;
import DAO.AbstractDAOFactory;
import DAO.DAO;
import DAO.FactoryType;

public class FournisseurDaoTest {

	

	 @Test
	   public void testFindById() {
		 DAO<Fournisseur> fDAO = AbstractDAOFactory.getFactory(FactoryType.DAO_FACTORY).getFournisseurDAO();
		 Fournisseur u = fDAO.find(22);
	       assertEquals(22, u.getIdentifiant());//assertEquals qui consiste à comparer une valeur attendue (1er argument), à un résultat (2ème argument)
	       //Si le résultat est égal à la valeur attendue, alors l'exécution se poursuit, sinon une exception est levée et la méthode de test est interrompue

	       assertEquals("karimessouabni0@gmail.com", u.getMailContact());
	       assertEquals("mypassword", u.getMdp());
	    
	   }

	@Test

	   public void testCreate() throws Exception {
		String myLocation = "Paris";
		
		Classement c = new Classement(3, "Location", myLocation );
		Fournisseur f = new Fournisseur(3, "mypassword", "karimessouabni0@gmail.com", 0, 0, c) ; 
		DAO<Fournisseur> fDAO = AbstractDAOFactory.getFactory(FactoryType.DAO_FACTORY).getFournisseurDAO();
		fDAO.create(f);
	    System.out.println("Termine");
	
	}
	
	
	 
	 
}
