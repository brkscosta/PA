/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Exceptions.WebCrawlerException;
import Model.WebCrawler;
import Model.WebPage;
import Views.*;
import java.io.IOException;

/**
 *
 * @author BRKsCosta
 */
public class HomeController {
    
    private final Home mainView;
    private final WebCrawler model;

    public HomeController(WebCrawler model, Home mainView) {
        this.mainView = mainView;
        this.model = model;
        
        mainView.setTriggersButtons(this);
        model.addObserver(mainView);
    }
    
    // TODO Methods here
    
    public void start() throws WebCrawlerException, IOException{
        WebPage rootWebPage = model.rootWebPage;
        rootWebPage.setPersonalURL(this.mainView.getInputURL());
        model.start();
    }
    
    public void exitApp(){
        this.mainView.exitApp();
    }

    public void importFiles(){
        //TODO
    }
    
    public void clearErrors(){
        this.mainView.clearError();
    }
    
    public void exportFiles() {
        //TODO
        this.mainView.exportFile();
    }
    
    public void undoAction() {
        // TODO
        mainView.undoGraph();
    }
    
    public void showErrors() {
        
    }
    
    @Override
    public String toString() {
        return "HomeController";
    }
    
}
