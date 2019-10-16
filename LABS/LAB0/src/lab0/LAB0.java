/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab0;

import java.util.Random;

/**
 *
 * @author BRKsCosta
 */
public class LAB0 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        QueueArray<Integer> myQ = new QueueArray(4);
        try {
            
            for (int i = 0; i < 14; i++) {
                myQ.enqueue(i);
            }

            System.out.println(myQ);
        } catch (QueueFullException e) {
            System.out.println(e + " ");
        }
        
       try {
            //Remover 2 elementos
            System.out.println("Removi: " + myQ.dequeue());
             System.out.println(myQ.toString());
             System.out.println("Removi: " + myQ.dequeue());
             System.out.println(myQ.toString());
        } catch (QueueEmptyException e) {
             System.out.println(e.getMessage());
        }

        int i = 0;
        System.out.println("\nFILA\n");
        while (!myQ.isEmpty()) {
            System.out.println(++i + "-" + myQ.dequeue());
        }

    }

    public static int getRandomNumber() {
        
        Random rand = new Random();
        int n = rand.nextInt();
        n += 1;
        return n;
    }

}
