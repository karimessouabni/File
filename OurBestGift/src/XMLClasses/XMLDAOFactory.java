package XMLClasses;

import DAO.AbstractDAOFactory;
import DAO.DAO;



/**
 * @author karim
 *
 */
public class XMLDAOFactory extends AbstractDAOFactory {
	public DAO getAdministrateurDAO() {
		return new XMLAdministrateurDAO(null);
	}
	public DAO getFournisseurDAO() {
		return new XMLFournisseurDAO(null);
	}
	public DAO getClassementDAO() {
		return new XMLClassementDAO(null);
	}
}

