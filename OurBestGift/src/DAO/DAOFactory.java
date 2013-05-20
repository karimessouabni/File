package DAO;


import java.sql.Connection;

import Administration_des_Fournisseurs.Administrateur;
import Administration_des_Fournisseurs.Classement;
import Administration_des_Fournisseurs.Fournisseur;


/**
 * @author karim
 *
 */
public class DAOFactory extends AbstractDAOFactory {
protected static final Connection conn = ConnectionManager.getDbCon().conn;   
  
/**
* Retourne un objet Administrateur interagissant avec la BDD
* @return DAO
*/
public DAO<Administrateur> getAdministrateurDAO(){
  return new AdministrateurDAO(conn);
}

/**
* Retourne un objet Fournisseur interagissant avec la BDD
* @return DAO
*/
public DAO<Fournisseur> getFournisseurDAO(){
  return new FournisseurDAO(conn);
}

/**
* Retourne un objet Classement interagissant avec la BDD
* @return DAO
*/
public  DAO<Classement> getClassementDAO(){
  return new ClassementDAO(conn);
}


}