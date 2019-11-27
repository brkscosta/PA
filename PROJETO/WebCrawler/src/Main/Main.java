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
import javafx.stage.Stage;

/**
 *
 * @author BRKsCosta
 */
public class Main extends Application {
    private static int DIM = 600;
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
        
        Scene mainScene = createAppScene();
        
        primaryStage.setTitle("Web Crawler");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    private Scene createAppScene() throws IOException {
        String stackOverflow = "https://stackoverflow.com/";
        String google = "https://www.google.com/";
        String youTube = "https://www.youtube.com/watch?v=yF3JWJksP9I";
        //crawler.start();
        
        WebCrawler model = new WebCrawler(google, 4, WebCrawler.StopCriteria.PAGES);
        Home view = new Home(model);
        HomeController controller = new HomeController(model, view);
        System.out.println(view.toString());
        
        model.addObserver(view);
        
        Scene scene = new Scene(view, 900, 500);
        
        return scene;
        
    }
    
}
