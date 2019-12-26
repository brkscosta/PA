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
import com.brunomnsilva.smartgraph.graphview.SmartGraphVertex;
import java.io.IOException;

/**
 *
 * @author BRKsCosta
 */
public class HomeController {

    public final Home view;
    private final WebCrawler model;
    private final CareTaker caretaker;

    public HomeController(WebCrawler model, Home view, CareTaker caretaker) throws IOException {
        this.view = view;
        this.model = model;
        //Create new state of model
        this.caretaker = caretaker;

        view.setTriggersButtons(HomeController.this);
        model.addObserver(view);
    }

    // Methods here
    public void startSearch(String criteria, int numPages)
            throws WebCrawlerException, IOException {
        setRootWebPage();
        switch (criteria) {
            case "BFS":
                model.setStartURL(view.getInputURL());
                model.start(WebCrawler.StopCriteria.PAGES, numPages);
                caretaker.requestSave(view.getInputURL());
                WebPage v = model.getRootWebPage();
                System.out.println("Controller set root " + v);
                if (model.countWebPages() > 0) {
                    view.setColor(v);
                }
                break;
            case "DFS":
                model.setStartURL(view.getInputURL());
                model.start(WebCrawler.StopCriteria.DEPTH, 0);
                caretaker.requestSave(view.getInputURL());
                break;
            default:
                model.setStartURL(view.getInputURL());
                model.start(WebCrawler.StopCriteria.ITERATIVE, 0);
                caretaker.requestSave(view.getInputURL());
                break;
        }
    }

    private void setRootWebPage() throws IOException {
        String inputURL = this.view.getInputURL();

        if (inputURL.trim().length() == 0) {
            view.showError("Não pode ter um URL vazio!");
        }
        model.setStartURL(view.getInputURL());
        WebPage rootWebPage = model.createWebPage();
        rootWebPage.setPersonalURL(inputURL);
        model.setRootWebPage(rootWebPage);

    }

    public void exitApp() {
        this.view.exitApp();
    }

    public WebPage getRootPage() {
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
        // TODO
        view.undoGraph();
    }

    public void removePage(SmartGraphVertex<WebPage> graphVertex) {
        this.model.graph.removeVertex(graphVertex.getUnderlyingVertex());
        caretaker.requestSave(view.getInputURL());
    }

    public void doUndo() {
        if (!caretaker.canUndo()) {
            view.showError("No more undos Availables!");
            return;
        }
        caretaker.requestRestore();
    }

    @Override
    public String toString() {
        return "Home Controller: \n CareTaker: " + this.caretaker;
    }

}
