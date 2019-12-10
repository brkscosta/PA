/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mementopattern;

import java.util.List;

/**
 * A public memento violates the encapsulation principle
 * of the memento pattern. Only the originator should know
 * the details of its memento.
 * 
 * @deprecated 
 * 
 * @author brunomnsilva
 */
public class BagOfWordsMemento_NotEncapsulated {
    
    private String name;
    private List<String> bag;

    public BagOfWordsMemento_NotEncapsulated(String name, List<String> bag) {
        this.name = name;
        this.bag = bag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getBag() {
        return bag;
    }

    public void setBag(List<String> bag) {
        this.bag = bag;
    }
    
    
    
}
