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
 * @author BRKsCosta and danielcordeiro
 */
public class HomeController {

    private final HomeView view;
    private final WebCrawler model;
    private final CareTaker caretaker;

    public HomeController(WebCrawler model, HomeView view, CareTaker caretaker) {
        this.view = view;
        this.model = model;
        this.caretaker = caretaker;

        this.view.setTriggersButtons(this);
        this.model.addObserver(this.view); // Subscribe the model
    }

    // Getters 
    public HomeView getView() {
        return view;
    }

    public WebCrawler getModel() {
        return model;
    }

    public CareTaker getCaretaker() {
        return caretaker;
    }
    
    // Other methods
    public void startSearch(HomeView.StopCriteria criteria, int numCriteria)
            throws WebCrawlerException, IOException {

        // Init the WebCrawler
        this.model.buildWebCrawler(criteria, numCriteria, view.getInputURL());

        // Update the view
        //view.updateGraph(); // Why update here? If when model WebCrawler is updated it send a message to the Observer HomeView
        //this.view.update(model, this);

        // Set color to the root
        /*if (numCriteria > 0) {
            this.view.setColorRootPage(this.model.getRootWebPage());
        }*/

        // Save a new Memento
        //this.caretaker.requestSave();
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

    /* NOT BEING USED
    public void removePage(SmartGraphVertex<WebPage> graphVertex) {
        this.model.removePage(graphVertex.getUnderlyingVertex());
        caretaker.requestSave();
        //view.updateGraph();
    }*/

    // This method will 
    public void insertNewSubRoot(SmartGraphVertex<WebPage> subRoot) throws IOException {
        model.insertNewSubWebPageCrawler(subRoot.getUnderlyingVertex());
    }

    @Override
    public String toString() {
        return "Home Controller: \n CareTaker: " + this.caretaker;
    }

}
