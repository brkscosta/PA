/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author brunomnsilva
 */
public class BagOfWords extends Observable implements Originator {
    
    private String name;
    private List<String> bag;

    public BagOfWords(String name) {
        this.name = name;
        this.bag = new LinkedList<>();
    }
    
    public void add(String word) {
        
        if(!bag.contains(word))
            bag.add(word);
        
        setChanged();
        notifyObservers();
    }

    public void setName(String name) {
        this.name = name;
        
        setChanged();
        notifyObservers();
    }

    public String getName() {
        return name;
    }

    public List<String> getWords() {
        return new ArrayList<>(bag);
    }
    
    
    
    @Override
    public String toString() {
        return "BagOfWords{" + "name=" + name + ", bag=" + bag + '}';
    }

    @Override
    public Memento save() {
        return new BagOfWordsMemento(name, bag);
    }

    @Override
    public void restore(Memento savedState) {
        if(savedState instanceof BagOfWordsMemento) {
            BagOfWordsMemento save = (BagOfWordsMemento)savedState;
            
            this.name = save.name;
            this.bag = save.bag; // funciona!!! :)
            
            //alternativa, reutilizando lista atual
            //this.bag.clear();
            //this.bag.addAll(save.bag);
            
            //outra alternativa, alocando mais memória
            //this.bag = new LinkedList<>(save.bag);
            
            setChanged();
            notifyObservers();
        }
    }
    
    private class BagOfWordsMemento implements Memento {
        /* save state attributes */
        private final String name;
        private final List<String> bag;
        
        /* attributes for description */
        private final Date createdAt;
        private final int count;
        
        public BagOfWordsMemento(String name, List<String> bag) {
            this.name = name;
            //this.bag = bag; //nao funciona!!!
            this.bag = new LinkedList<>(bag);            
            
            this.createdAt = new Date();
            this.count = this.bag.size();
        }

        @Override
        public String getDescription() {        
            return String.format("BagOfWordsMemento (%d words) created at %s",
                    count, createdAt.toString());
        }
    }
    
    
}
