/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderflowmain;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;

/**
 *
 * @author ashtonhess
 */
public class OrderFlowScreen2Model extends AbstractPropertyChangeSupport {
    
    public OrderRecordClass recordArray[];
    DatabaseSupport screen2ModelDBSupport;
    
    public OrderFlowScreen2Model(){
        propertyChangeSupport = new PropertyChangeSupport(this);
        screen2ModelDBSupport = new DatabaseSupport();
    }
    
    public void OrderFlowScreen2ModelReset(){
        System.out.println("Resetting OrderFlowScreen2Model.");
        
        recordArray = null;
        firePropertyChange("importFileNameText","","Please select a file...");
        firePropertyChange("screen2ProgressBar","",0.0);
        firePropertyChange("screen2ProgressPercentage","",0.0);
        
        
        System.out.println("Resetting OrderFlowScreen2Model finished.");
    }
    
    public File getFile(){
    FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open CSV file to import.");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Comma Seperated Values", "*.csv")
        );
        
        File selectedFile = fileChooser.showOpenDialog(null);
        
        if(selectedFile != null){
            System.out.println("File was chosen.\n");
            String filename = selectedFile.getName();
            
            //this String filename is returning NULL right now.
           // String filename = fileChooser.getInitialFileName();
            
            System.out.println(filename+" has been imported.\n");
          //  importFileNameText.setText(filename);
          firePropertyChange("importFileNameText","",filename);
          return selectedFile;
        }
        return null;
    }
    
    public Boolean scanFileToObjects(File importFile) throws FileNotFoundException{
        
        System.out.println("scanFileToObjects has been activated\n");
            
            //Scanner scans for the number of lines. This number of lines is used to allocate the size of the array of Record objects. 
            Scanner initialFileScanner = new Scanner(importFile);
            initialFileScanner.useDelimiter("\n#");
            int initialLineCounter=0;
            
            while(initialFileScanner.hasNext()){
                initialLineCounter++;
                initialFileScanner.next();
                System.out.println("Initial line counter: "+initialLineCounter);
            }
            initialFileScanner.reset();
            
            recordArray = new OrderRecordClass[initialLineCounter];

            
            int lineCount = 0;
            
            Scanner fileScanner = new Scanner(importFile);
            fileScanner.useDelimiter("\n#");

            
            
            while(fileScanner.hasNext()){
                
                
                
                lineCount++;
                System.out.println(lineCount);
                OrderRecordClass record = new OrderRecordClass();
                String line = fileScanner.next();
               
                Scanner innerFileScanner = new Scanner(line);
                innerFileScanner.useDelimiter(",");
                record.orderNumber = Integer.parseInt(innerFileScanner.next().replace("#", ""));
                record.contactEmail = innerFileScanner.next();
                String financialStatusTemp = innerFileScanner.next();
                if (financialStatusTemp.equals("paid")) {
                    record.financialStatusPaid = true;
                }else{
                    record.financialStatusPaid = false;
                }
                innerFileScanner.next();//increments over "Paid at" col
                String fufillmentStatusTemp = innerFileScanner.next();
                if (fufillmentStatusTemp.equals("fulfilled")) {
                    record.fufillmentStatus = true;
                }else{
                    record.fufillmentStatus = false;
                }
                innerFileScanner.next();//increments over "Fufilled at" col
                innerFileScanner.next();//increments over "Accepts Marketing" col
                innerFileScanner.next();//increments over "Currency" col
                innerFileScanner.next();//increments over "Subtotal" col
                innerFileScanner.next();//increments over "Shipping" col
                innerFileScanner.next();//increments over "Taxes" col
                innerFileScanner.next();//increments over "Total" col
                record.discountCode = innerFileScanner.next();
                record.discountAmount = Double.parseDouble(innerFileScanner.next());
                innerFileScanner.next();//increments over "Shipping Method" col
                record.createdAt = innerFileScanner.next();
                record.itemQuantity = Integer.parseInt(innerFileScanner.next());
                record.itemName = innerFileScanner.next();
                record.itemPrice = Double.parseDouble(innerFileScanner.next());
                innerFileScanner.next();//increments over "Lineitem compare at price" col
                record.itemSKU = innerFileScanner.next();
                innerFileScanner.next();//increments over "Lineitem requires shipping" col
                innerFileScanner.next();//increments over "Lineitem taxable" col
                innerFileScanner.next();//increments over "Lineitem fufillment status" col
                record.billingName = innerFileScanner.next();
                record.billingStreet = innerFileScanner.next();
                record.billingAdd1 = innerFileScanner.next();
                record.billingAdd2 = innerFileScanner.next();
                innerFileScanner.next();//increments over "Billing Company" col
                record.billingCity = innerFileScanner.next();
                record.billingZip = innerFileScanner.next();
                record.billingState = innerFileScanner.next();
                record.billingCountry = innerFileScanner.next();
                record.billingPhone = innerFileScanner.next().replaceAll("\\D", "");
                record.shippingName = innerFileScanner.next();
                innerFileScanner.next();//increments over "Shipping Street" col
                innerFileScanner.next();//increments over "Shipping Address1" col
                innerFileScanner.next();//increments over "Shipping Address2" col
                innerFileScanner.next();//increments over "Shipping Company" col
                innerFileScanner.next();//increments over "Shipping City" col
                innerFileScanner.next();//increments over "Shipping Zip" col
                innerFileScanner.next();//increments over "Shipping Province"
                innerFileScanner.next();//increments over "Shipping Country" col
                innerFileScanner.next();//increments over "Shipping Phone" col
                innerFileScanner.useDelimiter("\"");//This is added to prevent errors from occuring if a "," is used in the orderNotes.
                innerFileScanner.next();
                record.orderNotes = innerFileScanner.next();
                innerFileScanner.next();
                innerFileScanner.useDelimiter(",");
              
                record.orderNoteAttributes = innerFileScanner.next().replace("\"", "");//This .replace is used to take the quotes off of the string.
                
                innerFileScanner.close();
                
                //testing: prints out the values from the record object. checks if object populated correctly.
//                System.out.println(lineCount
//                        +" record: \nOrder Number: "+record.orderNumber
//                        +"\nContact Email: "+record.contactEmail
//                        +"\nFinancial Status Paid:"+record.financialStatusPaid
//                        +"\nFufillment Status: "+record.fufillmentStatus
//                        +"\nDiscount Code: "+record.discountCode
//                        +"\nDiscount Amount: "+record.discountAmount
//                        +"\nCreated At: "+record.createdAt
//                        +"\nItem Quantity: "+record.itemQuantity
//                        +"\nItem Name: "+record.itemName
//                        +"\nItem Price: "+record.itemPrice
//                        +"\nItem SKU:"+record.itemSKU
//                        +"\nBilling Name:"+record.billingName
//                        +"\nBilling Street:"+record.billingStreet
//                        +"\nBilling Address1: "+record.billingAdd1
//                        +"\nBilling Address2: "+record.billingAdd2
//                        +"\nBilling City:"+record.billingCity
//                        +"\nBilling Zip:"+record.billingZip
//                        +"\nBilling State: "+record.billingState
//                        +"\nBilling Country: "+ record.billingCountry
//                        +"\nBilling Phone: "+record.billingPhone
//                        +"\nShipping Name: "+record.shippingName
//                        +"\nOrder Notes: "+record.orderNotes
//                        +"\nOrder Note Attributes: "+record.orderNoteAttributes);
//                System.out.println("\n------------------------------------------\n");
                
                System.out.println("Record #: "+lineCount);
                record.printOrderRecordClass();

                recordArray[lineCount-1] = record; 
                
            }
            
            fileScanner.close();
        
        
        int uploadCounter=0;
        Boolean connectionStatus;
        for (OrderRecordClass record: recordArray) {
            connectionStatus = screen2ModelDBSupport.uploadRecordObject(record);
            if (connectionStatus==true) {
                uploadCounter++;
            Double progressPercentage = 0.0;
            progressPercentage = Double.valueOf(uploadCounter)/Double.valueOf(lineCount);
            System.out.println("Upload counter: "+uploadCounter);
            System.out.println("Current progress: "+progressPercentage*100+"%");
            firePropertyChange("screen2ProgressBar","",progressPercentage);
            firePropertyChange("screen2ProgressPercentage","",progressPercentage*100);
            }else{
                
                //This same error is being thrown when trying to upload duplicate entries.     !!!!!!!!!!!!!!!!!!!!!!!!!!!
                
                System.out.println("There was an error connecting to the database. Objects will not be uploaded. 207 OrderFlowScreen2Model");
                Alert databaseAlert = new Alert(AlertType.ERROR);
                databaseAlert.setContentText("There was an error connecting to the database. Ensure database is active and try again.");
                System.out.println("Database error alert displaying to user. OrderFlowScreen2Model.scanFileToObjects 213");
                databaseAlert.show();
                return false;
            }
            
        }

        System.out.println("uploadRecordObject called.");
        return true;
    }
    
}
