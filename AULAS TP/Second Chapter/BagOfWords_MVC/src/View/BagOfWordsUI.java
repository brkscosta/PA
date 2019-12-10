/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.BagOfWordsController;
import Model.BagOfWords;
import java.util.Observable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author brunomnsilva
 */
public class BagOfWordsUI extends VBox implements BagOfWordsView {
    
    private TextField txtWord, txtName;
    private Label lblName, lblError;
    private Button btAdd, btChange, btUndo;
    private ListView<String> listWords;

    
    public BagOfWordsUI(BagOfWords bag) {
        
        initComponentsAndLayout();
        
        update(bag, null);
    }
    
    private void initComponentsAndLayout() {
        
        /* init components */
        txtWord = new TextField();
        txtName = new TextField();
        
        lblName = new Label();
        lblError = new Label();
        
        btAdd = new Button("Add Word");
        btChange = new Button("Change Name");
        btUndo = new Button("Undo");
        
        listWords = new ListView<>();
        
        /* init layout */
        
        HBox line1 = new HBox(txtWord, btAdd);
        HBox line2 = new HBox(txtName, btChange);
        
        this.getChildren().addAll(line1, line2, lblName, listWords, btUndo, lblError);
        
        
    }

    @Override
    public String getWordInput() {
        return txtWord.getText();
    }

    @Override
    public String getNameInput() {
        return txtName.getText();
    }

    @Override
    public void showErrorMessage(String msg) {
        lblError.setText(msg);
    }

    @Override
    public void clearWordInput() {
        txtWord.setText("");
    }

    @Override
    public void clearNameInput() {
        txtName.setText("");
    }

    @Override
    public void update(Observable o, Object o1) {
        
        if(o instanceof BagOfWords) {
            
            BagOfWords bag = (BagOfWords)o;
            
            /* atualizar name */
            lblName.setText( bag.getName() );
           
            
            /* atualizar lista */
            listWords.getItems().clear();
            listWords.getItems().addAll(bag.getWords());
            
        }
    }

    @Override
    public void setTriggers(BagOfWordsController controller) {
        
        btAdd.setOnAction((action) -> {
            controller.doAddWord();
        });
        
        btChange.setOnAction((action) -> {
            controller.doChangeName();
        });
        
        btUndo.setOnAction((action) -> {
            controller.doUndo();
        });
        
    }
    
}
