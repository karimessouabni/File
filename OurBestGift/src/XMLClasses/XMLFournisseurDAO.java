package XMLClasses;

import java.sql.Connection;

import Administration_des_Fournisseurs.Fournisseur;
import DAO.DAO;

/**
 * @author karim
 *
 */
public class XMLFournisseurDAO extends DAO<Fournisseur> {


	

		public XMLFournisseurDAO(Connection conn) {
			super(conn);
			// TODO Auto-generated constructor stub
		}
		public Fournisseur create(Fournisseur obj) {
			// TODO Auto-generated method stub	
			return obj;
		}
		public boolean delete(Fournisseur obj) {
			return true ;	
		}
		public Fournisseur find(long id) {
			// TODO Auto-generated method stub
			return null;
		}
		public Fournisseur update(Fournisseur obj) {
			// TODO Auto-generated method stub		
			return obj;
		}

	}

