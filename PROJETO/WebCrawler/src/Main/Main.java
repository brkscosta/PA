/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Patterns.FactoryMVC.FactoryMVC;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    public void start(Stage ignored) throws Exception {
        
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.sizeToScene();
        stage.setTitle("Web Crawler");
        stage.setResizable(false);
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("/Resources/images/icon.png")));
        stage.setScene(FactoryMVC.createMVCApp());
        stage.show();
        FactoryMVC.view.graphView.init();
    }
}
