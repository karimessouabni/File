

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLWarning;

public class ConnectionManager {
        
        private static ConnectionManager instance;
        private  Connection conn;
        
        private ConnectionManager() throws Exception{
        	try {
    			// Load the jdbc driver for MySQL
    			Class.forName("com.mysql.jdbc.Driver");

    			// Get a connection to the database source using Thin JDBC driver
    			String url = "jdbc:mysql://localhost:3306/test";
    			conn = DriverManager.getConnection(url, "root", "root");

    			// Print stdout warning messages if necessary
    			checkForSQLWarnings(conn.getWarnings());

    			// Print stdout info messages
    			printInfo(conn);

    		} catch (SQLException e) {
    			System.err.println("\n*** SQLException caught in LoadDriver()");
    			printSQLErrors(e);
    			throw e;
    		} catch (Exception e) {
    			// This exception is caught if JDBC driver used cannot be loaded
    			System.err.println("\n*** Exception caught in LoadDriver()");
    			throw e;
    		}


                
        }

        
        public Connection getConnection(){
                
                return null;
                
        }
        
        public static ConnectionManager getInstance(){
                
                if(instance==null){
                        try {
							instance=new ConnectionManager();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                }
                return instance;
                
        }
        
        
        
        private void printInfo(Connection c) throws Exception {
    		// Get meta-data about the database
    		DatabaseMetaData info = c.getMetaData();
    		System.out.println("\nConnected to :\t" + info.getURL());
    		System.out.println("Driver :\t" + info.getDriverName());
    		System.out.println("Version :\t" + info.getDriverVersion());
    	}
        
    	private boolean checkForSQLWarnings(SQLWarning w) throws SQLException {
    		boolean warning = false;
    		if (w != null) {
    			warning = true;
    			System.out.println("\n**** Warning ****\n");

    			while (w != null) {
    				System.out.println("SQLState: " + w.getSQLState());
    				System.out.println("Message:  " + w.getMessage());
    				System.out.println("Vendor:   " + w.getErrorCode());
    				System.out.println("");
    				w = w.getNextWarning();
    			}
    		}
    		return warning;
    	}
    	
    	
    	private void printSQLErrors(SQLException e) {
    		while (e != null) {
    			System.err.println("SQLState: " + e.getSQLState());
    			System.err.println("Message:  " + e.getMessage());
    			System.err.println("Vendor:   " + e.getErrorCode());
    			System.err.println("");
    			e = e.getNextException();
    		}
    	}

}

// aajouter insert et quiery