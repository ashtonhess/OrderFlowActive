/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderflowmain;

import java.util.HashMap;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/**
 *
 * @author ashtonhess
 */
public abstract class ScreenController {
    
    public static HashMap<String, Pane> screenMap = new HashMap<>();
    public static Scene main;
    
    //public ScreenController(Scene main){
    //    this.main = main;
        
    //}
    
    public static void addScreen(String name, Pane pane){
        screenMap.put(name, pane);
    }
    public static void removeScreen(String name){
        screenMap.remove(name);
        
    }
    public static void activate(String name){
        main.setRoot(screenMap.get(name));
    }
    
}
