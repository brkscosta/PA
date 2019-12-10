/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controller.HomeController;
import Controller.IHomeOperations;
import Exceptions.WebCrawlerException;
import Model.WebCrawler;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.control.ScrollPane;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

/**
 *
 * @author BRKsCosta
 */
public class Home extends VBox implements Observer, IHomeOperations {

    private WebCrawler webCrawlerModel;

    //Menu  
    private MenuBar menuBar;
    private Menu menuFile;
    private Menu menuEdit;
    private Menu menuHelp;
    private MenuItem mFileItemExportFile;
    private MenuItem mFileItemImportFile;
    private MenuItem mFileItemExit;
    private MenuItem mEditUndo;
    private MenuItem mEditRedo;
    private MenuItem mHelpAbout;
    private SeparatorMenuItem separatorMenu;

    //Actions left panel
    private Button btnStartCrawler;
    private TextField txtFieldURL;
    private TextField txtFieldNumPages;
    private RadioButton rdBtnBreadthFirst;
    private RadioButton rdBtnBreadthFirstDepth;
    private RadioButton rdBtnIterative;
    private final Spinner spinner = new Spinner();

    //Layout
    private SplitPane splitPane;
    private AnchorPane anchorPaneLeft;
    private AnchorPane anchorPaneRigth;
    private ScrollPane scrollPaneGraph;
    private HBox bottomHBox;

    //Labels
    private Label lblStatistics;
    private Label labelErros;
    private Label lblCriteria;
    private Label lblAnotherThing;
    private Label lblWebCrawler;
    private Label lblNumPages;

    private static final String INITAL_VALUE = "0";

    public Home(WebCrawler model) {
        this.webCrawlerModel = model;
        this.initializeComponents();
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
        this.menuEdit.getItems().addAll(mEditUndo, mEditRedo);

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
        this.rdBtnBreadthFirstDepth = new RadioButton("Profundidade");
        this.rdBtnBreadthFirstDepth.setToggleGroup(group);
        this.rdBtnBreadthFirstDepth.setSelected(false);
        this.rdBtnIterative = new RadioButton("Iterativo");
        this.rdBtnIterative.setToggleGroup(group);
        this.rdBtnIterative.setSelected(false);
        
        //Left Layout
        this.anchorPaneLeft = new AnchorPane();
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10000,
                Integer.parseInt(INITAL_VALUE)));
        spinner.setEditable(true);

        EventHandler<KeyEvent> enterKeyEventHandler;

        enterKeyEventHandler = (KeyEvent event) -> {
            // handle users "enter key event"
            if (event.getCode() == KeyCode.ENTER){
                try {
                    // yes, using exception for control is a bad solution ;-)
                    int parseInt = Integer.parseInt(spinner.getEditor().textProperty().get());
                    System.out.println("Number on input: " + parseInt);
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

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(1));
        int row = 0;
        grid.add(new Label("Nº Pages: "), 0, row);
        grid.add(spinner, 1, row);

        VBox items = new VBox(lblCriteria, txtFieldURL, btnStartCrawler,
                rdBtnBreadthFirst, grid, rdBtnBreadthFirstDepth, rdBtnIterative);
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

        //Center pane graph will shows here
        VBox boxScroll = new VBox();
        this.lblWebCrawler = new Label("Welcome to your WebCrawler Graph");
        this.lblWebCrawler.setFont(new Font("Verdana", 16));
        this.lblWebCrawler.setPadding(new Insets(0, 0, 15, 0));
        
        try {
            //Creating an image 
            Image image;
            image = new Image(new FileInputStream("C:\\OneDrive\\Documentos\\GitHub\\PA\\PROJETO\\WebCrawler\\src\\Resources\\images\\graph.png"));
            //Setting the image view 
            ImageView imageView = new ImageView(image);

            //Setting the position of the image 
            imageView.setX(50);
            imageView.setY(25);

            //setting the fit height and width of the image view 
            imageView.setFitHeight(455);
            imageView.setFitWidth(500);

            //Setting the preserve ratio of the image view 
            imageView.setPreserveRatio(true);
            boxScroll.getChildren().add(lblWebCrawler);
            boxScroll.getChildren().add(imageView);
            this.scrollPaneGraph = new ScrollPane();
            this.scrollPaneGraph.setContent(boxScroll);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.splitPane = new SplitPane();

        //TODO Add graph here
        this.splitPane.setDividerPositions(0.5f, 1.3f, 0.4f);
        this.splitPane.getItems().addAll(anchorPaneLeft, scrollPaneGraph, anchorPaneRigth);

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

        /*if(o instanceof WebCrawler){
            WebCrawler model = (WebCrawler)o;
            
            // TODO
            
        }*/
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public String getInputURL() {
        return this.txtFieldURL.getText();
    }

    @Override
    public void undoGraph() {
        System.out.println("Views.Home.undoGraph()");
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
    public void showError() {

    }

    @Override
    public void setTriggersButtons(HomeController controller) {

        this.mFileItemExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.exitApp();
            }
        });

        this.mFileItemExportFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Export file");
            }
        });

        this.mFileItemImportFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Import File");
            }
        });

        this.mEditUndo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                undoGraph();
            }
        });

        this.mEditRedo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                redoGraph();
            }
        });

        this.btnStartCrawler.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                try {
                    controller.start();
                } catch (WebCrawlerException | IOException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @Override
    public String toString() {
        return "View: " + Home.class;
    }

}
