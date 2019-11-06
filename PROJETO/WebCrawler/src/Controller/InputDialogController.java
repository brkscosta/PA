/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Bot;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.stage.Stage;

/**
 *
 * @author BRKsCosta
 */
public class InputDialogController {
    
    @FXML
    private Button btnOk, btnCancel;
    
    @FXML
    private TextField inputTextURL;
          
    @FXML
    private void okButton(ActionEvent event) throws IOException {
        
        String start_url = this.inputTextURL.getText();
        Bot bot = new Bot(start_url);
        bot.start();
        
        //Closing panel
        Stage stage = (Stage) btnOk.getScene().getWindow();
        stage.close();
        
        WebEngine engine = new WebEngine();
        engine.load(start_url);
    }

    @FXML
    private void closeWindow(ActionEvent event) {
        
        // get a handle to the stage
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        
        // do what you have to do
        stage.close();
    }

}
