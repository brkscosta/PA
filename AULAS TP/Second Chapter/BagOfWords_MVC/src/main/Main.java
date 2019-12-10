/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Controller.BagOfWordsController;
import Model.BagOfWords;
import Model.Caretaker;
import View.BagOfWordsUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author BRKsCosta
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
                
        /* model */
        BagOfWords bag = new BagOfWords("Initial name");
        bag.add("PA");
        bag.add("ATAD");
        
        Caretaker caretaker = new Caretaker(bag);
        
        /* view */
        BagOfWordsUI view = new BagOfWordsUI(bag);
        
        /* controller */
        BagOfWordsController controller = new BagOfWordsController(bag, caretaker, view);
        
        Scene scene = new Scene(view, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
