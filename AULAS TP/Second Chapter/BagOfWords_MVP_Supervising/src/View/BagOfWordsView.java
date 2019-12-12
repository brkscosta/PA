/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.BagOfWordsPresenter;
import java.util.List;
import java.util.Observer;
import javafx.scene.control.Label;

/**
 * Comportamento que uma visualização/interação do bag of words deve implementar.
 * 
 * @author brunomnsilva
 */
public interface BagOfWordsView {
    
    public String getWordInput();
    
    public String getNameInput();
    
    public void showErrorMessage(String msg);
    
    public void clearWordInput();
    
    public void clearNameInput();
    
    // Adicionar presenter
    
    public Label getLabel();
    
    public void setWords(List<String> words);
    
    //.. Outras, e.g., limpar a mensagem de erro.
    
    /**
     * Permite à view mapear os seus botões para as ações do controlador
     * @param controller 
     */
    public void setTriggers(BagOfWordsPresenter controller);
    
}
