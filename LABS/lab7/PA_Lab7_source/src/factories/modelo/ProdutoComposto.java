/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factories.modelo;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author brunomnsilva
 */
public class ProdutoComposto extends Produto {
    
    private List<Produto> composicao;
    
    public ProdutoComposto(String nome, double iva) {
        super(nome);
        
        composicao = new LinkedList<>();
    }
    
    public ProdutoComposto(String nome, double iva, Produto... produtos) {
        this(nome, iva);
        
        for(Produto p : produtos) {
            adicionar(p);
        }
        
    }       
    
    public final void adicionar(Produto p) {
        if(!composicao.contains(p)) {
            composicao.add(p);
        }
    }

    @Override
    public String toString() {
        String output = getNome() + "\n"; 
        for(Produto p : composicao) {
            output += "\t" + p.toString() + "\n";
        }
        return output;
    }

    @Override
    public double getCustoSemIva() {
        double total = 0;
        for(Produto p : composicao) {
            total += p.getCustoSemIva();
        }
        return total;
    }
    
    @Override
    public double getCustoComIva() {
        double total = 0;
        for(Produto p : composicao) {
            total += p.getCustoComIva();
        }
        return total;
    }

    @Override
    public void aplicarDesconto(double percentagem) {
        for(Produto p : composicao) {
            p.aplicarDesconto(percentagem);
        }
    }
    
    
    
}
