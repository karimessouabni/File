package Tests;

import java.util.ArrayList;

import org.junit.Test;

import Administration_des_Fournisseurs.Classement;
import Administration_des_Fournisseurs.Fournisseur;
import DAO.AbstractDAOFactory;
import DAO.DAO;
import DAO.FactoryType;

public class FournisseurDaoTest {

	@Test

	   public void testCreate() throws Exception {
		ArrayList locationList = new ArrayList();
		locationList.add("Nice");
		locationList.add("Paris");
		locationList.add("Lyon");
		locationList.add("Cannes");
		
		Classement c = new Classement(12, "Location", locationList);
		Fournisseur f = new Fournisseur(12, "mypassword", "karimessouabni0@gmail.com", 12, 30, c) ; 
		DAO<Fournisseur> fDAO = AbstractDAOFactory.getFactory(FactoryType.DAO_FACTORY).getFournisseurDAO();
		fDAO.create(f);
	    System.out.println("Terminé");

		
	}
}
