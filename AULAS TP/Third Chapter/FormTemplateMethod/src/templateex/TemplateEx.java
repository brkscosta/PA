/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package templateex;

/**
 *
 * @author patricia.macedo
 */
public class TemplateEx {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // TODO code application logic here
        B b = new B('e');
        System.out.println(b.metodo("Adorei a viagem ao Mexico"));
        C c = new C('o');
        System.out.println(c.metodo("Adorei a viagem ao Mexico"));
    }

}
