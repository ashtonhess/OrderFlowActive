/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderflowmain;

import javafx.util.Pair;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ashtonhess
 */

//as of right now all of this needs to be tested... 10-12-21
public class DatabaseSupport {
    
    public String databaseURL = "jdbc:mysql://localhost:3306/bOrderFlow";
    public String databaseUser = "root";
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
    
    //function returns true if the connection is active.
    //returns false if the connection is inactive.
    public Boolean connectionStatus(Connection conn){
        try {
            if (conn.isClosed()==true) {
                System.out.println("The connection is closed.");
                return false;
            }else{
                System.out.println("The connection is open.");
                return true;
                        }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseSupport.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    public void closeConnection(Connection conn){
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error closing database connection.");
            Logger.getLogger(AbstractDatabaseSupport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

        //executeQuery function.
        //INPUTS: String: a query written exactly as it should be.
        //OUTPUTS: Boolean: if the query was successfully executed or not.
        //         ResultSet: the set that the query returned.
    public Pair<Boolean,ResultSet> executeQuery(String queryString){
        Connection executeQueryConn = null;
//        Pair<Boolean,ResultSet> resultPair = new Pair(false,null);
        ResultSet executeQueryResult;
        try{
            executeQueryConn = getConnection();
            if (this.connectionStatus(executeQueryConn)==true) {
                Statement stmt = executeQueryConn.createStatement();
                executeQueryResult =  stmt.executeQuery(queryString);
                return new Pair<Boolean, ResultSet>(true,executeQueryResult);
            }else{
                return new Pair<Boolean, ResultSet>(false,null);
            }
        }catch(SQLException sqlExcept){
            System.out.println("getConnection() failed. Database error.");
            return new Pair<Boolean, ResultSet>(false,null);
        }
//        return new Pair<Boolean, ResultSet>(false,null);
    }
    
    public Boolean uploadRecordObject(OrderRecordClass record){
         Properties connProperties = new Properties();
         connProperties.put("user", this.databaseUser);
         connProperties.put("pass", this.databasePass);
         
         Connection currentConnection = null;
         try{
             try {
                 Class.forName("com.mysql.jdbc.Driver").newInstance();
             } catch (InstantiationException ex) {
                 Logger.getLogger(DatabaseSupport.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IllegalAccessException ex) {
                 Logger.getLogger(DatabaseSupport.class.getName()).log(Level.SEVERE, null, ex);
             }
             System.out.println("Driver loaded.");
         } catch (ClassNotFoundException e){
             System.out.println("Error loading the driver.");
             System.out.println("DatabaseSupport.uploadRecordObject returning false");
             return false;
         }
         try{
             currentConnection = DriverManager.getConnection(databaseURL, connProperties);
        
             if (this.connectionStatus(currentConnection) == true){
                 System.out.println("currentConnection successful.");
             
                 Statement stmt = null;
                 String c = ", ";
                 String q = "'";
                 String insertRecordObjectsSQL = "INSERT INTO RecordObjects (orderNumber, contactEmail, billingName, shippingName,"
                         +" billingPhone, billingStreet, billingAdd1, billingAdd2, billingCity, billingState, billingZip,"
                         +" billingCountry, createdAt, financialStatusPaid, fufillmentStatus, orderNotes, discountCode,"
                         +" discountAmount, itemName, itemPrice, itemQuantity, itemSKU, orderNoteAttributes)"+"\n"
                         + "VALUES ("+record.orderNumber+c+q+record.contactEmail+q+c+q+record.billingName+q+c+q+record.shippingName+q
                         +c+q+record.billingPhone+q+c+q+record.billingStreet+q
                         +c+q+record.billingAdd1+q+c+q+record.billingAdd2+q+c+q+record.billingCity+q+c+q+record.billingState+q+c+q+record.billingZip+q
                         +c+q+record.billingCountry+q+c+q+record.createdAt+q+c+record.financialStatusPaid+c+record.fufillmentStatus
                         +c+q+record.orderNotes+q+c+q+record.discountCode+q+c+record.discountAmount+c+q+record.itemName+q+c+record.itemPrice
                         +c+record.itemQuantity+c+q+record.itemSKU+q+c+q+record.orderNoteAttributes+q+");";
                 System.out.println(insertRecordObjectsSQL);
                 //temporary
                 //query = "";
                 
                 stmt = currentConnection.createStatement();
                 System.out.println("Next line: 'ResultSet rs = stmt.executeQuery(query);'");
//                 ResultSet rs = stmt.executeQuery(query);
                 stmt.executeUpdate(insertRecordObjectsSQL);
                 System.out.println("Query executed. Check DB for upload status.");
                 
             }else{
                 System.out.println("currentConnection!=true 128 DBSupport");
                 System.out.println("DatabaseSupport.uploadRecordObject returning false 129");
                 return false;
                 //throw new Error("currentConnection != true");
                 
             }
             
         } catch (SQLException e){
                System.out.println("currentConnection!=true DBSupport 136");
                System.out.println("DatabaseSupport.uploadRecordObject returning false 137");
                return false;
             //e.printStackTrace();
             //throw new Error("There was a problem getting currentConnection.");
             
         } finally {
             try{
                 if(conn!=null){
                     conn.close();
                     System.out.println("Connection closed.");
                     
                 }
//                     conn.close();
//                     System.out.println("Connection closed.");
             
             } catch (SQLException ex){
                 System.out.println(ex.getMessage());
             }
         }
            
        //if the connectionStatus does not == true, then return false to show error in record obj upload.
        System.out.println("DatabaseSupport.uploadRecordObject returning true 158");
        return true;
    }
    
}






//This is the code to connect and login to DB.

//Properties connProperties = new Properties();
//         connProperties.put("user", this.databaseUser);
//         connProperties.put("pass", this.databasePass);
//         
//         Connection currentConnection = null;
//         try{
//             currentConnection = DriverManager.getConnection(databaseURL, connProperties);
//             System.out.println("currentConnection successful.");
//             
//             
//             
//         } catch (SQLException e){
//             throw new Error("There was a problem getting currentConnection.");
//         } finally {
//             try{
//                 if(conn!=null){
//                     conn.close();
//                 }
//             
//             } catch (SQLException ex){
//                 System.out.println(ex.getMessage());
//             }
//         }
//            
//        //if the connectionStatus does not == true, then return false to show error in record obj upload.
//        return false;