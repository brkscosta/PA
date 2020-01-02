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
import Views.*;
import com.brunomnsilva.smartgraph.graph.Vertex;
import com.brunomnsilva.smartgraph.graphview.SmartGraphVertex;
import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 *
 * @author BRKsCosta
 */
public class HomeController {

    public final HomeView view; // Make it private -> create a getter method
    private final WebCrawler model;
    private final CareTaker caretaker;

    public HomeController(WebCrawler model, HomeView view, CareTaker caretaker) {
        this.view = view;
        this.model = model;

        //Create new state of model
        this.caretaker = caretaker;

        this.view.setTriggersButtons(this);
        model.addObserver(this.view); // Subscribe the model
    }

    public void startSearch(HomeView.StopCriteria criteria, int numPages)
            throws WebCrawlerException, IOException {

        // Init the WebCrawler
        model.buildWebCrawler(criteria, numPages, view.getInputURL());

        // Update the view
        //view.updateGraph(); // Why update here? If when model WebCrawler is updated it send a message to the Observer HomeView
        view.update(model, this);

        // Set color to the root
        if (model.getNumPages() > 0) {
            view.setColorRootPage(model.getRootWebPage());
        }

        // Save a new Memento
        caretaker.requestSave();
    }

    public void exitApp() {
        this.view.exitApp();
    }

    public Vertex<WebPage> getRootPage() {
        return model.getRootWebPage();
    }

    public void clearErrors() {
        this.view.clearError();
    }

    public void importFiles() {
        this.view.importFile();
    }

    public void exportFile(String fileType) {
        System.out.println("Controller get all links: " + model.getAllLinks());
        System.out.println("Controller file type: " + fileType);
    }

    public void undoAction() {
        // Checks if we have saved webcrawler's
        if (!caretaker.canUndo()) {
            view.showError("Sem undos disponíveis.");
        }
        caretaker.requestRestore();
    }

    public void openWebPage(String url) {

        try {
            Desktop.getDesktop().browse(new URL(url).toURI());
        } catch (MalformedURLException ex) {
            model.getLogger().writeToLog(ex.getMessage());
            view.showErrorStackTraceException(ex.getMessage());
        } catch (URISyntaxException | IOException ex) {
            model.getLogger().writeToLog(ex.getMessage());
            view.showErrorStackTraceException(ex.getMessage());
        }

    }

    public void clearGraph() {
        model.clearGraph();
    }

    public void removePage(SmartGraphVertex<WebPage> graphVertex) {
        this.model.removePage(graphVertex.getUnderlyingVertex());
        caretaker.requestSave();
        //view.updateGraph();
    }

    // This method will 
    public void insertNewSubRoot(SmartGraphVertex<WebPage> subRoot) {
        model.insertNewSubWebPageCrawler(subRoot.getUnderlyingVertex());
    }

    @Override
    public String toString() {
        return "Home Controller: \n CareTaker: " + this.caretaker;
    }

}
