/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factories.modelo;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author brunomnsilva
 */
public class Fatura {
    
    private static int NUMERO_FATURA = 1;
    
    private final int numero;
    private final String contribuinte;
    private final Date data;    
    
    private List<Produto> produtos;
   
    private Fatura(String contribuinte, Date data) {
        this.contribuinte = contribuinte;        
        this.data = data;
        this.numero = NUMERO_FATURA++;
        
        produtos = new LinkedList<>();
    }
    
    public void adicionar(Produto p) {
        produtos.add(p);
    }
    
    public double totalSemIva() {
        double total = 0;
        for(Produto p : produtos) {
            total += p.getCustoSemIva();
        }
        return total;
    }
    
    public double totalComIva() {
        double total = 0;
        for(Produto p : produtos) {
            total += p.getCustoComIva();
        }
        return total;
    }
    
    public static Fatura comContribuinte(String contribuinte){
        return new Fatura(contribuinte, new Date());
    }
    
    public static Fatura semContribuinte(){
        return new Fatura("999999999", new Date());
    }
    
    @Override
    public String toString() {
        String output = String.format("Fatura Nº %d | %s \n", this.numero, this.data.toGMTString());        
        output += String.format("Cliente NIF: %s \n", contribuinte);
        output += "----------------\n";
        for(Produto p : produtos) {
            output += p.toString() + "\n";
        }
        output += "----------------\n";
        double totalSemIva = totalSemIva();
        double totalComIva = totalComIva();
        
        output += String.format("Total S/IVA: %.2f€ \n", totalSemIva);
        output += String.format("Total Imposto: %.2f€ \n", (totalComIva - totalSemIva));
        output += String.format("Total Fatura: %.2f€ \n", totalComIva);
        
        return output;
    }
}
