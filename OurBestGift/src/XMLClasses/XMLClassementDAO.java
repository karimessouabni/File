package XMLClasses;

import java.sql.Connection;

import Administration_des_Fournisseurs.Classement;
import DAO.DAO;

/**
 * @author karim
 *
 */
public class XMLClassementDAO extends DAO<Classement> {


	public XMLClassementDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	public Classement create(Classement obj) {
		// TODO Auto-generated method stub	
		return obj;
	}
	public boolean delete(Classement obj) {
		return true ;	
	}
	public Classement find(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	public Classement update(Classement obj) {
		// TODO Auto-generated method stub		
		return obj;
	}

}
