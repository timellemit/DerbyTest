package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class TestConn
{
    private String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private String protocol = "jdbc:derby:";
    
    public static void main(String[] args)
    {
        new TestConn().go();
        System.out.println("TestConn finished");
    }

    void go()
    {
        loadDriver();
        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;
        try
        {
            Properties props = new Properties();
            props.put("user", "jury_kashnitskiy");
            props.put("password", "yura1990");

            String dbName = "/home/jury_kashnitskiy/workspace/derbyDB/MyDB"; 
            conn = DriverManager.getConnection(protocol + dbName,props);

            System.out.println("Connected to database " + dbName);
            conn.setAutoCommit(false);
            s = conn.createStatement();          
            rs = s.executeQuery(
                  "SELECT * from executers");
            while (rs.next()) {
                System.out.print(rs.getString(1) + ", ");
                System.out.print(rs.getString(2) + ", ");
                System.out.print(rs.getString(3) + ", ");
                System.out.println(rs.getString(4));
            }
            rs.close();
            s.close();
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
        } catch (SQLException sqle)
        {
            System.out.println(sqle);
        }

                    
    }
    
    private void loadDriver() {
        try {
            Class.forName(driver).newInstance();
            System.out.println("Loaded the appropriate driver");
        } catch (ClassNotFoundException cnfe) {
            System.err.println("\nUnable to load the JDBC driver " + driver);
            System.err.println("Please check your CLASSPATH.");
            cnfe.printStackTrace(System.err);
        } catch (InstantiationException ie) {
            System.err.println(
                        "\nUnable to instantiate the JDBC driver " + driver);
            ie.printStackTrace(System.err);
        } catch (IllegalAccessException iae) {
            System.err.println(
                        "\nNot allowed to access the JDBC driver " + driver);
            iae.printStackTrace(System.err);
        }
    }
}