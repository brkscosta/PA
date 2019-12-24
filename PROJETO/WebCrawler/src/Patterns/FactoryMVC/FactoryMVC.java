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

    public static void showApp() throws IOException {

        WebCrawler model = new WebCrawler();
        BorderPane window = new BorderPane();

        Home view = new Home(model);
        HomeController controller = new HomeController(model, view);
        
        window.setCenter(view);
        
        Scene mainScene = new Scene(window, 1500, 700);
        
        Stage stage = new Stage(StageStyle.DECORATED);
        
        stage.sizeToScene();
        stage.setTitle("Web Crawler");
        stage.setResizable(false);
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("/Resources/images/icon.png")));
        stage.setScene(mainScene);
        stage.show();

        view.graphView.init();

    }
}
