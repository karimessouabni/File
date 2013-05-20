package XMLClasses;

import java.sql.Connection;

import Administration_des_Fournisseurs.Administrateur;
import DAO.DAO;

/**
 * @author karim
 *
 */
public class XMLAdministrateurDAO extends DAO<Administrateur> {


	public XMLAdministrateurDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	public Administrateur create(Administrateur obj) {
		// TODO Auto-generated method stub	
		return obj;
	}
	public boolean delete(Administrateur obj) {
		return true ;	
	}
	public Administrateur find(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	public Administrateur update(Administrateur obj) {
		// TODO Auto-generated method stub		
		return obj;
	}

}
