/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startegyexample.sol2;

import startegyexample.sol3.*;

/**
 *
 * @author Utilizador
 */
public abstract class Empregado {
   
    protected String name;
    protected double salarioBase;

    public Empregado(String name, double salarioBase) {
        this.name = name;
        this.salarioBase = salarioBase;
    }
      
   public abstract double calcularSalario(int nDias,double valorExtra);
    
  
   
}
