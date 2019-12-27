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
import Patterns.Singleton.LoggerException;
import com.brunomnsilva.smartgraph.containers.SmartGraphDemoContainer;
import com.brunomnsilva.smartgraph.graphview.*;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import Patterns.Singleton.LoggerWriter;
import com.brunomnsilva.smartgraph.graph.DigraphEdgeList;
import com.brunomnsilva.smartgraph.graph.Graph;
import com.brunomnsilva.smartgraph.graph.Vertex;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author BRKsCosta
 */
public class Home extends VBox implements Observer, IHomeOperations {

    LoggerWriter logW = LoggerWriter.getInstance();
    //SmartPlacementStrategy strategy = new SmartCircularSortedPlacementStrategy();
    SmartPlacementStrategy strategy = new SmartRandomPlacementStrategy();
    public SmartGraphPanel<WebPage, Link> graphView;
    //public SmartGraphPanel<String, String> graphView;

    //Menu  
    private MenuBar menuBar;
    private Menu menuFile;
    private Menu menuEdit;
    private Menu menuHelp;
    private MenuItem mFileItemExportFile;
    private MenuItem mFileItemImportFile;
    private MenuItem mFileItemExit;
    private MenuItem mEditUndo;
    private MenuItem mEditClearGraph;
    private MenuItem mEditRedo;
    private MenuItem mHelpAbout;
    private SeparatorMenuItem separatorMenu;
    private SeparatorMenuItem separatorEdit;

    //Actions left panel
    private Button btnStartCrawler;
    private TextField txtFieldURL;
    private TextField txtFieldNumPages;
    private RadioButton rdBtnBreadthFirst;
    private RadioButton rdBtnDepth;
    private RadioButton rdBtnIterative;
    private final Spinner spinner = new Spinner();

    //Layout
    private SplitPane splitPane;
    private AnchorPane anchorPaneLeft;
    private AnchorPane anchorPaneRigth;
    private HBox bottomHBox;

    //Labels
    private Label lblStatistics;
    private Label labelErros;
    private Label lblCriteria;
    private Label lblAnotherThing;
    private Label lblWebCrawler;
    private Label lblNumPages;

    private static final String INITAL_VALUE = "10";
    private Scene scene;
    //Graph<String, String> g = build_flower_graph();

    public Home(WebCrawler model) {

        this.strategy = new SmartCircularSortedPlacementStrategy();
        //this.graphView = new SmartGraphPanel(g, strategy);
        this.graphView = new SmartGraphPanel(model.graph, strategy);
        BorderPane window = new BorderPane(Home.this);
        window.setCenter(Home.this);
        this.scene = new Scene(window, 1500, 700);
        this.initializeComponents();

        update(model, null);

    }

    private Graph<String, String> build_flower_graph() {

        Graph<String, String> g = new DigraphEdgeList<>();

        g.insertVertex("A");
        g.insertVertex("B");
        g.insertVertex("C");
        g.insertVertex("D");
        g.insertVertex("E");
        g.insertVertex("F");
        g.insertVertex("G");

        g.insertEdge("A", "B", "1");
        g.insertEdge("A", "C", "2");
        g.insertEdge("A", "D", "3");
        g.insertEdge("A", "E", "4");
        g.insertEdge("A", "F", "5");
        g.insertEdge("A", "G", "6");

        g.insertVertex("H");
        g.insertVertex("I");
        g.insertVertex("J");
        g.insertVertex("K");
        g.insertVertex("L");
        g.insertVertex("M");
        g.insertVertex("N");

        g.insertEdge("H", "I", "7");
        g.insertEdge("H", "J", "8");
        g.insertEdge("H", "K", "9");
        g.insertEdge("H", "L", "10");
        g.insertEdge("H", "M", "11");
        g.insertEdge("H", "N", "12");

        g.insertEdge("A", "H", "0");

        //g.insertVertex("ISOLATED");
        return g;
    }

    private void initializeComponents() {

        //Set up menu bar
        this.menuFile = new Menu("File");
        this.mFileItemImportFile = new MenuItem("Import File");
        this.mFileItemExportFile = new MenuItem("Export File");
        this.mFileItemExit = new MenuItem("Exit");
        this.separatorMenu = new SeparatorMenuItem();

        this.menuFile.getItems().addAll(mFileItemExportFile, mFileItemImportFile,
                separatorMenu, mFileItemExit);

        this.menuEdit = new Menu("Edit");
        this.mEditUndo = new MenuItem("Undo");
        this.mEditRedo = new MenuItem("Redo");
        this.mEditClearGraph = new MenuItem("Clear Graph");
        this.separatorEdit = new SeparatorMenuItem();
        this.menuEdit.getItems().addAll(mEditUndo, mEditRedo, separatorEdit, mEditClearGraph);

        this.mHelpAbout = new MenuItem("About");
        this.menuHelp = new Menu("Help");
        this.menuHelp.getItems().add(mHelpAbout);

        this.menuBar = new MenuBar();
        this.menuBar.getMenus().addAll(menuFile, menuEdit, menuHelp);

        // Criteria type on left pane
        final ToggleGroup group = new ToggleGroup();
        this.lblCriteria = new Label("Modos Pesquisa");
        this.lblCriteria.setFont(new Font("Verdana", 16));
        this.lblCriteria.setPadding(new Insets(0, 0, 15, 0));
        lblCriteria.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(lblCriteria, 0.0);
        AnchorPane.setRightAnchor(lblCriteria, 0.0);
        lblCriteria.setAlignment(Pos.CENTER);

        this.txtFieldURL = new TextField();
        this.txtFieldURL.setText("http://www.brunomnsilva.com/sandbox/index.html");
        this.txtFieldURL.setId("textFieldSearchURL");
        this.txtFieldURL.setPrefSize(6, 15);

        this.btnStartCrawler = new Button("GO!");
        this.rdBtnBreadthFirst = new RadioButton("Largura");
        this.rdBtnBreadthFirst.setToggleGroup(group);
        this.rdBtnBreadthFirst.setSelected(true);

        this.lblNumPages = new Label("Número de páginas");
        this.txtFieldNumPages = new TextField();
        this.txtFieldNumPages.setId("textFieldNumPages");
        this.txtFieldNumPages.setMinSize(10, 10);
        this.rdBtnDepth = new RadioButton("Profundidade");
        this.rdBtnDepth.setToggleGroup(group);
        this.rdBtnDepth.setSelected(false);
        this.rdBtnIterative = new RadioButton("Iterativo");
        this.rdBtnIterative.setToggleGroup(group);
        this.rdBtnIterative.setSelected(false);

        //Left Layout
        this.anchorPaneLeft = new AnchorPane();
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10000,
                Integer.parseInt(INITAL_VALUE)));
        spinner.setEditable(true);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(1));
        int row = 0;
        grid.add(new Label("Nº Pages: "), 0, row);
        grid.add(spinner, 1, row);

        VBox items = new VBox(lblCriteria, txtFieldURL, btnStartCrawler,
                rdBtnBreadthFirst, grid, rdBtnDepth, rdBtnIterative);
        items.setPadding(new Insets(5, 0, 0, 10));
        AnchorPane.setTopAnchor(items, 15.0);
        AnchorPane.setLeftAnchor(items, 10.0);
        AnchorPane.setRightAnchor(items, 10.0);
        this.anchorPaneLeft.getChildren().add(items);
        //END LEFT LAYOUT

        // Statistics on rigth pane
        this.lblStatistics = new Label("Estatísticas");
        this.anchorPaneRigth = new AnchorPane();
        VBox vboxChart = barChart();
        AnchorPane.setTopAnchor(vboxChart, 10.0);
        AnchorPane.setLeftAnchor(vboxChart, 10.0);
        AnchorPane.setRightAnchor(vboxChart, 80.0);
        AnchorPane.setBottomAnchor(vboxChart, 80.0);
        anchorPaneRigth.getChildren().addAll(vboxChart);

        //Graph interface
        SmartGraphDemoContainer graphContainter = new SmartGraphDemoContainer(graphView);
        graphView.setAutomaticLayout(true);
        this.splitPane = new SplitPane();
        this.splitPane.setDividerPositions(0.5f, 1.3f, 0.4f);
        this.splitPane.getItems().addAll(anchorPaneLeft, graphContainter, anchorPaneRigth);

        //Config HBox Bootom
        Pane panelBottom = new Pane();
        panelBottom.setPadding(new Insets(0, 410, 0, 410));
        this.labelErros = new Label("Erros Aqui");
        this.lblAnotherThing = new Label("Outra Coisa");
        this.bottomHBox = new HBox();
        HBox.setHgrow(panelBottom, Priority.ALWAYS);
        this.bottomHBox.getChildren().addAll(labelErros, panelBottom, lblAnotherThing);
        bottomHBox.setStyle("-fx-background-color: #867B71;");

        /*Creates layout*/
        Home.setVgrow(splitPane, Priority.ALWAYS);
        this.getChildren().addAll(menuBar, splitPane, bottomHBox);
        getStylesheets().add(this.getClass().getResource("/Resources/css/styles.css").toExternalForm());

    }

    /**
     * This method build the bar chart
     *
     * @return
     */
    private VBox barChart() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Devices");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Visits");
        BarChart barChart = new BarChart(xAxis, yAxis);
        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("2014");
        dataSeries1.getData().add(new XYChart.Data("Desktop", 567));
        dataSeries1.getData().add(new XYChart.Data("Phone", 65));
        dataSeries1.getData().add(new XYChart.Data("Tablet", 23));
        barChart.getData().add(dataSeries1);
        VBox vbox = new VBox(barChart);
        return vbox;
    }

    @Override
    public void update(Observable o, Object o1) {
        WebCrawler obsModel = (WebCrawler) o;
        if (o instanceof WebCrawler) {
            
            if (obsModel.countWebPages() > 0) {
                graphView.update();
            }
           
            if(obsModel.isFinished == true)
                graphView.update();
                obsModel.isFinished = false;
        }

    }

    @Override
    public String getInputURL() {
        return this.txtFieldURL.getText();
    }

    private void redoGraph() {
        System.out.println("Views.Home.redoGraph()");
    }

    @Override
    public void importFile() {
        System.out.println("Views.Home.importFile()");
    }

    @Override
    public void exportFile() {
        System.out.println("Views.Home.exportFile()");
    }

    @Override
    public void exitApp() {
        Platform.exit();
        System.out.println("Views.Home.exitApp()");
        System.exit(0);
    }

    @Override
    public void clearError() {
        this.labelErros.setText("");
    }

    @Override
    public void showError(String errorMsg) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Alguma coisa não está certa...");
        alert.setContentText(errorMsg);
        alert.showAndWait();
    }

    @Override
    public void setTriggersButtons(HomeController controller) {

        this.mFileItemExit.setOnAction((ActionEvent event) -> {
            controller.exitApp();
        });

        this.mFileItemExportFile.setOnAction((ActionEvent event) -> {
            System.out.println("Export file");
        });

        this.mFileItemImportFile.setOnAction((ActionEvent event) -> {
            System.out.println("Import File");
        });

        this.mEditUndo.setOnAction((ActionEvent event) -> {
            controller.undoAction();
        });

        this.mEditRedo.setOnAction((ActionEvent event) -> {
            redoGraph();
        });

        this.mEditClearGraph.setOnAction(((event) -> {
            System.out.println("Clear graph");
        }));

        this.btnStartCrawler.setOnAction((ActionEvent t) -> {
            selectSearchType(controller);
            //graphView.update();
        });

        graphView.setVertexDoubleClickAction(graphVertex -> {
            System.out.println("Vertex contains element: " + graphVertex.getUnderlyingVertex().element());
            //want fun? uncomment below with automatic layout
            controller.removePage(graphVertex);
            //graphVertex.setStyle("-fx-fill: #D06809; -fx-stroke: black;");
            graphView.setAutomaticLayout(true);
            graphView.update();
        });

        graphView.setEdgeDoubleClickAction(graphEdge -> {
            System.out.println("Edge contains element: " + graphEdge.getUnderlyingEdge().element());
            //dynamically change the style when clicked
            graphEdge.setStyle("-fx-stroke: black; -fx-stroke-width: 2;");
            graphView.update();
        });

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
                    spinner.getEditor().textProperty().set(INITAL_VALUE);

                }
            }
        };

        // note: use KeyEvent.KEY_PRESSED, because KeyEvent.KEY_TYPED is to late, spinners
        // SpinnerValueFactory reached new value before key released an SpinnerValueFactory will
        // throw an exception
        spinner.getEditor().addEventHandler(KeyEvent.KEY_PRESSED, enterKeyEventHandler);

    }

    private void selectSearchType(HomeController controller) throws
            LoggerException, WebCrawlerException, NumberFormatException {
        try {
            int parseInt = Integer.parseInt(spinner.getEditor().textProperty().get());
            if (rdBtnBreadthFirst.isSelected()) {

                lblAnotherThing.setText("Selecionou BFS");
                controller.startSearch("BFS", parseInt);

            } else if (rdBtnDepth.isSelected()) {
                lblAnotherThing.setText("Selecionou DFS");
                controller.startSearch("DFS", parseInt);

            } else {
                lblAnotherThing.setText("Selecionou Iterativo");
                controller.startSearch("Iterative", parseInt);

            }
        } catch (IOException ex) {
            LoggerWriter.getInstance().writeToLog("Classe View btnStarcrawler: " + ex.getStackTrace()[0]);
        }
    }

    public void setColorRootPage(Vertex<WebPage> p) {
        System.out.println("root? " + p);
        graphView.getStylableVertex(p).setStyle("-fx-fill: #D06809; -fx-stroke: #61B5F1;");
    }

    @Override
    public String toString() {
        return "View: " + Home.class;
    }

    public void updateGraph() {
        graphView.update();
    }
}
