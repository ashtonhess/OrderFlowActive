/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderflowmain;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 *
 * @author ashtonhess
 */
public class OrderFlowScreen3FXMLController implements Initializable, PropertyChangeListener {

    public OrderFlowScreen3Model searchScreenModel;
    
    @FXML
    private ComboBox<String> comboBoxOne;
    @FXML
    private ComboBox<String> comboBoxTwo;
    @FXML
    private Button screen3HomeButton;
    
    
    
    public String comboBoxOneSelection;
    public String comboBoxTwoSelection;
    @FXML
    private Button executeQueryButton;
    @FXML
    private TextField textFieldOne;
    @FXML
    private TextField textFieldTwo;
    
    

//    public String comboBoxOptions[] = {"Option1", "Option2", "Option3", "Option4"};
    
    @FXML
    private void handleScreen3HomeButton(ActionEvent event) {
        System.out.println("Activating Screen1: Home\n");
        //System.out.println("Resetting Screen3.");
        //importScreenModel.OrderFlowScreen2ModelReset();
        ScreenController.activate("OrderFlowONEFXML");
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        searchScreenModel = new OrderFlowScreen3Model();
        searchScreenModel.addPropertyChangeListener(this);
//        comboBoxOne.getItems().add(comboBoxOptions);
        setUpComboBoxes();
        
    }    
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        
        
    }

    @FXML
    private void handleExecuteQueryButton(ActionEvent event) {
    }
    
    public void setUpComboBoxes(){
        ObservableList<String> optionsList = comboBoxOne.getItems();
        optionsList.add("Order Number");
        optionsList.add("Email");
        optionsList.add("Name");
        optionsList.add("Phone Number");
        optionsList.add("MOCode");
        ObservableList<String> optionsList2 = comboBoxTwo.getItems();
        optionsList2.add("Order Number");
        optionsList2.add("Email");
        optionsList2.add("Name");
        optionsList2.add("Phone Number");
        optionsList2.add("MOCode");
        
        EventHandler<ActionEvent> comboBoxOneEvent = 
                new EventHandler<ActionEvent>(){
                    public void handle(ActionEvent e){
                        comboBoxOneSelection = comboBoxOne.getValue();
                        System.out.println("The value of comboBoxOne is: "+comboBoxOneSelection);
                    }
                };
        comboBoxOne.setOnAction(comboBoxOneEvent);
        
        EventHandler<ActionEvent> comboBoxTwoEvent = 
                new EventHandler<ActionEvent>(){
                    public void handle(ActionEvent e){
                        comboBoxTwoSelection = comboBoxTwo.getValue();
                        System.out.println("The value of comboBoxTwo is: "+comboBoxTwoSelection);
                    }
                };
        comboBoxTwo.setOnAction(comboBoxTwoEvent);
    }
    
}
