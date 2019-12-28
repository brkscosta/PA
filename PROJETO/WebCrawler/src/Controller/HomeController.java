/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.WebCrawlerException;
import Model.WebCrawler;
import Model.WebPage;
import Views.*;
import com.brunomnsilva.smartgraph.graphview.SmartGraphVertex;
import java.io.IOException;

/**
 *
 * @author BRKsCosta
 */
public class HomeController {

    private final Home view;
    private final WebCrawler model;

    public HomeController(WebCrawler model, Home view) {
        this.view = view;
        this.model = model;

        view.setTriggersButtons(HomeController.this);
        model.addObserver(view);
    }
 
    // Methods here
    public void start() throws WebCrawlerException, IOException {
        setRootWebPage();
        model.start();
        
    }

    public void automaticMode() {
        setRootWebPage();
    }
    
    public void iterativeMode(){
        setRootWebPage();
    }
    
    private void setRootWebPage() {
        WebPage rootWebPage = model.rootWebPage;
        String inputURL = this.view.getInputURL();

        if (inputURL.trim().length() == 0)
            view.showError("Não pode ter um URL vazio!");
        
        rootWebPage.setPersonalURL(inputURL);
    }

    public void exitApp() {
        this.view.exitApp();
    }

    public void importFiles() {
        //TODO
    }

    public void clearErrors() {
        this.view.clearError();
    }

    public void exportFiles() {
        //TODO
        this.view.exportFile();
    }

    public void undoAction() {
        // TODO
        view.undoGraph();
    }

    @Override
    public String toString() {
        return "HomeController";
    }

    public void removePage(SmartGraphVertex<WebPage> graphVertex) {
        this.model.graph.removeVertex(graphVertex.getUnderlyingVertex());
    }

    

}
