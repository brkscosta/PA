/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startegyexample.sol3;

/**
 *
 * @author Utilizador
 */
public class TesteCalculoSalario {

    public static void main(String[] args) {
        Empregado emp1 = new Empregado("ricardo", 1000, 4.5);
        System.out.println("salario (estrategia base) " + emp1.calculaSalario(20, 1000));

        //completar - alterar o empregado para passar a receber em funcao de Comissao
        System.out.println("salario (estrategia comissão) " + emp1.calculaSalario(20, 1000));

    }

}
