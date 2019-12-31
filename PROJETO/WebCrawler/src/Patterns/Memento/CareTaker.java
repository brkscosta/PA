/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patterns.Memento;

import java.util.Stack;

/**
 *
 * @author BRKsCosta
 */
public class CareTaker {
    
    private Originator originator;

    private Stack<Memento> mementos = new Stack<>();

    /**
     * Creates a caretaker for a specific originator.
     * 
     * @param originator originator to care about 
     */
    public CareTaker(Originator originator) {
        this.originator = originator;
    }
    
    /**
     * Requests the originator memento state and stores it.
     */
    public void requestSave() {
        //memento = originator.save();
        Memento save = originator.save();
        System.out.println(save.getDescription());
        mementos.push(save);
    }
    
    /**
     * Requests the originator a restore of the last saved memento 
     */
    public void requestRestore() {
        if(!canUndo()) return;
        
        //originator.restore(memento);
        Memento save = mementos.pop();
        originator.restore(save);
    }
    
    /**
     * Whether a restore (undo) operation can be provided.
     * 
     * @return  true if possible 
     */
    public boolean canUndo() {
        //return memento !=  null;
        return !mementos.isEmpty();
    }

    @Override
    public String toString() {
        String output = "SAVED STATES: \n";
        for (Memento m : mementos) {
            output += m.getDescription() + "\n";
        }
        return output;
    }
    
}
