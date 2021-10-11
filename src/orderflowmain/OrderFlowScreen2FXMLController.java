/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderflowmain;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * FXML Controller class
 *
 * @author ashtonhess
 */


public class OrderFlowScreen2FXMLController implements Initializable, PropertyChangeListener {
    //This class used to extend ScreenController... I dont believe this is necessary... further testing required though.
    
    
    public File importFile;
    
    @FXML
    private Text importFileNameText;
    @FXML
    private ProgressBar importProgressBar;
    @FXML
    private Text importProgressText;
    
    public OrderFlowScreen2Model importScreenModel;
//    @FXML
//    private ComboBox<?> comboBoxOne;
//    @FXML
//    private ComboBox<?> comboBoxTwo;
//    @FXML
//    private Button screen3HomeButton;
    @FXML
    private AnchorPane pane;
    @FXML
    private Button importSelectFileButton;
    @FXML
    private Button screen2HomeButton;
    @FXML
    private Button importImportButton;
    
    
    @FXML
    private void handleImportSelectFileButton(ActionEvent event){
        
        importFile = importScreenModel.getFile();
        
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Open CSV file to import.");
//        fileChooser.getExtensionFilters().addAll(
//                new ExtensionFilter("Comma Seperated Values", "*.csv")
//        );
//        
//        File selectedFile = fileChooser.showOpenDialog(null);
//        
//        if(selectedFile != null){
//            System.out.println("File was chosen.\n");
//            String filename = selectedFile.getName();
//            
//            //this String filename is returning NULL right now.
//           // String filename = fileChooser.getInitialFileName();
//            
//            System.out.println(filename+" has been imported.\n");
//            importFileNameText.setText(filename);
//        }
    }
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        importScreenModel = new OrderFlowScreen2Model();
        importScreenModel.addPropertyChangeListener(this);
    }    

    @FXML
    private void handleImportButton(ActionEvent event) throws FileNotFoundException {
        importScreenModel.scanFileToObjects(importFile);
        
    }
   
    @FXML
    private void handleHomeButton(ActionEvent event) {
        System.out.println("Activating Screen1: Home\n");
        System.out.println("Resetting Screen2.");
        importScreenModel.OrderFlowScreen2ModelReset();
        ScreenController.activate("OrderFlowONEFXML");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        
        if (evt.getPropertyName().equals("importFileNameText")) {
            //System.out.println("Some shit");
            importFileNameText.setText((String)evt.getNewValue());
        }
        if (evt.getPropertyName().equals("screen2ProgressBar")){
            importProgressBar.setProgress((Double)evt.getNewValue());
        }
        if (evt.getPropertyName().equals("screen2ProgressPercentage")){
            //this probably needs to be formatted right now
            //NEEDS TESTING!
            importProgressText.setText(Double.toString((Double)evt.getNewValue())+"%");
        }
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @FXML
//    private void handleScreen3HomeButton(ActionEvent event) {
//    }

    

    
    
}
