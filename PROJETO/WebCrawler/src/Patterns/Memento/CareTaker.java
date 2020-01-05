/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patterns.Memento;

import java.util.Stack;

/**
 * This class is responsible to save the state of the concrete class that we 
 * want to store. Delegate a instance from the originator {@link Model.WebCrawler}.
 * 
 * @see Patterns.Memento.IOriginator
 * @see Patterns.Memento.IMemento
 * 
 * @author BRKsCosta and danielcordeiro
 */
public class CareTaker {
    
    private IOriginator originator;

    private Stack<IMemento> mementos = new Stack<>();

    /**
     * Creates a caretaker for a specific originator.
     * 
     * @param originator originator to care about 
     */
    public CareTaker(IOriginator originator) {
        this.originator = originator;
    }
    
    /**
     * Requests the originator memento state and stores it.
     */
    public void requestSave() {
        IMemento save = originator.save();
        System.out.println(save.getDescription());
        mementos.push(save);
    }
    
    /**
     * Requests the originator a restore of the last saved memento 
     */
    public void requestRestore() {
        if(!canUndo()) return;
        
        IMemento save = mementos.pop();
        originator.restore(save);
    }
    
    /**
     * Whether a restore (undo) operation can be provided.
     * 
     * @return  true if possible 
     */
    public boolean canUndo() {
        return !mementos.isEmpty();
    }

    @Override
    public String toString() {
        String output = "SAVED STATES: \n";
        for (IMemento m : mementos) {
            output += m.getDescription() + "\n";
        }
        return output;
    }
    
}
