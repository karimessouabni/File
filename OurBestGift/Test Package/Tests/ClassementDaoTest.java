package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Administration_des_Fournisseurs.Classement;
import DAO.AbstractDAOFactory;
import DAO.DAO;
import DAO.FactoryType;

public class ClassementDaoTest {

	
	 @Test
	   public void testFindById() {
		 DAO<Classement> fDAO = AbstractDAOFactory.getFactory(FactoryType.DAO_FACTORY).getClassementDAO();
		 	Classement u = fDAO.find(5);
	       assertEquals(5, u.getId());//assertEquals qui consiste à comparer une valeur attendue (1er argument), à un résultat (2ème argument)
	       //Si le résultat est égal à la valeur attendue, alors l'exécution se poursuit, sinon une exception est levée et la méthode de test est interrompue

	       assertEquals("Loisir", u.getNom());
	       assertEquals("musique", u.getSousClassement());
	    
	   }

	@Test

	   public void testCreate() throws Exception {
		String myLocation = "Paris";
		
		Classement c = new Classement(7, "loisir", "musique" ); 
		DAO<Classement> fDAO = AbstractDAOFactory.getFactory(FactoryType.DAO_FACTORY).getClassementDAO();
		fDAO.create(c);
	    System.out.println("Termine");
	
	}
}
