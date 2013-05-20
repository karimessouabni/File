package DAO;

import XMLClasses.XMLDAOFactory;


/**
 * @author Karim
 *
 */
public abstract class AbstractDAOFactory {

	public abstract DAO getAdministrateurDAO();
	public abstract DAO getFournisseurDAO();
	public abstract DAO getClassementDAO();

	/**
	 * Méthode nous permettant de récupérer une factory de DAO
	 * @param type
	 * @return AbstractDAOFactory
	 */
	public static AbstractDAOFactory getFactory(FactoryType type){
		
		if(type.equals(type.DAO_FACTORY)) 
			return new DAOFactory();
		
		if(type.equals(type.XML_DAO_Factory))
			return new XMLDAOFactory();
		
		return null;
	}
	
}
