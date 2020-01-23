/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patterns.Factories;

import Controller.HomeController;
import Main.Main;
import Model.WebCrawler;
import Patterns.DAO.IWebCrawlerDAO;
import Patterns.DAO.WebCrawlerFile;
import Patterns.DAO.WebCrawlerJson;
import Patterns.Memento.CareTaker;
import Views.HomeView;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * This class is responsible for creating the MVC structure and the 
 * exported file types.
 *
 * @author BRKsCosta and danielcordeiro
 */
public class Factories {

    //<editor-fold defaultstate="collapsed" desc="Variables">
    private static HomeView view;
    private static HomeController controller;
    private static Stage stage;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters">
    /**
     * This method will return the Stage instance
     * @return a Stage object
     */
    public static Stage getAppStage() {
        return stage;
    }
    
    /**
     * This method will return a HomeView instance
     * @return a HomeView object
     */
    public static HomeView getView() {
        return view;
    }
    
    /**
     * This method will return a HomeController instance
     * @return a HomeController object
     */
    public static HomeController getController() {
        return controller;
    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Factory Methods">
    /**
     * This method shows the app
     */
    public static void showApp() {
        stage = new Stage(StageStyle.DECORATED);
        stage.sizeToScene();
        stage.setTitle("Web Crawler");
        stage.setResizable(false);
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("/Resources/images/icon.png")));
        stage.setScene(createMVCApp());
        stage.show();
        getView().getGraphView().init();
    }
    
    /**
     * This method will return a Scene instance after creating the MVC pattern
     * @return a Scene object
     */
    private static Scene createMVCApp() {
        
        WebCrawler model = new WebCrawler();
        view = new HomeView(model);
        CareTaker careTaker = new CareTaker(model);
        controller = new HomeController(model, view, careTaker);
        
        return view.getScene();
    }
    
    /**
     * This method will create a file of a certain type with a WebCrawler object
     * @param fileType String with the wanted file type
     * @param model WebCrawler model given to the file
     * @return a IWebCrawlerDAO object implementation
     */
    public static IWebCrawlerDAO createFileType(String fileType, WebCrawler model) {
        
        switch (fileType) {
            case "DATA":
                return new WebCrawlerFile(model);
            case "JSON":
                return new WebCrawlerJson(model);
            default:
                getView().showError("Tipo de ficheiro não existente!");
        }
        return null;
    }
//</editor-fold>
}
