package ordersDerbyDB;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
 
public class MyFrame extends JFrame {
	String query1 = "select * from executers";
	String query2 = "select * from drivers";
	String query3 = "select * from autos";
	String query4 = "select * from orders";
    MakeResultSetTable resSetTable; 
    JTable table;                       
    JLabel msgline;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton insertButton1;
    private JButton insertButton2;
    private JButton insertButton3;
    private JButton insertButton4;
    
    class PushingListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            Object button = e.getSource();
            if(button instanceof JButton){
            	switch (((JButton) button).getText()) {
            		case "Исполнители":
            			displayQueryResults(query1);
            			break;
            		case "Водители":
            			displayQueryResults(query2);
            			break;
            		case "Автомобили":
            			displayQueryResults(query3);
            			break;
            		case "Заказы":
            			displayQueryResults(query4);
            			break;
            	}
             }
        }
    }
    
    class InsertListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            Object button = e.getSource();
            if(button instanceof JButton){
            	switch (((JButton) button).getText()) {
            		case "Исполнители":
            			new InsertExecuters();
            			break;
            		case "Водители":
            			new InsertDrivers();
            			break;
            		case "Автомобили":
            			new InsertAutos();
            			break;
            		case "Заказы":
            			new InsertOrders();
            			break;
            	}
             }
        }
    }
    
    public MyFrame (MakeResultSetTable f) {
    	
    	super("QueryFrame");
    	
    	ImageIcon execIcon = new ImageIcon("images/executers.gif");
    	JLabel execViewLabel = new JLabel("Просмотр", execIcon, JLabel.CENTER);
    	JLabel execInsertLabel = new JLabel("Добавление", execIcon, JLabel.CENTER);
    	
    	ImageIcon drivIcon = new ImageIcon("images/drivers.gif");
    	JLabel drivViewLabel = new JLabel("Просмотр", drivIcon, JLabel.CENTER);
    	JLabel drivInsertLabel = new JLabel("Добавление", drivIcon, JLabel.CENTER);
    	
    	ImageIcon autoIcon = new ImageIcon("images/autos.gif");
    	JLabel autoViewLabel = new JLabel("Просмотр", autoIcon, JLabel.CENTER);
    	JLabel autoInsertLabel = new JLabel("Добавление", autoIcon, JLabel.CENTER);
    	
    	ImageIcon orderIcon = new ImageIcon("images/orders.gif");
    	JLabel orderViewLabel = new JLabel("Просмотр", orderIcon, JLabel.CENTER);
    	JLabel orderInsertLabel = new JLabel("Добавление", orderIcon, JLabel.CENTER);
    	
    	button1 = new JButton("Исполнители");
    	button2 = new JButton("Водители");
    	button3 = new JButton("Автомобили");
    	button4 = new JButton("Заказы");
    	insertButton1 = new JButton("Исполнители");
    	insertButton2 = new JButton("Водители");
    	insertButton3 = new JButton("Автомобили");
    	insertButton4 = new JButton("Заказы");
		table = new JTable();         
		msgline = new JLabel();
		
		JPanel selectPanel = new JPanel();
		selectPanel.setLayout(new GridLayout(2, 4));

		selectPanel.add(execViewLabel);
		selectPanel.add(drivViewLabel);
		selectPanel.add(autoViewLabel);
		selectPanel.add(orderViewLabel);
		selectPanel.add(button1);
		selectPanel.add(button2);
		selectPanel.add(button3);
		selectPanel.add(button4);
		
		JPanel insertPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		insertPanel.setLayout(new GridLayout(2, 4));
		bottomPanel.setLayout(new GridLayout(2, 1));

		insertPanel.add(execInsertLabel);
		insertPanel.add(drivInsertLabel);
		insertPanel.add(autoInsertLabel);
		insertPanel.add(orderInsertLabel);
		insertPanel.add(insertButton1);
		insertPanel.add(insertButton2);
		insertPanel.add(insertButton3);
		insertPanel.add(insertButton4);
		bottomPanel.add(msgline);
		bottomPanel.add(insertPanel);
		
		
		
		this.resSetTable = f;
		Container contentPane = getContentPane();
		contentPane.add(selectPanel, BorderLayout.NORTH);
		JScrollPane scroll = new JScrollPane(table);
		contentPane.add(scroll, BorderLayout.CENTER);
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		button1.addActionListener(new PushingListener());
		button2.addActionListener(new PushingListener());
		button3.addActionListener(new PushingListener());
		button4.addActionListener(new PushingListener());
		insertButton1.addActionListener(new InsertListener());
		insertButton2.addActionListener(new InsertListener());
		insertButton3.addActionListener(new InsertListener());
		insertButton4.addActionListener(new InsertListener());
    }
 
    public void displayQueryResults(final String q) {
    	msgline.setText("Соединение с БД...");
	
    	EventQueue.invokeLater(new Runnable() {
    		public void run() {
    			try {
    				table.setModel(resSetTable.getResultSetTableModel(q));
    				msgline.setText(" ");  
    			} catch (SQLException ex) {
    				msgline.setText(" ");
    				JOptionPane.showMessageDialog(MyFrame.this,
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
    	MyFrame frame = new MyFrame(resSetTable);  
    	frame.setSize(800,600);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setVisible(true);
    }
}