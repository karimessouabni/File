package DAO;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *  
 * @author Karim 
 */
public class ConnectionManager {

    public Connection conn;
    private Statement statement;
    public static ConnectionManager db;

    private ConnectionManager() {
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "karimDB";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "root";
        try {
            Class.forName(driver).newInstance();
            this.conn = (Connection) DriverManager.getConnection(url + dbName, userName, password);
        } catch (Exception sqle) {
            sqle.printStackTrace();
        }
    }

    /**
     *
     * @return MysqlConnect Database connection object
     */
    public static synchronized ConnectionManager getDbCon() {
        if (db == null) {
            db = new ConnectionManager();
        }
        return db;

    }

    /**
     *
     * @param query String The query to be executed
     * @return a ResultSet object containing the results or null if not
     * available
     * @throws SQLException
     */
    public ResultSet select(String query) throws SQLException {
        statement = db.conn.createStatement();
        ResultSet res = statement.executeQuery(query);
        db.conn.close();
        return res;
    }

    /**
     * @desc Method to insert data to a table
     * @param insertQuery String The Insert query
     * @return boolean
     * @throws SQLException
     */
    private void close() throws SQLException {
        db.conn.close();
    }

    public int insert(String insertQuery) throws SQLException {
        statement = db.conn.createStatement();
        int result = statement.executeUpdate(insertQuery);
        db.conn.close();
        return result;

    }
}