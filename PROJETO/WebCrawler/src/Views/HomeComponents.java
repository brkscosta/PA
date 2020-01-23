/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import com.brunomnsilva.smartgraph.containers.SmartGraphDemoContainer;
import com.brunomnsilva.smartgraph.graphview.SmartGraphPanel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

/**
 * This class supports all components present on HomeView
 *
 * @author BRKsCosta
 */
public class HomeComponents extends VBox {

    //<editor-fold defaultstate="collapsed" desc="Variables">
    //<editor-fold defaultstate="collapsed" desc="Menu">
    protected MenuBar menuBar;
    protected Menu menuFile;
    protected Menu menuEdit;
    protected Menu menuHelp;
    protected MenuItem mFileItemExportFile;
    protected MenuItem mFileItemImportFile;
    protected MenuItem mFileItemExit;
    protected MenuItem mEditUndo;
    protected MenuItem mEditClearGraph;
    protected MenuItem mEditRedo;
    protected MenuItem mHelpAbout;
    protected SeparatorMenuItem separatorMenu;
    protected SeparatorMenuItem separatorEdit;
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Chart variables">
    private XYChart.Series chartVisitedPages;
    private XYChart.Series chartNotFound;
    private XYChart.Series chartHttpsProtocol;
    private XYChart.Series chartLinks;
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Actions left panel">
    protected Button btnStartCrawler;
    protected TextField txtFieldURL;
    protected TextField txtFieldNumPages;
    protected RadioButton rdBtnBreadthFirst;
    protected RadioButton rdBtnDepth;
    protected RadioButton rdBtnIterative;
    protected RadioButton rdBtnExpandedPages;
    protected final Spinner spinner = new Spinner();
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Layout">
    protected SplitPane splitPane;
    protected AnchorPane anchorPaneLeft;
    protected AnchorPane anchorPaneRigth;
    protected HBox bottomHBox;
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Labels">
    protected Label lblStatistics;
    protected Label lblInfo;
    protected Label lblCriteria;
    protected Label lblAnotherThing;
    protected Label lblWebCrawler;
    protected Label lblNumPages;
//</editor-fold>
//</editor-fold>

    public HomeComponents() {

    }

    //<editor-fold defaultstate="collapsed" desc="Chart Getters">
    /**
     * This get method will give a XYChart.Series instance
     * @return a XYChart.Series object
     */
    public XYChart.Series chartGetLinks() {
        return chartLinks;
    }
    
    /**
     * This get method will give a XYChart.Series instance
     * @return a XYChart.Series object
     */
    public XYChart.Series chartGetVisited() {
        return chartVisitedPages;
    }
    
    /**
     * This get method will give a XYChart.Series instance
     * @return a XYChart.Series object
     */
    public XYChart.Series chartGetNotFound() {
        return chartNotFound;
    }
    
    /**
     * This get method will give a XYChart.Series instance
     * @return a XYChart.Series object
     */
    public XYChart.Series chartGetHttpsProtocol() {
        return chartHttpsProtocol;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">
    /**
     * Initialize all components present on UI.
     *
     * @param graphView SmartGraphPanel lib parameter
     */
    protected void initializeComponents(SmartGraphPanel graphView) {
        //Set up menu bar
        this.menuFile = new Menu("File");
        this.mFileItemImportFile = new MenuItem("Import File");
        this.mFileItemExportFile = new MenuItem("Export File");
        this.mFileItemExit = new MenuItem("Exit");
        this.separatorMenu = new SeparatorMenuItem();
        this.menuFile.getItems().addAll(mFileItemExportFile, mFileItemImportFile, separatorMenu, mFileItemExit);
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
        this.rdBtnExpandedPages = new RadioButton("Limitar Expansão de Páginas");
        this.rdBtnExpandedPages.setToggleGroup(group);
        this.rdBtnExpandedPages.setSelected(false);
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
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10000, Integer.parseInt("10")));
        spinner.setEditable(true);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(1));
        grid.add(spinner, 1, 0);
        HBox a = new HBox(btnStartCrawler, grid);
        a.setPadding(new Insets(15, 0, 0, 0));
        VBox vboxRadio = new VBox(rdBtnBreadthFirst, rdBtnExpandedPages, rdBtnDepth, rdBtnIterative);
        vboxRadio.setPadding(new Insets(20, 0, 0, 0));
        VBox items = new VBox(lblCriteria, txtFieldURL, a, vboxRadio);
        items.setPadding(new Insets(0, 0, 0, 10));
        AnchorPane.setTopAnchor(items, 16.0);
        AnchorPane.setLeftAnchor(items, 10.0);
        AnchorPane.setRightAnchor(items, 10.0);
        this.anchorPaneLeft.getChildren().add(items);
        //END LEFT LAYOUT
        // Statistics on right pane
        this.lblStatistics = new Label("Estatísticas");
        this.anchorPaneRigth = new AnchorPane();
        VBox vboxChart = barChart();
        AnchorPane.setTopAnchor(vboxChart, 10.0);
        AnchorPane.setLeftAnchor(vboxChart, 15.0);
        AnchorPane.setRightAnchor(vboxChart, 80.0);
        AnchorPane.setBottomAnchor(vboxChart, 80.0);
        anchorPaneRigth.getChildren().addAll(vboxChart);
        
        //Center pane graph will shows here
        this.lblWebCrawler = new Label("Welcome to your WebCrawler Graph");
        this.lblWebCrawler.setFont(new Font("Verdana", 16));
        this.lblWebCrawler.setPadding(new Insets(0, 0, 15, 0));
        
        //Graph interface
        SmartGraphDemoContainer graphContainter = new SmartGraphDemoContainer(graphView);
        graphView.setAutomaticLayout(true);
        this.splitPane = new SplitPane();
        this.splitPane.setDividerPositions(0.5f, 1.3f, 0.4f);
        this.splitPane.getItems().addAll(anchorPaneLeft, graphContainter, anchorPaneRigth);
        
        //Config HBox Bootom
        Pane panelBottom = new Pane();
        panelBottom.setPadding(new Insets(0, 410, 0, 410));
        this.lblInfo = new Label("Bem-Vindo!");
        this.lblInfo.setTextFill(Paint.valueOf("#ff6961"));
        this.lblAnotherThing = new Label("Outra Coisa");
        this.bottomHBox = new HBox();
        HBox.setHgrow(panelBottom, Priority.ALWAYS);
        this.bottomHBox.getChildren().addAll(lblInfo, panelBottom /*, lblAnotherThing*/);
        bottomHBox.setStyle("-fx-background-color: #867B71;");
        /*Creates layout*/
        HomeView.setVgrow(splitPane, Priority.ALWAYS);
        this.getChildren().addAll(menuBar, splitPane, bottomHBox);
        getStylesheets().add(this.getClass().getResource("/Resources/css/styles.css").toExternalForm());
    }
    
    /**
     * This method build the bar chart
     *
     * @return Return the statistics on chart
     */
    private VBox barChart() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Páginas Web");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Nº Pág. Visitas");
        BarChart barChart = new BarChart(xAxis, yAxis);
        
        chartVisitedPages = new XYChart.Series();
        chartNotFound = new XYChart.Series();
        chartHttpsProtocol = new XYChart.Series();
        chartLinks = new XYChart.Series();
        
        chartVisitedPages.setName("Visitadas");
        chartNotFound.setName("Não encontradas");
        chartHttpsProtocol.setName("Protocolos HTTP");
        chartLinks.setName("Links entre páginas");
        
        chartVisitedPages.getData().add(new XYChart.Data("", 0));
        chartNotFound.getData().add(new XYChart.Data("", 0));
        chartHttpsProtocol.getData().add(new XYChart.Data("", 0));
        chartLinks.getData().add(new XYChart.Data("", 0));
        
        barChart.getData().addAll(chartVisitedPages, chartNotFound,
                chartHttpsProtocol, chartLinks);
        VBox vbox = new VBox(barChart);
        return vbox;
    }
//</editor-fold>
}
