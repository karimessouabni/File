package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Administration_des_Fournisseurs.Administrateur;
import Administration_des_Fournisseurs.Fournisseur;

/**
 * @author Karim
 * 
 */
public class AdministrateurDAO extends DAO<Administrateur> {

	public AdministrateurDAO(Connection conn) {
		super(conn);
	}

	public Administrateur create(Administrateur obj) {
		try {

			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery(
					"SELECT NEXTVAL('Administrateur_id_seq') as id");
			if (result.first()) {
				long id = result.getLong("id");
				PreparedStatement prepare = this.connect
						.prepareStatement("INSERT INTO Administrateur (A_id, A_mail, A_mp) VALUES(?, ?, ?)");
				prepare.setLong(1, id);
				prepare.setString(2, obj.getMail());
				prepare.setString(2, obj.getMp());

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

	public boolean delete(Administrateur obj) {
		try {

			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeUpdate(
					"DELETE FROM Administrateur WHERE A_id = "
							+ obj.getIdentifiant());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		Administrateur fou = this.find(obj.getIdentifiant());
		if (fou.equals(null))
			return true; // si il existe encore sur la BD
		return false;
	}

	public Administrateur update(Administrateur obj) {
		return null;
	}

	public Administrateur find(long id) {
		Administrateur f = null;

		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery(
					"SELECT * FROM Administrateur WHERE A_id = " + id);
			if (result.first())
				try {
					f = new Administrateur(id, result.getString("A_mdp"),
							result.getString("A_mail"));
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
