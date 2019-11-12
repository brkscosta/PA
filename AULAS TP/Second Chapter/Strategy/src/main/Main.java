/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author BRKsCosta
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(2);
        list.add(4);
        list.add(10);
        
        System.out.println("Before unsorted: " + list);
        
        ListSorted sorter = new ListSorted(list);
        System.out.println("Buble Sort: " + sorter.sort());
        
        sorter.setAlgorithm(new SelectionSort());

        System.out.println("Selection Sort: " + sorter.sort());
        
    }
    
}
