/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package templatebase;

import java.util.Date;

/**
 *
 * @author Utilizador
 */
public class TemplateBase {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
<<<<<<< HEAD
        GestorRelatorio relatorio = new GestorRelatorio(
                1, new Date(), "serve o presente relatorio", "Rita Coelho", "Experiencia da massa indecisa");
        System.out.println(relatorio.escreverDocumento());
        GestorCarta carta = new GestorCarta(
                2, new Date(), "serve o presente relatorio", "Rita Coelho", "Luis Silva");
        System.out.println(carta.escreverCarta());
    }

=======
        GestorRelatorio relatorio= new GestorRelatorio(
                1,new Date(),"serve o presente relatorio","Rita Coelho","Experiencia da massa indecisa");
        System.out.println(relatorio.imprimeFormatado());
        GestorCarta carta= new GestorCarta(
                2,new Date(),"serve o presente relatorio","Rita Coelho","Luis Silva");   
        System.out.println(carta.imprimeFormatado());
    }
    
>>>>>>> 2380f3999bd41e4d52f5b880fbb8f36d20dd0500
}
