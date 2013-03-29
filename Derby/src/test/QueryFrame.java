package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class QueryFrame extends JFrame {
	MakeResultSetTable resSetTable;   
    JTextField query;                  
    JTable table;                       
    JLabel msgline;                 

    public QueryFrame(MakeResultSetTable f) {
    	super("QueryFrame");  // Set window title

    	addWindowListener(new WindowAdapter() {
    		public void windowClosing(WindowEvent e) { 
    			System.exit(0); 
    		}
	    });

    	this.resSetTable = f;

		query = new JTextField();     // Lets the user enter a query
		table = new JTable();         // Displays the table
		msgline = new JLabel();       // Displays messages

		Container contentPane = getContentPane();
		contentPane.add(query, BorderLayout.NORTH);
		contentPane.add(new JScrollPane(table), BorderLayout.CENTER);
		contentPane.add(msgline, BorderLayout.SOUTH);

		query.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayQueryResults(query.getText());
			}
	    });
    }

    public void displayQueryResults(final String q) {
    	msgline.setText("Contacting database...");
	
    	EventQueue.invokeLater(new Runnable() {
    		public void run() {
    			try {
    				table.setModel(resSetTable.getResultSetTableModel(q));
    				msgline.setText(" ");  
    			} catch (SQLException ex) {
    				msgline.setText(" ");
    				JOptionPane.showMessageDialog(QueryFrame.this,
    				new String[] { 
    						ex.getClass().getName() + ": ",
    						ex.getMessage()
    				});
    			}
    		}
	    });
    }

    public static void main(String args[]) throws Exception {
    	MakeResultSetTable resSetTable = 
	    new MakeResultSetTable("org.apache.derby.jdbc.EmbeddedDriver",
	    		"jdbc:derby:/home/jury_kashnitskiy/workspace/derbyDB/MyDB", 
	    		"jury_kashnitskiy", "yura1990");

    	QueryFrame qf = new QueryFrame(resSetTable);  
    	qf.setSize(500, 600);
    	qf.setVisible(true);
    }
}