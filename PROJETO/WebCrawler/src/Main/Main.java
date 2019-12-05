/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Controller.HomeController;
import Model.WebCrawler;
import Exceptions.WebCrawlerException;
import Views.Home;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author BRKsCosta
 */
public class Main extends Application {


    /**
     * @param args the command line arguments
     * @throws Exceptions.WebCrawlerException
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws WebCrawlerException, IOException {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        String url = "http://moodle.ips.pt/";
        
        WebCrawler model = new WebCrawler(url, 2, WebCrawler.StopCriteria.PAGES);
        Home view = new Home(model);
        HomeController controller = new HomeController(model, view);
               
        BorderPane window = new BorderPane();
        window.setCenter(view);
        Scene mainScene = new Scene(window, 1024, 500);
        primaryStage.setTitle("Web Crawler");
        //primaryStage.setResizable(true);
        primaryStage.setScene(mainScene);
        primaryStage.show();
     
    }
    
    
}
