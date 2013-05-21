package DAO;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
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
			
				PreparedStatement prepare = this.connect
						.prepareStatement("INSERT INTO Classement (C_id, C_nom, C_sousClassement) VALUES(null, ?, ?)",Statement.RETURN_GENERATED_KEYS);
			

				prepare.setString(1, obj.getNom());
				prepare.setString(2, obj.getSousClassement());
				prepare.executeUpdate();
				ResultSet keyResultSet = prepare.getGeneratedKeys();
		        long newC_id = 0;
		        if (keyResultSet.next()) {
		        	newC_id = (int) keyResultSet.getLong(1);
		            
		        }

					
				
				
			
				obj = this.find(newC_id);

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
					"SELECT * FROM Classement WHERE C_id = " + id);
			if (result.first())
				try {
					f = new Classement(id, result.getString("C_nom"),
							(String) result
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
