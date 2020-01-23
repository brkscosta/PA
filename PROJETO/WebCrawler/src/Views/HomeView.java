/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controller.HomeController;
import Model.Link;
import Model.WebCrawler;
import Model.WebCrawlerException;
import Model.WebPage;
import static Patterns.Factories.Factories.getAppStage;
import Patterns.Singleton.LoggerException;
import com.brunomnsilva.smartgraph.graphview.*;
import java.io.IOException;
import java.util.Observable;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import Patterns.Singleton.LoggerWriter;
import com.brunomnsilva.smartgraph.graph.Vertex;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.StageStyle;

/**
 * This class contains all implementation about the UI. Implements a behavioral
 * of the view and a observer to keep updated when model is changed.
 *
 * @see Model.WebCrawler
 * @see Views.IHomeOperations
 *
 * @author BRKsCosta and danielcordeiro
 */
public final class HomeView extends HomeComponents implements Observer, IHomeOperations {

    /**
     * This ENUM represents the stop criteria type
     */
    public enum StopCriteria {
        PAGES, DEPTH, ITERATIVE, EXPANDED;
    }

    //<editor-fold defaultstate="collapsed" desc="Variables">
    LoggerWriter logW = LoggerWriter.getInstance();
    private Scene scene;

    // Graph interface
    private SmartGraphEdge<Link, WebPage> edgeClicked = null;
    private SmartGraphVertex<WebPage> vertexClicked;

    private BorderPane window;
    private boolean inIterativeMode = false;

    private SmartPlacementStrategy strategy;
    private SmartGraphPanel<WebPage, Link> graphView;
//</editor-fold>

    public HomeView(WebCrawler model) {

        this.strategy = new SmartCircularSortedPlacementStrategy();
        this.graphView = new SmartGraphPanel<>(model.getGraph(), strategy);

        window = new BorderPane(HomeView.this);
        window.setCenter(HomeView.this);

        this.scene = new Scene(window, 1500, 700);

        super.initializeComponents(graphView);

        update(model, null);

    }

    //<editor-fold defaultstate="collapsed" desc="Getters">
    /**
     * this method will return a SmartGraphPanel<WebPage, Link> instance
     *
     * @return a SmartGraphPanel<WebPage, Link> object
     */
    public SmartGraphPanel<WebPage, Link> getGraphView() {
        return graphView;
    }

    //<editor-fold defaultstate="collapsed" desc="Chart Getters">
    @Override
    public XYChart.Series chartGetVisited() {
        return super.chartGetVisited();
    }

    @Override
    public XYChart.Series chartGetNotFound() {
        return super.chartGetNotFound();
    }

    @Override
    public XYChart.Series chartGetHttpsProtocol() {
        return super.chartGetHttpsProtocol();
    }

    @Override
    public XYChart.Series chartGetLinks() {
        return super.chartGetLinks();
    }
//</editor-fold>
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Class Behaviors">
    @Override
    public String getInputURL() {
        return this.txtFieldURL.getText();
    }

    @Override
    public void importFile(HomeController controller) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir Ficheiro");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Data File", "*.data"),
                new ExtensionFilter("Json File", "*.json"));
        File selectedFile = fileChooser.showOpenDialog(getAppStage());
        if (selectedFile != null) {
            String fileName = selectedFile.getName();
            String fileExtension = fileName.substring(fileName.lastIndexOf(".")
                    + 1, selectedFile.getName().length());
            controller.importFiles(fileExtension.toUpperCase());

        }
    }

    @Override
    public void exportFile(HomeController controller) {
        chooseFileType(controller);
    }

    @Override
    public void exitApp() {
        Platform.exit();
        System.out.println("Views.Home.exitApp()");
        System.exit(0);
    }

    @Override
    public void clearError() {
        this.lblInfo.setText("");
    }

    @Override
    public void showError(String errorMsg) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Alerta");
        alert.setHeaderText("Oooops, vamos com calma..");
        alert.setContentText(errorMsg);
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
    }

    @Override
    public void setTriggersButtons(HomeController controller) {

        // Quit app
        this.mFileItemExit.setOnAction((ActionEvent event) -> {
            controller.exitApp();
        });

        // Apply DAO - TODO
        this.mFileItemExportFile.setOnAction((ActionEvent event) -> {
            exportFile(controller);
        });

        this.mFileItemImportFile.setOnAction((ActionEvent event) -> {
            importFile(controller);
        });

        // Apply Memento - Undo
        this.mEditUndo.setOnAction((ActionEvent event) -> {
            controller.undoAction();
        });

        this.mEditRedo.setOnAction((ActionEvent event) -> {
            redoGraph();
        });

        this.mEditClearGraph.setOnAction(((event) -> {
            controller.clearGraph();
            System.out.println("Clear graph");
        }));

        // Show the graph
        this.btnStartCrawler.setOnAction((ActionEvent t) -> {
            selectSearchType(controller);
        });

        // VISIT WEB PAGE
        this.graphView.setVertexDoubleClickAction(graphVertex -> {
            System.out.println("Vertex contains element: " + graphVertex.getUnderlyingVertex().element());

            if (this.inIterativeMode) {
                try {
                    controller.insertNewSubRoot(graphVertex.getUnderlyingVertex());
                    controller.saveCaretacker();
                } catch (IOException ex) {
                    Logger.getLogger(HomeView.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                controller.openWebPage(graphVertex.getUnderlyingVertex().element().getPersonalURL());
            }

            graphVertex.setStyle("-fx-fill: gold; -fx-stroke: brown;");
            graphView.update();
        });

        this.graphView.setEdgeDoubleClickAction(graphEdge -> {
            System.out.println("EDGE -1");
            //dynamically change the style when clicked
            if (this.edgeClicked != null) {
                System.out.println("EDGE 0");
                // Check if the clicked edge is the same as the edge passed as an argument
                if (graphEdge.getUnderlyingEdge() == this.edgeClicked) {
                    // Change the color to the default
                    graphEdge.setStyle("-fx-stroke: #FF6D66; -fx-stroke-width: 2;");
                    this.edgeClicked = null;

                    System.out.println("EDGE 1");
                } else {
                    // Change the color of the old edgeClicked to the default
                    this.edgeClicked.setStyle("-fx-stroke: #FF6D66; -fx-stroke-width: 2;");

                    // Assign to the edgeClicked a new value
                    this.edgeClicked = graphEdge;
                    this.edgeClicked.setStyle("-fx-stroke: black; -fx-stroke-width: 2;");
                    System.out.println("EDGE 2");
                }
            } else {
                // Assign to the edgeClicked a new value
                this.edgeClicked = graphEdge;
                graphEdge.setStyle("-fx-stroke: black; -fx-stroke-width: 2;");
                System.out.println("EDGE 3");
            }

            // Refresh color's
            this.graphView.update();
        });

        // Try to catch the ENTER key event
        EventHandler<KeyEvent> enterKeyEventHandler;

        enterKeyEventHandler = (KeyEvent event) -> {
            // handle users "enter key event"
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    // yes, using exception for control is a bad solution ;-)
                    selectSearchType(controller);
                } catch (NumberFormatException e) {
                    // show message to user: "only numbers allowed"
                    // reset editor to INITAL_VALUE
                    spinner.getEditor().textProperty().set("10");

                }
            }
        };

        // note: use KeyEvent.KEY_PRESSED, because KeyEvent.KEY_TYPED is to late, spinners
        // SpinnerValueFactory reached new value before key released an SpinnerValueFactory will
        // throw an exception
        spinner.getEditor().addEventHandler(KeyEvent.KEY_PRESSED, enterKeyEventHandler);

    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    @Override
    public void update(Observable o, Object o1) {

        WebCrawler obsModel = (WebCrawler) o; // Model

        if (o instanceof WebCrawler) {
            // Não será preciso fazer update se não houver páginas
            if (obsModel.countWebPages() > 0) {
                graphView.update();
            }

            if (obsModel.isFinished == true) {
                graphView.update();
                obsModel.isFinished = false;
            }
        }
    }
    
    /**
     * This method make redo action on graph
     *
     */
    private void redoGraph() {
        // TODO
        System.out.println("Views.Home.redoGraph()");
    }
    
    /**
     * This method execute the search requested
     *
     * @param controller The controller object
     * @throws LoggerException
     * @throws WebCrawlerException
     * @throws NumberFormatException
     */
    private void selectSearchType(HomeController controller) throws
            LoggerException, WebCrawlerException, NumberFormatException {
        try {
            // Get the numPages
            int parseInt = Integer.parseInt(spinner.getEditor().textProperty().get());
            
            if (rdBtnBreadthFirst.isSelected()) {
                this.inIterativeMode = false;
                lblInfo.setText("Selecionou BFS");
                controller.startSearch(HomeView.StopCriteria.PAGES, parseInt);
            } else if (rdBtnDepth.isSelected()) {
                this.inIterativeMode = false;
                lblInfo.setText("Selecionou DFS");
                controller.startSearch(HomeView.StopCriteria.DEPTH, parseInt);
            } else if (rdBtnExpandedPages.isSelected()) {
                lblInfo.setText("Selecionou Expansão de Páginas");
                this.inIterativeMode = false;
                controller.startSearch(HomeView.StopCriteria.EXPANDED, parseInt);
            } else {
                lblInfo.setText("Selecionou Iterativo");
                this.inIterativeMode = true;
                controller.startSearch(HomeView.StopCriteria.ITERATIVE, parseInt);
            }
        } catch (IOException ex) {
            LoggerWriter.getInstance().writeToLog("Classe View btnStarcrawler: " + ex.getStackTrace()[0]);
        }
    }
    
    /**
     * Set the root webpage
     *
     * @param p The concrete vertex
     */
    public void setColorRootPage(Vertex<WebPage> p) {
        System.out.println("root? " + p);
        System.out.println("graphview ? " + graphView.getStylableVertex(p));
        graphView.getStylableVertex(p).setStyle("-fx-fill: gold; -fx-stroke: brown;");
    }
    
    /**
     * Update the graph view
     */
    public void updateGraph() {
        graphView.update();
    }
    
    /**
     * Show errors along the application
     *
     * @param msg The error message
     */
    public void showErrorStackTraceException(String msg) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Exception");
        alert.setHeaderText("Hmmm, algo não está bom");
        alert.initStyle(StageStyle.UTILITY);
        Exception ex = new WebCrawlerException(msg);
        
        // Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();
        
        Label label = new Label("The exception stacktrace was:");
        
        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);
        
        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);
        
        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);
        
        alert.showAndWait();
        exitApp();
    }
    
    /**
     * Open the dialog to open the file
     *
     * @param controller The controller object
     */
    private void chooseFileType(HomeController controller) {
        List<String> choices = new ArrayList<>();
        choices.add("DATA");
        choices.add("JSON");
        
        ChoiceDialog<String> dialog = new ChoiceDialog<>("DATA", choices);
        dialog.initStyle(StageStyle.UTILITY);
        dialog.setTitle("Exportar");
        dialog.setHeaderText("Por favor, selecione um formato.");
        dialog.setContentText("Formato:");
        
        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            controller.exportFile(result.get());
        }
    }
//</editor-fold>

    @Override
    public String toString() {
        return "View: " + HomeView.class;
    }
}
