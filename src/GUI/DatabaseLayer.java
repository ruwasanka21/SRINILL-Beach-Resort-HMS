
package GUI;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseLayer {
    
    public static Connection mycon() {
        Connection con = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/panorama_hotel", "root", "");
        } catch (Exception e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
        return con;
    } 
}
