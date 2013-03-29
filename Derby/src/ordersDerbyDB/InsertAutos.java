package ordersDerbyDB;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Properties;

//INSERT NEW RECORD

public class InsertAutos implements ActionListener {
    JFrame fr;
    JPanel po;

    JLabel l1, l2, l3, l4, l5, main;
    JTextField stateNumTextField, carBrandTextField;

    JComboBox status, driver;
    GridBagConstraints gbc;
    GridBagLayout go;
    JButton ok, exit;

    public InsertAutos() {
	    fr = new JFrame("Добавление автомобиля");

	    po = new JPanel();
	    fr.getContentPane().add(po);
	    fr.setVisible(true);
	    fr.setSize(500, 200);
	
	    fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	    go = new GridBagLayout();
	    gbc = new GridBagConstraints();
	    po.setLayout(go);
	    
	    l2 = new JLabel("Госномер");
	    stateNumTextField = new JTextField(10);
	    
	    l3 = new JLabel("Марка");
	    carBrandTextField = new JTextField(30);
	
	    l4 = new JLabel("Водитель");
	    String str4[] = { "Вершинин Сергей", "Алексеев Игорь", "Чехов Юрий"};
	    driver = new JComboBox(str4);
	
	    l5 = new JLabel("Статус");
	    String str5[] = { "На заказе", "Свободен", "В ремонте"};
	    status = new JComboBox(str5);
		
	    ok = new JButton("Выполнить");
	
	    exit = new JButton("Выход");

	    driver.addActionListener(this);
	    status.addActionListener(this);
	
	 // ///////////////////////////////////////
		
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.gridx = 5;
	    gbc.gridy = 5;
	    go.setConstraints(l2, gbc);
	    po.add(l2);
	
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.gridx = 10;
	    gbc.gridy = 5;
	    go.setConstraints(stateNumTextField, gbc);
	    po.add(stateNumTextField);
	
	    //--------------------------------------------
	    
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.gridx = 5;
	    gbc.gridy = 10;
	    go.setConstraints(l3, gbc);
	    po.add(l3);
	
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.gridx = 10;
	    gbc.gridy = 10;
	    go.setConstraints(carBrandTextField, gbc);
	    po.add(carBrandTextField);
	
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
	    go.setConstraints(status, gbc);
	    po.add(status);
	
		//--------------------------------------------
	    
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.gridx = 10;
	    gbc.gridy = 120;
	    go.setConstraints(ok, gbc);
	    po.add(ok);
	
	    ok.addActionListener(this);
	
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.gridx = 11;
	    gbc.gridy = 120;
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
                            .prepareStatement("Insert into autos(state_number, brand, driver_id, status_id) " +
                            		"values(?,?,?,?)");

            String stateNum = stateNumTextField.getText();
            String carBrand = carBrandTextField.getText();

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
          
            String st = status.getSelectedItem().toString();
            switch (st) {
	        	case "На заказе":
	        		st = "1";
	        		break;
	        	case "Свободен":
	        		st = "2";
	        		break;
	        	case "В ремонте":
	        		st = "3";
	        		break;
            }
            
            ps.setString(1, stateNum);
            ps.setString(2, carBrand);
            ps.setString(3, dr);
            ps.setString(4, st);

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
            new InsertAutos();

    }

}

