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
     */
    public static void main(String[] args) {
        launch(args);
        
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        String url = "http://moodle.ips.pt/";
        String testeWebCrawler = "http://www.brunomnsilva.com/sandbox/index.html";
        
        //Aplicar padrão memento no webCrawler e criar uma classe para fazer a gestão
        WebCrawler model = new WebCrawler(testeWebCrawler, 20, WebCrawler.StopCriteria.PAGES);
        Home view = new Home(model);
        HomeController controller = new HomeController(model, view);
        
        System.out.println(" " + controller);
        
        BorderPane window = new BorderPane();
        window.setCenter(view);
        Scene mainScene = new Scene(window, 1090, 500);
        primaryStage.setTitle("Web Crawler");
        primaryStage.setResizable(true);
        primaryStage.setScene(mainScene);
        primaryStage.show();

    }

}
