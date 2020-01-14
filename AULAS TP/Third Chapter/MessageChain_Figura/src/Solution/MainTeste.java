/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Solution;

/**
 *
 * @author Utilizador
 */
public class MainTeste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Figura f1= new Figura();
        
        f1.addLinha(1, 2, 0, 0);
        
        System.out.println("f1 "+ f1);
        f1.moveFigura(5, 2);
        System.out.println("f1 "+ f1);
        
        
    }

}
