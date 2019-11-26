/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author BRKsCosta
 */
public class Main extends Pane {

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
    private SeparatorMenuItem separatorMenu;
    private SplitPane splitPane;
    private AnchorPane anchorPaneLeft;
    private AnchorPane anchorPaneRigth;
    private AnchorPane anchorPaneGraph;
    private ScrollPane scrlGraph;
    private Label lblStatistics;
    private Label lblCriteria;
    private Label lblWebCrawler;
    private HBox bottomHBox;
    

    public Main() {
        this.initializeComponents();
    }

    private void initializeComponents() {

        //Set up menu bar
        this.menuFile = new Menu("File");
        this.mFileItemImportFile = new MenuItem("Import File");
        this.menuFile.getItems().add(mFileItemImportFile);
        this.mFileItemExportFile = new MenuItem("Export File");
        this.menuFile.getItems().add(mFileItemExportFile);
        this.separatorMenu = new SeparatorMenuItem();
        this.menuFile.getItems().add(separatorMenu);

        this.mFileItemExit = new MenuItem("Exit");
        this.menuFile.getItems().add(mFileItemExit);

        this.menuEdit = new Menu("Edit");
        this.mEditUndo = new MenuItem("Undo");
        this.menuEdit.getItems().add(mEditUndo);

        this.mEditRedo = new MenuItem("Redo");
        this.menuHelp.getItems().add(mEditRedo);

        this.mHelpAbout = new MenuItem("About");
        this.menuHelp = new Menu("Help");
        this.menuHelp.getItems().add(mHelpAbout);

        this.menuBar = new MenuBar(menuFile, menuEdit, menuHelp);

        this.lblCriteria = new Label("Modos Pesquisa");
        this.anchorPaneLeft = new AnchorPane(lblCriteria);

        this.splitPane = new SplitPane(anchorPaneLeft, anchorPaneGraph, anchorPaneRigth);
        
        // Finishing Setup
        this.mainVBox = new VBox();
        this.mainVBox.getChildren().add(menuBar);
        this.mainVBox.getChildren().add(splitPane);
        this.mainVBox.getChildren().add(bottomHBox);
        
        mainVBox.setSpacing(10);
        mainVBox.setPadding(new Insets(15, 20, 10, 10));
    }

}
