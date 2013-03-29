package ordersDerbyDB;

import java.sql.*;

public class MakeResultSetTable {
	
    Connection connection;

    public MakeResultSetTable(String driverClassName, String dbname, String username, String password)
    throws ClassNotFoundException, SQLException {
    	Class.forName(driverClassName);
    	connection = DriverManager.getConnection(dbname, username, password);
    }
    
    public ResultSetTableModel getResultSetTableModel(String query) throws SQLException {
		if (connection == null)
		    throw new IllegalStateException("Connection already closed.");
		Statement statement = connection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		ResultSet r = statement.executeQuery(query);
		return new ResultSetTableModel(r);
    }

    public void close() {
    	try { 
    		connection.close(); 
    	} catch (Exception e) {}     
    	connection = null; 
    }

    protected void finalize() { 
    	close(); 
    }
}