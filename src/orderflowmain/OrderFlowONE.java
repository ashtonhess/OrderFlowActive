/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderflowmain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ashtonhess
 *
 *
 *
 */
public class OrderFlowONE extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("OrderFlowONEFXML.fxml"));
        
        Scene scene = new Scene(root);
        
        //ScreenController screenController = new ScreenController(scene);
       // screenController.addScreen("OrderFlowONEFXML", FXMLLoader.load(getClass().getResource("OrderFlowONEFXML.fxml")));
       // screenController.activate("OrderFlowONEFXML");
        
        //screenController.addScreen("OrderFlowScreen2FXML", FXMLLoader.load(getClass().getResource("OrderFlowScreen2FXML.fxml")));
        
        ScreenController.main = scene;
        
        ScreenController.addScreen("OrderFlowONEFXML", FXMLLoader.load(getClass().getResource("OrderFlowONEFXML.fxml")));
        ScreenController.addScreen("OrderFlowScreen2FXML", FXMLLoader.load(getClass().getResource("OrderFlowScreen2FXML.fxml")));
        ScreenController.addScreen("OrderFlowScreen3FXML", FXMLLoader.load(getClass().getResource("OrderFlowScreen3FXML.fxml")));
        
        
        ScreenController.activate("OrderFlowONEFXML");
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
