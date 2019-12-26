/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patterns.FactoryMVC;

import Controller.HomeController;
import Main.Main;
import Model.WebCrawler;
import Patterns.Memento.CareTaker;
import Views.Home;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author BRKsCosta
 */
public class FactoryMVC {
    
    public static Home view;
    
    public static Scene createMVCApp() throws IOException {

        WebCrawler model = new WebCrawler();
        view = new Home(model);
        CareTaker careTaker = new CareTaker(model);
        HomeController controller = new HomeController(model, view, careTaker);
        
        return view.getScene();
    }

}
