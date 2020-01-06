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
import Patterns.DAO.IWebCrawlerDAO;
import static Patterns.Factories.Factories.*;
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
 * This class is responsible to manage the model class and the view. All logic
 * of the application is here.
 *
 * @author BRKsCosta and danielcordeiro
 */
public class HomeController {

    private final HomeView view;
    private final WebCrawler model;
    private final CareTaker caretaker;

    @SuppressWarnings("LeakingThisInConstructor")
    public HomeController(WebCrawler model, HomeView view, CareTaker caretaker) {
        this.view = view;
        this.model = model;
        this.caretaker = caretaker;

        this.view.setTriggersButtons(this);
        this.model.addObserver(this.view); // Subscribe the model
    }

    // Getters 
    /**
     * Return the class view instance
     *
     * @return HomeView object
     */
    public HomeView getView() {
        return view;
    }

    /**
     * Return the model instance
     *
     * @return WebCrawler object
     */
    public WebCrawler getModel() {
        return model;
    }

    /**
     * Return the CareTakes class to manage undo.
     *
     * @return CareTaker Object
     */
    public CareTaker getCaretaker() {
        return caretaker;
    }

    /**
     * Start the search of the WebPage
     *
     * @param criteria The type of stop criteria
     * @param numCriteria Number of max pages to generate
     * @throws WebCrawlerException
     * @throws IOException
     */
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

    /**
     * Exit the application
     */
    public void exitApp() {
        this.view.exitApp();
    }

    /**
     * Get the root webpage
     *
     * @return Vertex element
     */
    public Vertex<WebPage> getRootPage() {
        return model.getRootWebPage();
    }

    /**
     * Clear errors
     */
    public void clearErrors() {
        this.view.clearError();
    }

    /**
     * Import files
     */
    public void importFiles(String fileType) {
        
        IWebCrawlerDAO dao = createFileType(fileType, model);
        dao.loadFile();
    }

    /**
     * Exports the chosen file type.
     *
     * @param fileType Type of the file
     */
    public void exportFile(String fileType) {

        // Choose the file to create
        IWebCrawlerDAO dao = createFileType(fileType, model);
        dao.saveAll();

        this.model.exportFile(fileType);
    }

    /**
     * Undo the action (Used in interactive mode)
     */
    public void undoAction() {
        // Checks if we have saved webcrawler's
        if (!caretaker.canUndo()) {
            view.showError("Sem undos disponíveis.");
        }
        caretaker.requestRestore();
    }

    /**
     * Open the WebPage when clicked
     *
     * @param url The URL of the WebPage
     */
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

    /**
     * Deletes all element stored on the graph
     */
    public void clearGraph() {
        model.clearGraph();
    }

    /* NOT BEING USED
    public void removePage(SmartGraphVertex<WebPage> graphVertex) {
        this.model.removePage(graphVertex.getUnderlyingVertex());
        caretaker.requestSave();
        //view.updateGraph();
    }*/
    /**
     * Insert new page when click in the parent page
     *
     * @param subRoot The current page clicked
     * @throws IOException
     */
    public void insertNewSubRoot(SmartGraphVertex<WebPage> subRoot) throws IOException {
        model.insertNewSubWebPageCrawler(subRoot.getUnderlyingVertex());
    }

    @Override
    public String toString() {
        return "Home Controller: \n CareTaker: " + this.caretaker;
    }

}
