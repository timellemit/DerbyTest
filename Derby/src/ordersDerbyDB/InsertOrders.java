package ordersDerbyDB;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Properties;

//INSERT NEW RECORD

public class InsertOrders implements ActionListener {
    JFrame fr;
    JPanel po;

    JLabel l2, l3, l4, l5, main;
    JTextField dateTextField;

    JComboBox date, auto, exec, driver;
    GridBagConstraints gbc;
    GridBagLayout go;
    JButton ok, exit;

    public InsertOrders() {
	    fr = new JFrame("Вставка в таблицу ORDERS");
	    
	    po = new JPanel();
	    fr.getContentPane().add(po);
	    fr.setVisible(true);
	    fr.setSize(400, 200);
	
	    fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	    go = new GridBagLayout();
	    gbc = new GridBagConstraints();
	    po.setLayout(go);
	    
	    l2 = new JLabel("Дата");
	    dateTextField = new JTextField(10);
	    
	    l3 = new JLabel("Исполнитель");
	    String str3[] = { "Z2 Logistic", "A2 Logistic","С-Карго"};
	    exec = new JComboBox(str3);
	
	    l4 = new JLabel("Водитель");
	    String str4[] = { "Вершинин Сергей", "Алексеев Игорь","Чехов Юрий"};
	    driver = new JComboBox(str4);
	
	    l5 = new JLabel("Автомобиль");
	    String str5[] = { "Renault Megan", "Toyotta Celica", "BMW X6"};
	    auto = new JComboBox(str5);
	
	    
	
	    ok = new JButton("Выполнить");
	
	    exit = new JButton("Выход");
	
	    exec.addActionListener(this);
	    driver.addActionListener(this);
	    auto.addActionListener(this);
	
	
		//--------------------------------------------
	
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.gridx = 5;
	    gbc.gridy = 5;
	    go.setConstraints(l2, gbc);
	    po.add(l2);
	
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.gridx = 10;
	    gbc.gridy = 5;
	    go.setConstraints(dateTextField, gbc);
	    po.add(dateTextField);
	
	    //--------------------------------------------
	
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.gridx = 5;
	    gbc.gridy = 10;
	    go.setConstraints(l3, gbc);
	    po.add(l3);
	
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.gridx = 10;
	    gbc.gridy = 10;
	    go.setConstraints(exec, gbc);
	    po.add(exec);
	
	    //--------------------------------------------
	
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.gridx = 5;
	    gbc.gridy = 20;
	    go.setConstraints(l4, gbc);
	    po.add(l4);
	
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.gridx = 10;
	    gbc.gridy = 20;
	    go.setConstraints(driver, gbc);
	    po.add(driver);
	
	    //--------------------------------------------
	
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.gridx = 5;
	    gbc.gridy = 60;
	    go.setConstraints(l5, gbc);
	    po.add(l5);
	
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.gridx = 10;
	    gbc.gridy = 60;
	    go.setConstraints(auto, gbc);
	    po.add(auto);
	    
	    //--------------------------------------------
	
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.gridx = 10;
	    gbc.gridy = 190;
	    go.setConstraints(ok, gbc);
	    po.add(ok);
	
	    ok.addActionListener(this);
	
	    //--------------------------------------------
	
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.gridx = 11;
	    gbc.gridy = 190;
	    go.setConstraints(exit, gbc);
	    po.add(exit);
	
	    exit.addActionListener(this);

    }

    public void actionPerformed(ActionEvent ev) {

    if (ev.getSource() == ok) {

            try {
            Connection conn;
            Properties props = new Properties();
            props.put("user", "jury_kashnitskiy");
            props.put("password", "yura1990");

            String dbName = "jdbc:derby:/home/jury_kashnitskiy/workspace/derbyDB/MyDB"; 
            conn = DriverManager.getConnection(dbName,props);

            PreparedStatement ps = conn
                            .prepareStatement("Insert into orders(order_date,executers_id,driver_id,auto_id)" +
                            		" values(?,?,?,?)");

            String ex = exec.getSelectedItem().toString();
            switch (ex) {
            	case "Z2 Logistic":
            		ex = "1";
            		break;
            	case "A2 Logistic":
            		ex = "2";
            		break;
            	case "С-Карго":
            		ex = "3";
            		break;
            }
            String dr = driver.getSelectedItem().toString();
            switch (dr) {
        	case "Вершинин Сергей":
        		dr = "1";
        		break;
        	case "Алексеев Игорь":
        		dr = "2";
        		break;
        	case "Чехов Юрий":
        		dr = "3";
        		break;
            }
            
            String dateStr = dateTextField.getText();
            java.util.Date javaDate = new java.util.Date(dateStr);
            long t = javaDate.getTime();
            java.sql.Date sqlDate = new java.sql.Date(t);
            
            String au = auto.getSelectedItem().toString();
            switch (au) {
        	case "Renault Megan":
        		au = "1";
        		break;
        	case "Toyota Celica":
        		au = "2";
        		break;
        	case "BMW X6":
        		au = "3";
        		break;
            }
            
            ps.setDate(1, sqlDate);
            ps.setString(2, ex);
            ps.setString(3, dr);
            ps.setString(4, au);

            ps.executeUpdate();
            conn.close();

            JOptionPane.showMessageDialog(null, "Запись добавлена успешно.","Запись добавлена",
                    JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {

                    System.out.println("ERROR  " + e);
                    e.printStackTrace();

            }

            }

            if (ev.getSource() == exit) {

                    fr.dispose();

            }
    }

    public static void main(String a[]) {
            new InsertOrders();

    }

}

