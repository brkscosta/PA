/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tadstackt1;

import java.util.Iterator;

/**
 *
 * @author patricia.macedo
 */
public class TADStackT1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        StackArray<Integer> stack = new StackArray(5);
        
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        //System.out.println("Ultimo empilhado: " + stack.peek());
        
        Iterator<Integer> iterator = stack.iterator();
        while(iterator.hasNext())
            System.out.println("Próximo da pilha " +  iterator.next());
        iterator.next();
    }

}
