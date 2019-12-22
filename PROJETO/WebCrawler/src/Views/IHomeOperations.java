/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controller.HomeController;

/**
 *
 * @author BRKsCosta
 */
public interface IHomeOperations {
    
    String getInputURL();
    void undoGraph();
    void importFile();
    void exportFile();
    void exitApp();
    void clearError();
    void showError(String errorMsg);
    
    void setTriggersButtons(HomeController controller);
    
}
