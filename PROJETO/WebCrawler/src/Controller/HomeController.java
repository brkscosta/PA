/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Patterns.Memento.CareTaker;
import Model.WebCrawlerException;
import Model.WebCrawler;
import Model.WebPage;
import Patterns.Stategy.SearchDepth;
import Patterns.Stategy.SearchPages;
import Views.*;
import com.brunomnsilva.smartgraph.graph.Vertex;
import com.brunomnsilva.smartgraph.graphview.SmartGraphVertex;
import java.io.IOException;

/**
 *
 * @author BRKsCosta
 */
public class HomeController {

    public final HomeView view;
    private final WebCrawler model;
    private final CareTaker caretaker;

    public HomeController(WebCrawler model, HomeView view, CareTaker caretaker) throws IOException {
        this.view = view;
        this.model = model;
        //Create new state of model
        this.caretaker = caretaker;
        
        view.setTriggersButtons(HomeController.this);
        model.addObserver(view); // Subscribe the model
    }

    public void startSearch(String criteria, int numPages)
            throws WebCrawlerException, IOException {
        switch (criteria) {
            case "BFS":
                model.setStartURL(view.getInputURL());
                model.setRootWebPage(model.createWebPage());
                model.setNumPages(numPages);
                model.chosseSearchType(new SearchPages(model));
                if(model.getNumPages() > 0)
                    view.setColorRootPage(model.getRootWebPage());
                view.updateGraph();
                break;
            case "DFS":
                model.setStartURL(view.getInputURL());
                model.setRootWebPage(model.createWebPage());
                model.setNumPages(numPages);
                model.chosseSearchType(new SearchDepth(model));
                //view.setColorRootPage(model.getRootWebPage());
                break;
            default:
                model.setStartURL(view.getInputURL());
                model.setRootWebPage(model.createWebPage());
                model.itertive(model.getRootWebPage().element());
                //view.setColorRootPage(model.getRootWebPage());
                caretaker.requestSave();
                break;
        }
    }

    public void exitApp() {
        this.view.exitApp();
    }

    public Vertex<WebPage> getRootPage() {
        return model.getRootWebPage();
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
        // Checks if we have saved webcrawler's
        if (!caretaker.canUndo()) {
            view.showError("No more undos are available.");
        }
        caretaker.requestRestore();
        view.updateGraph();
    }

    public void removePage(SmartGraphVertex<WebPage> graphVertex) {
        this.model.removePage(graphVertex.getUnderlyingVertex());
        caretaker.requestSave();
        view.updateGraph();
    }

    @Override
    public String toString() {
        return "Home Controller: \n CareTaker: " + this.caretaker;
    }

}
