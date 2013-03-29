package ordersDerbyDB;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Properties;

//INSERT NEW RECORD

public class InsertExecuters implements ActionListener {
    JFrame fr;
    JPanel po;

    JLabel l2, l3, l4, l5, main;
    JTextField adressTextField, phoneTextField, nameTextField;

    GridBagConstraints gbc;
    GridBagLayout go;
    JButton ok, exit;

    public InsertExecuters() {
	    fr = new JFrame("Добавление исполнителя");
	    
	    po = new JPanel();
	    fr.getContentPane().add(po);
	    fr.setVisible(true);
	    fr.setSize(800, 200);
	
	    fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    go = new GridBagLayout();
	    gbc = new GridBagConstraints();
	    po.setLayout(go);
	    
	    l2 = new JLabel("Адрес");
	    adressTextField = new JTextField(50);
	
	    l3 = new JLabel("Телефон");
	    phoneTextField = new JTextField(20);
	    
	    l4 = new JLabel("Название");
	    nameTextField = new JTextField(30);
	
	    ok = new JButton("Выполнить");
	
	    exit = new JButton("Выход");
	
	
		//--------------------------------------------
	
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.gridx = 5;
	    gbc.gridy = 5;
	    go.setConstraints(l2, gbc);
	    po.add(l2);
	
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.gridx = 10;
	    gbc.gridy = 5;
	    go.setConstraints(adressTextField, gbc);
	    po.add(adressTextField);
	
	    //--------------------------------------------
	
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.gridx = 5;
	    gbc.gridy = 10;
	    go.setConstraints(l3, gbc);
	    po.add(l3);
	
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.gridx = 10;
	    gbc.gridy = 10;
	    go.setConstraints(phoneTextField, gbc);
	    po.add(phoneTextField);
	
	    //--------------------------------------------
	
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.gridx = 5;
	    gbc.gridy = 20;
	    go.setConstraints(l4, gbc);
	    po.add(l4);
	
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.gridx = 10;
	    gbc.gridy = 20;
	    go.setConstraints(nameTextField, gbc);
	    po.add(nameTextField);
	    
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
                            .prepareStatement("Insert into executers(adress, phone, name)" +
                            		" values(?,?,?)");

            String adr = adressTextField.getText();
            String phone = phoneTextField.getText();
            String name = nameTextField.getText();

            ps.setString(1, adr);
            ps.setString(2, phone);
            ps.setString(3, name);

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
            new InsertExecuters();

    }

}

