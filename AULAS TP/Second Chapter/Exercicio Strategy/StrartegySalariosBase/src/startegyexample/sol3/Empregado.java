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
public class Empregado {

    //completar
    private String name;
    private double salarioBase;
    private double subsidioAlmoco;
    

    public Empregado( String name, double salarioBase, double subsidioAlmoco) {

        this.name = name;
        this.salarioBase = salarioBase;
        this.subsidioAlmoco = subsidioAlmoco;
    }

    public String getName() {
        return name;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public double calculaSalario(int nDias, double valorExtra) {
        //completar e modificar
        return 0;
    }

    
}
