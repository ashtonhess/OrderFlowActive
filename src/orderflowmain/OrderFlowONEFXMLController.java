/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderflowmain;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author ashtonhess
 */
public class OrderFlowONEFXMLController implements Initializable {
        //This class used to extend ScreenController... I dont believe this is necessary... further testing required though.

    
    @FXML
    private Button importButton;
    @FXML
    private Button searchButton;
    
    @FXML
    private void handleImportButtonAction(ActionEvent event) {
        System.out.println("Activating Screen2: Import\n");
        ScreenController.activate("OrderFlowScreen2FXML");
    }
    @FXML
    private void handleSearchButtonAction(ActionEvent event){
        System.out.println("Activating Screen3: Search\n");
        ScreenController.activate("OrderFlowScreen3FXML");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    
}
