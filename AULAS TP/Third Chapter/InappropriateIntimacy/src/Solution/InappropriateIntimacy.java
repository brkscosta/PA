/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Solution;

import sun.net.www.content.image.gif;

/**
 *
 * @author Utilizador
 */
public class InappropriateIntimacy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        GrupoFormacao g1= new GrupoFormacao("grupo1");
        g1.addPeople(960313828, "joanacosta@gmail.com", "282124719", "Joanã Costa");
       
        //Hide Delegate
        System.out.println("Numero de elementos  " + g1.getElementos().size());
        System.out.println("g1 " + g1);
    }
    
}
