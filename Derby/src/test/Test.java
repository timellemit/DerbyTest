package test;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;


public class Test {
	public static void main(String[] args) {
		java.util.Date javaDate = new java.util.Date("2013/03/22");
        long t = javaDate.getTime();
        System.out.println("2013/03/22".replace('/', '-'));
        java.sql.Date sqlDate = new java.sql.Date(t);
        System.out.println(sqlDate);
        java.sql.Date sqlDate2 = new java.sql.Date(t);
        
        SimpleDateFormat df = new SimpleDateFormat("2005-03-21");
    
        
	}

}
