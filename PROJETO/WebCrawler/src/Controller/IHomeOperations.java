/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author BRKsCosta
 */
public interface IHomeOperations {
    
    void getInputURL();
    void undoGraph();
    void importFile();
    void exportFile();
    void exitApp();
    void clearError();
    void showError();
    
    void setTriggersButtons(HomeController controller);
    
}
