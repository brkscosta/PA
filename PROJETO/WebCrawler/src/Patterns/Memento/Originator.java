/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patterns.Memento;

/**
 *
 * @author BRKsCosta
 */
public interface Originator {
    
     /**
     * Request of memento for current state.
     * 
     * @return      the memento state 
     */
    public Memento save();
    
    /**
     * Request to change state for this memento.
     * 
     * @param savedState    the memento state to restore
     */
    public void restore(Memento savedState);
    
}
