/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factories.modelo;

/**
 *
 * @author danielcordeiro
 */
public abstract class Pizzaria implements PizzariaFactory{
    private double IVA_NORMAL;
    private double IVA_REDUZIDO;

    public double getIVA_NORMAL() {
        return IVA_NORMAL;
    }

    public double getIVA_REDUZIDO() {
        return IVA_REDUZIDO;
    }

    public void setIVA_NORMAL(double IVA_NORMAL) {
        this.IVA_NORMAL = IVA_NORMAL;
    }

    public void setIVA_REDUZIDO(double IVA_REDUZIDO) {
        this.IVA_REDUZIDO = IVA_REDUZIDO;
    }
    
    
}
