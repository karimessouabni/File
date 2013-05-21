package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Administration_des_Fournisseurs.Fournisseur;
import Administration_des_Fournisseurs.Classement;

/**
 * @author karim
 * 
 */
public class FournisseurDAO extends DAO<Fournisseur> {

	public FournisseurDAO(Connection conn) {
		super(conn);
	}

	public Fournisseur create(Fournisseur obj) {
		final String sqlQuery ="INSERT INTO Fournisseur (F_id, F_mail, F_mdp, F_nbventedecide, F_taxe, F_classements ) VALUES(null, ?, ?, ?, ?, ?)";
		try {
			

			PreparedStatement prepare = this.connect.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
			
			
	
				prepare.setString(1, obj.getMailContact());
				prepare.setString(2, obj.getMdp());
				prepare.setInt(3, obj.getNbVenteDecide());
				prepare.setFloat(4, obj.getTaxe());
				prepare.setLong(5, obj.getClassements().getId());
				prepare.executeUpdate();
				
				ResultSet keyResultSet = prepare.getGeneratedKeys();
		        long newF_id = 0;
		        if (keyResultSet.next()) {
		        	newF_id = (int) keyResultSet.getLong(1);
		            
		        }

 
					
				obj = this.find(newF_id);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (this.connect != null)
					this.connect.close();
			} catch (SQLException sqle) {
			}
		}
		return obj;

	}

	public boolean delete(Fournisseur obj) {
		try {

			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeUpdate(
					"DELETE FROM Fournisseur WHERE F_id = "
							+ obj.getIdentifiant());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		Fournisseur fou = this.find(obj.getIdentifiant());
		if (fou.equals(null))
			return true; // si il existe encore sur la BD
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DAO.DAO#update(java.lang.Object) Update d'un Fournisseur sur la BD
	 */
	public Fournisseur update(Fournisseur obj) {
		try {
			DAO<Classement> ClassementDAO = AbstractDAOFactory.getFactory(
					FactoryType.DAO_FACTORY).getClassementDAO();
			// Si le Classement n'existe pas en base, on le crée
			if (obj.getClassements().getId() == 0) {
				obj.setClassements(ClassementDAO.create(obj.getClassements()));
			}
			// On met à jours l'objet Classement
			ClassementDAO.update(obj.getClassements());

			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeUpdate(
					"UPDATE Fournissuer SET F_mdp = '" + obj.getMdp() + "',"
							+ " F_mail = '" + obj.getMailContact() + "',"
							+ "'," + " F_nbVenteDecide = '" + obj.getTaxe()
							+ "'," + "'," + " F_taxe = '"
							+ obj.getClassements().getId() + "',"
							+ " F_classements = '"
							+ obj.getClassements().getId() + "'"
							+ " WHERE F_id = " + obj.getIdentifiant());

			obj = this.find(obj.getIdentifiant());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return obj;
	}

	public Fournisseur find(long id) {
		Fournisseur f = null;

		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery(
					"SELECT * FROM Fournisseur WHERE F_id = " + id);
			if (result.first())
				try {
					DAO<Classement> ClassementDAO = AbstractDAOFactory
							.getFactory(FactoryType.DAO_FACTORY)
							.getClassementDAO();
					f = new Fournisseur(id, result.getString("F_mdp"),
							result.getString("F_mail"),
							result.getInt("F_nbVenteDecide"),
							result.getInt("F_taxe"),
							ClassementDAO.find((long) (result
									.getLong("F_classements")))); // on cherche
																	// le
																	// Classement
																	// dans la
																	// table
																	// classement
																	// a laide
																	// de son id
					// (Classement) result.getObject("F_classements")); // A
					// revoir..
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return f;
	}

}
