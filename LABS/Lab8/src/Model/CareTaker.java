/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Stack;

/**
 *
 * @author BRKsCosta
 */
public class CareTaker {

    private Originator originator;

    private Stack<Memento> mementos = new Stack<>();

    public CareTaker(Originator originator) {
        this.originator = originator;
    }

    public void saveState() {
        Memento save = originator.createMemento();
        mementos.push(save);
        
    }

    public void restoresState() {
        if(!canUndo()) return;

        Memento pop = mementos.pop();
        originator.setMemento(pop);
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
        output = mementos.stream().map((m) -> m.getDescription() + "\n").reduce(output, String::concat);
        return output;
    }
}
