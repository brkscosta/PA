/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Solution;

/**
 *
 * @author patricia.macedo
 */
public class MainSolution {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // TODO code application logic here
        Agenda minhaAgenda = new Agenda("Escola");
        minhaAgenda.adicionaContacto("Ana", 23413);
        minhaAgenda.adicionaContacto("Rita", 23413);
        minhaAgenda.adicionaContacto("Lurdes", 23413);
        System.out.println(minhaAgenda.imprime());

        System.out.println(" Numero da Ana " + minhaAgenda.getNumeroContacto("Ana"));
        System.out.println("\n Contactos existentes na Agenda\n");
        int inc = 1;
        for (Data contato : minhaAgenda.getContatos()) {
             System.out.println(inc++ + "-" + contato.getNome());
        }
       
    }

}
