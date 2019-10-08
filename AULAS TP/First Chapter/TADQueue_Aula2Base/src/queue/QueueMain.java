/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queue;

/**
 *
 * @author patricia.macedo
 */
public class QueueMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Queue<String> myQ = new QueueLinked<>();
        myQ.enqueue("Luda");
        myQ.enqueue("José");
        myQ.enqueue("Claudio");
        myQ.enqueue("Andreia");
        myQ.enqueue("Rafael");
        myQ.enqueue("Diogo");
        System.out.println("O primeiro da fila é " + myQ.peek());
        System.out.println("Size " + myQ.size());
        myQ.clear();
        System.out.println("Size " + myQ.size());
//        int i=0;
//        System.out.println("\nFILA\n");
//        while(!myQ.isEmpty())
//              System.out.println(++i + "-" + myQ.dequeue());
     
            
    }

}
