package ordersDerbyDB;

import java.sql.*;
import javax.swing.table.*;
import javax.swing.event.*;

public class ResultSetTableModel implements TableModel {
    ResultSet results;         
    ResultSetMetaData metadata;   
    int numcols, numrows;          
    
    ResultSetTableModel(ResultSet results) throws SQLException {
		this.results = results;          
		metadata = results.getMetaData();      
		numcols = metadata.getColumnCount();
		results.last(); 
		numrows = results.getRow(); 
    }

    public void close() {
    	try { 
    		results.getStatement().close();
    	} catch(SQLException e) {};
    }

    protected void finalize() { 
    	close(); 
    }

    public int getColumnCount() { 
    	return numcols; 
    }
    
    public int getRowCount() { 
    	return numrows; 
    }

    public String getColumnName(int column) {
		try {
		    return metadata.getColumnLabel(column+1);
		} catch (SQLException e) { return e.toString(); }
	    }

    public Class getColumnClass(int column) { 
    	return String.class; 
    }
    
    public Object getValueAt(int row, int column) {
		try {
		    results.absolute(row+1);             
		    Object o = results.getObject(column+1); 
		    if (o == null) return null;       
		    else return o.toString();             
		} catch (SQLException e) { 
			return e.toString(); 
		}
    }

    public boolean isCellEditable(int row, int column) { 
    	return false; 
    } 
    public void setValueAt(Object value, int row, int column) {}
    public void addTableModelListener(TableModelListener l) {}
    public void removeTableModelListener(TableModelListener l) {}
}