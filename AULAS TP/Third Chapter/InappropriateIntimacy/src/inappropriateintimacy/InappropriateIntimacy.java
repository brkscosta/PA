/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inappropriateintimacy;

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
        g1.getElementos().add(new Pessoa(923413111,"ana@gmail.com","911111234","Paula"));
       //Hide Delegate
        System.out.println("Numero de elementos  " + g1.getElementos().size());
        System.out.println("g1 " + g1);
    }
    
}
