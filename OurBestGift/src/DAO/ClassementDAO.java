package DAO;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Administration_des_Fournisseurs.Classement;
import Administration_des_Fournisseurs.Fournisseur;

/**
 * @author Karim
 * 
 */
public class ClassementDAO extends DAO<Classement> {

	public ClassementDAO(Connection conn) {
		super(conn);
	}

	public Classement create(Classement obj) {
		try {

			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery(
					"SELECT NEXTVAL('Classement_id_seq') as id"); // faut créer
																	// une
																	// sequence
																	// dans la
																	// BD pour
																	// avoir
																	// l'id
																	// suivant
																	// avant
																	// l'inserations
			if (result.first()) {
				long id = result.getLong("id");
				PreparedStatement prepare = this.connect
						.prepareStatement("INSERT INTO Classement (Classement_id, Classement_nom) VALUES(?, ?)");
				prepare.setLong(1, id);
				prepare.setString(2, obj.getNom());
				prepare.setArray(3, (Array) obj.getSousClassementList());

				prepare.executeUpdate();
				obj = this.find(id);

			}
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

	public boolean delete(Classement obj) {
		try {

			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeUpdate(
					"DELETE FROM Classement WHERE C_id = " + obj.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		Classement fou = this.find(obj.getId());
		if (fou.equals(null))
			return true; // si il existe encore sur la BD
		return false;
	}

	public Classement update(Classement obj) {
		return null;
		// TO DO..
	}

	public Classement find(long id) {
		Classement f = null;

		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery(
					"SELECT * FROM Classement WHERE A_id = " + id);
			if (result.first())
				try {
					f = new Classement(id, result.getString("C_nom"),
							(ArrayList<String>) result
									.getObject("C_sousClassement"));
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
