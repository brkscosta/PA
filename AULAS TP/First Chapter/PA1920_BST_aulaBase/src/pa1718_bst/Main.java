/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa1718_bst;

import pa1718_bst.tad.BSTLinked;
import pa1718_bst.tad.BinarySearchTree;


/**
 *
 * @author brunomnsilva
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BSTLinked();
        
        bst.insert(2);
        bst.insert(1);
        bst.insert(3);
        bst.insert(8);
        bst.insert(6);
        bst.insert(4);
        bst.insert(5);
        bst.insert(10);
        bst.insert(9);
        
        System.out.println("BST:");
        System.out.println(bst);
        
        System.out.println("Is empty? " + bst.isEmpty());
        
        System.out.println("Size of tree? " + bst.size());
        System.out.println("Minimum element? " + bst.minimum());
        System.out.println("Maximum element? " + bst.maximum());
        
        System.out.println("Elements: ");
        Iterable<Integer> elements = bst.inOrder();
        for(Integer i : elements) {
            System.out.print(i + " ");
        }
        System.out.println();        
        
      
        System.out.println("BST:");
        System.out.println(bst);

        
        
        
        
    }
    
}
