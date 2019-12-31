/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controller.HomeController;
import java.util.Observer;
/**
 *
 * @author BRKsCosta
 */
public interface IHomeOperations extends Observer {
    
    String getInputURL();
    void importFile();
    void exportFile();
    void exitApp();
    void clearError();
    void showError(String errorMsg);
    
    void setTriggersButtons(HomeController controller);
    
}
