package ordersDerbyDB;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Properties;

//INSERT NEW RECORD

public class InsertDrivers implements ActionListener {
    JFrame fr;
    JPanel po;

    JLabel l2, l3, l4, l5, main;
    JTextField nameTextField;

    JComboBox auto, status;
    GridBagConstraints gbc;
    GridBagLayout go;
    JButton ok, exit;

    public InsertDrivers() {
	    fr = new JFrame("Добавление водителя");
	    
	    po = new JPanel();
	    fr.getContentPane().add(po);
	    fr.setVisible(true);
	    fr.setSize(600, 200);
	
	    fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	    go = new GridBagLayout();
	    gbc = new GridBagConstraints();
	    po.setLayout(go);
	    
	    l2 = new JLabel("Имя");
	    nameTextField = new JTextField(30);
	
	    l3 = new JLabel("Автомобиль");
	    String str3[] = { "Renault Megan", "Toyotta Celica", "BMW X6"};
	    auto = new JComboBox(str3);
	    
	    l4 = new JLabel("Статус");
	    String str4[] = {"Свободен", "С ночи", "На заказе", "Отпуск"};
	    status = new JComboBox(str4);
	
	    
	
	    ok = new JButton("Выполнить");
	
	    exit = new JButton("Выход");
	
	    status.addActionListener(this);
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
	    go.setConstraints(nameTextField, gbc);
	    po.add(nameTextField);
	
	    //--------------------------------------------
	
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.gridx = 5;
	    gbc.gridy = 10;
	    go.setConstraints(l3, gbc);
	    po.add(l3);
	
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.gridx = 10;
	    gbc.gridy = 10;
	    go.setConstraints(auto, gbc);
	    po.add(auto);
	
	    //--------------------------------------------
	
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.gridx = 5;
	    gbc.gridy = 20;
	    go.setConstraints(l4, gbc);
	    po.add(l4);
	
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.gridx = 10;
	    gbc.gridy = 20;
	    go.setConstraints(status, gbc);
	    po.add(status);
	
	    //--------------------------------------------
	
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.gridx = 10;
	    gbc.gridy = 60;
	    go.setConstraints(ok, gbc);
	    po.add(ok);
	
	    ok.addActionListener(this);
	
	    //--------------------------------------------
	
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.gridx = 11;
	    gbc.gridy = 60;
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
                            .prepareStatement("Insert into drivers(name, auto_id, status_id)" +
                            		" values(?,?,?)");

            String name = nameTextField.getText();
            String au = auto.getSelectedItem().toString();
            switch (au) {
        	case "Renault Megan":
        		au = "1";
        		break;
        	case "Toyotta Celica":
        		au = "2";
        		break;
        	case "BMW X6":
        		au = "3";
        		break;
            }
            String st = status.getSelectedItem().toString();
            switch (st) {
        	case "Свободен":
        		st = "1";
        		break;
        	case "С ночи":
        		st = "2";
        		break;
        	case "На заказе":
        		st = "3";
        		break;
        	case "Отпуск":
        		st = "4";
        		break;       		
            }
            
           
            ps.setString(1, name);
            ps.setString(2, au);
            ps.setString(3, st);

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
            new InsertDrivers();

    }

}

