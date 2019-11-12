/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startegyexample.sol1;



/**
 *
 * @author Utilizador
 */
public class TesteCalculoSalario {
    
    public static void main(String[] args) {
     Empregado vendedor = new Empregado(Empregado.TYPE_EMP.VENDEDOR, "Joao Casemiro", 
             1500, 200,0.02);
     System.out.println("vendedor salario "+ vendedor.calcularSalario(22,1000.0));
     Empregado diretor = new Empregado(Empregado.TYPE_EMP.DIRETOR, "Rui Santos", 
             1500,20,0.01);
     System.out.println("diretor salario "+ diretor.calcularSalario(22,100.0));
     
    }
    
}
