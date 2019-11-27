/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controller.HomeController;
import Controller.IHomeOperations;
import Model.WebCrawler;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.AccessibleRole;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author BRKsCosta
 */
public class Home extends Pane implements Observer, IHomeOperations {

    private WebCrawler webCrawlerModel;

    private VBox mainVBox;
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
    private Button btnStartCrawler;
    private TextField txtFieldURL;
    private TextField txtFieldNumPages;
    private RadioButton rdBtnBreadthFirst;
    private RadioButton rdBtnBreadthFirstDepth;
    private RadioButton rdBtnIterative;
    private SeparatorMenuItem separatorMenu;
    private SplitPane splitPane;
    private AnchorPane anchorPaneLeft;
    private AnchorPane anchorPaneRigth;
    private Label lblStatistics;
    private Label lblCriteria;
    private Label lblWebCrawler;
    private Label lblNumPages;
    private ScrollPane scrollPaneGraph;
    private HBox bottomHBox;
    private Group group;
    private Scene scene;
    private PerspectiveCamera camera;

    public Home(WebCrawler model) {
        this.webCrawlerModel = model;
        this.initializeComponents();

    }

    private Scene createScene() {
        this.group = new Group();
        this.scene.setRoot(group);
        this.scene.setCamera(camera);
        return scene;
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
        this.lblCriteria = new Label("Modos Pesquisa");
        this.txtFieldURL = new TextField();
        this.btnStartCrawler = new Button("GO!");
        this.rdBtnBreadthFirst = new RadioButton("Largura");
        this.lblNumPages = new Label("Número de páginas");
        this.txtFieldNumPages = new TextField();
        this.rdBtnBreadthFirstDepth = new RadioButton("Profundidade");
        this.rdBtnIterative = new RadioButton("Iterativo");
        this.anchorPaneLeft = new AnchorPane();
        this.anchorPaneLeft.getChildren().addAll(lblCriteria, txtFieldURL, btnStartCrawler,
                rdBtnBreadthFirst, lblNumPages, txtFieldNumPages, rdBtnBreadthFirstDepth,
                rdBtnIterative);

        // Statistics on rigth pane
        this.lblStatistics = new Label("Estatísticas");
        this.anchorPaneRigth = new AnchorPane();
        this.anchorPaneRigth.getChildren().add(lblStatistics);

        //Center pane graph will shows here
        this.lblWebCrawler = new Label("Welcome to your WebCrawler Graph");
        this.scrollPaneGraph = new ScrollPane();
        this.scrollPaneGraph.setContent(lblWebCrawler);

        this.splitPane = new SplitPane();
        this.splitPane.getItems().add(0, anchorPaneLeft);
        this.splitPane.getItems().add(1, scrollPaneGraph);
        this.splitPane.getItems().add(2, anchorPaneRigth);

        //Config HBox Bootom
        this.bottomHBox = new HBox(new Label("Teste"));
        this.bottomHBox.setNodeOrientation(NodeOrientation.INHERIT);
        this.bottomHBox.setAlignment(Pos.CENTER_LEFT);
        this.bottomHBox.setAccessibleRole(AccessibleRole.PARENT);
        this.bottomHBox.setScaleShape(true);
        this.bottomHBox.setCenterShape(true);
        this.bottomHBox.setCache(true);

        //Config VBox
        this.mainVBox = new VBox();
        this.mainVBox.setAlignment(Pos.TOP_LEFT);
        this.mainVBox.setOpacity(1);
        this.mainVBox.setNodeOrientation(NodeOrientation.INHERIT);
        this.mainVBox.setCursor(Cursor.DEFAULT);
        this.mainVBox.setBlendMode(BlendMode.SRC_OVER);
        this.mainVBox.setAccessibleRole(AccessibleRole.PARENT);
        this.mainVBox.setFillWidth(true);
        this.mainVBox.setScaleX(1);
        this.mainVBox.setScaleY(1);
        this.mainVBox.setScaleZ(1);

        // Finishing Setup
        this.mainVBox.getChildren().add(menuBar);
        this.mainVBox.getChildren().add(splitPane);
        this.mainVBox.getChildren().add(bottomHBox);

        //this.mainVBox.getChildren().addAll(menuBar, splitPane, bottomHBox);
    }

    @Override
    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getInputURL() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void undoGraph() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void importFile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void exportFile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void exitApp() {
        Platform.exit();
        System.exit(0);
    }

    @Override
    public void clearError() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showError() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTriggersButtons(HomeController controller) {
        
        this.mFileItemExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.exitApp();
            }
        });
        
        this.btnStartCrawler.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent t) {
                System.out.println("Inicar crawler");
            }
        });
        
        
    }

    @Override
    public String toString() {
        return "View: " + Home.class;
    }

}
