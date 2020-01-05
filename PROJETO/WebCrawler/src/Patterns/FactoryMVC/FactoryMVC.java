/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patterns.FactoryMVC;

import Controller.HomeController;
import Model.WebCrawler;
import Patterns.Memento.CareTaker;
import Views.HomeView;
import com.brunomnsilva.smartgraph.graphview.SmartStylableNode;
import java.io.IOException;
import javafx.scene.Scene;

/**
 *
 * @author BRKsCosta and danielcordeiro
 */
public class FactoryMVC {
    
    public static HomeView view;
    public static HomeController controller;
    public static Scene createMVCApp() throws IOException {
        
        WebCrawler model = new WebCrawler();
        view = new HomeView(model);
        CareTaker careTaker = new CareTaker(model); 
        controller = new HomeController(model, view, careTaker);
        return view.getScene();
    }
}
