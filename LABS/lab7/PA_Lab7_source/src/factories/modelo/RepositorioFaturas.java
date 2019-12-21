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
public final class RepositorioFaturas {
   
    private List<Fatura> repositorio;
    private static RepositorioFaturas instance = new RepositorioFaturas();
    
    private RepositorioFaturas() {
        repositorio = new LinkedList<>();
    }
    
    public static RepositorioFaturas getInstance(){
        return instance;
    }
    
       
    public void adicionarFatura(Fatura f) {
        repositorio.add(f);
       
    }
    
    @Override
    public String toString() {
        String output = "Número de Faturas registadas: " + repositorio.size() + "\n";
        for(Fatura f : repositorio) {
            output += "******\n";
            output += f.toString();
            output += "******\n";
        }
        output+="\n";
        return output;
    }

 
    
}
