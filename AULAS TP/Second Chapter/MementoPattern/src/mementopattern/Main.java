/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mementopattern;

/**
 *
 * @author brunomnsilva
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BagOfWords bag = new BagOfWords("Original name");
        Caretaker caretaker = new Caretaker(bag);
        
        caretaker.requestSave();
        
        bag.add("This");
        bag.add("is");
        bag.add("the");
        
        caretaker.requestSave();
        
        bag.setName("Changed name");
        
        bag.add("memento");
        
        caretaker.requestSave();
        
        bag.add("pattern");
        
        caretaker.requestSave();
        
        bag.add(".");
        
        System.out.println("Final state: ");
        System.out.println(bag);
        System.out.println();

        System.out.println(caretaker);
        
        System.out.println("Restoring all states...");
        while(caretaker.canUndo()) {
            caretaker.requestRestore();
            System.out.println(bag);
        }

        
    }
    
}
