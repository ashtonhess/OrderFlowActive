/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderflowmain;

//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ashtonhess
 */

//References to use!: 
// 1. https://www.tutorialspoint.com/jdbc/jdbc-insert-records.htm
// 2. https://www.tutorialspoint.com/jdbc/jdbc-db-connections.htm

public abstract class AbstractDatabaseSupport {
    
    public String databaseURL = "jdbc:mysql://localhost:3306";
    public String databaseUser = "admin";
    public String databasePass = "";
    //public String query1 = "INSERT INTO RecordObjects (orderNumber, contactEmail) VALUES (1111, 'ashton.hess@cerner.com');";
    
    public Connection conn;
    
    public Connection getConnection() throws SQLException {
        //Connection conn = null;
        
        Properties connProperties = new Properties();
        connProperties.put("user", this.databaseUser);
        connProperties.put("pass", this.databasePass);
        
        try(Connection connTry = DriverManager.getConnection(databaseURL, connProperties);){
            conn = connTry;
            System.out.println("Connected to database successfully.");
        } catch (SQLException e){
            System.out.println("Error connecting to database.");
            e.printStackTrace();
        }
        return conn;
    }
    
    public void closeConnection(Connection conn){
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error closing database connection.");
            Logger.getLogger(AbstractDatabaseSupport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
