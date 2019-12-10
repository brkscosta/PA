/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factories.modelo;


/**
 *
 * @author brunomnsilva
 */
public class Produto  {
    
    private String nome;
    private double custoSemIva;
    private double iva;

    public Produto(String nome, double custoSemIva, double iva) {
        this.nome = nome;
        this.custoSemIva = custoSemIva;
        this.iva = iva;
    }
    
    protected Produto(String nome) {
        this.nome = nome;
        this.custoSemIva = 0;
        this.iva = 0;
    }
    
    public String getNome() {
        return nome;
    }

    public double getCustoSemIva() {
        return custoSemIva;
    }

    public double getIva() {
        return iva;
    }
    
    public double getCustoComIva() {
        return getCustoSemIva() * (1 + getIva()/100);
    }

    @Override
    public String toString() {
        return String.format("%s | %.2f€ | %d%% IVA | %.2f€", 
                                getNome(), 
                                getCustoSemIva(), 
                                ((int)getIva()), 
                                getCustoComIva());
    }
    
    public void aplicarDesconto(double percentagem) {
        this.custoSemIva *= (1 - percentagem/100);
    }
    
}
