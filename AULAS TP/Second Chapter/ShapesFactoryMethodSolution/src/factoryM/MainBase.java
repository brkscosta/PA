/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factoryM;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Utilizador
 */
public class MainBase extends Application {

     private final static int DIM = 600;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
       
        VBox root = new PicturePane(DIM);
        Scene scene = new Scene(root, DIM, DIM);
        primaryStage.setTitle("FactotyMethod");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

   
}
