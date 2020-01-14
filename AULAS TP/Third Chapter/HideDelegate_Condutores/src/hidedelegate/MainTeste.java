/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hidedelegate;

/**
 *
 * @author Utilizador
 */
public class MainTeste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Camiao c1 = new Camiao(new Matricula("42-31-OS",1999,5), "TOYOTA");
        Camiao c2 = new Camiao(new Matricula("52-31-MM",2011,2), "HONDA");
        Camiao c3 = new Camiao(new Matricula("77-31-OS",1999,6), "MERCEDES");
        GestorCondutores gestor = new GestorCondutores();
        Condutor cond1= new Condutor("ana", c1);
        Condutor cond2= new Condutor("pedro", c2);
        Condutor cond3= new Condutor("antonio", c3);
        
        gestor.addCondutor(cond1);
        gestor.addCondutor(cond2);
        gestor.addCondutor(cond3);
        gestor.imprimeCondutoresInicial('a');
        System.out.println(gestor.pesquisaCondutorCamiao("42-31-OS").getNome());
    }

}
