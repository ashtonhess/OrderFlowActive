/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderflowmain;

import java.beans.PropertyChangeSupport;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;



/**
 *
 * @author ashtonhess
 */
public class OrderFlowScreen3Model extends AbstractPropertyChangeSupport {
    
    public OrderRecordClass recordArray[];
    DatabaseSupport screen3ModelDBSupport;
    
    
    
    
    public OrderFlowScreen3Model(){
        propertyChangeSupport = new PropertyChangeSupport(this);
        screen3ModelDBSupport = new DatabaseSupport();
        //screen2ModelDBSupport = new DatabaseSupport();
    }
    
    
    public void orderFlowScreen3ModelReset(){
        System.out.println("Resetting OrderFlowScreen3Model.");
        
        
    }
    
    
}
