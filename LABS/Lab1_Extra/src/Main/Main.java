/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Queue.QueueLinkedList;

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
        Queue.QueueLinkedList queue = new QueueLinkedList();
        
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println(queue);
        System.out.println("Tamanho da fila " + queue.size());
        
        
        
    }
    
}
