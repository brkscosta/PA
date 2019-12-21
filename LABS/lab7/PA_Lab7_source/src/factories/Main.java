/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factories;

import factories.modelo.BlackFriday;
import factories.modelo.Fatura;
import factories.modelo.PizzariaAcores;
import factories.modelo.PizzariaContinente;
import java.util.Scanner;
import factories.modelo.PizzariaFactory;
import factories.modelo.Produto;
import factories.modelo.ProdutoInexistente;
import factories.modelo.QuartaFeira;
import factories.modelo.RepositorioFaturas;

/**
 *
 * @author brunomnsilva
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
               
        //PizzariaFactory produtos = new PizzariaContinente();
        //PizzariaFactory produtos = new PizzariaAcores();
        //PizzariaFactory produtos = new QuartaFeira(new PizzariaContinente());
        PizzariaFactory produtos = new BlackFriday(new PizzariaAcores());
        
        Fatura fatura = null;
        RepositorioFaturas repositorioFaturas = RepositorioFaturas.getInstance();
        
        //leitura de varios produtos, até "sair"
        String line;
        Scanner teclado = new Scanner(System.in);
        
        while(true) {
            System.out.print("Nova Fatura. 'C'/'S' ? Nº Contrib.? (digite 'sair' para sair): ");
            line = teclado.nextLine().trim();
            
            if(line.equalsIgnoreCase("sair")) {
                System.out.println(repositorioFaturas.toString());
                break;
            }
            
            switch(line.toLowerCase().charAt(0)) {
                case 'c':
                    System.out.print("Nº Contrib.?: ");
                    String cont = teclado.nextLine();

                    //criar fatura com contribuinte
                     fatura = Fatura.comContribuinte(cont);
                    break;
                    
                case 's':
                    //criar fatura sem contribuinte
                    fatura = Fatura.semContribuinte();
                    
                    break;
                    
                default: continue; //opcao invalida. repete ciclo
            }
                        
            while(true) {
            System.out.print("(referencia|ver|gravar|apagar> ");
            line = teclado.nextLine();
            
            if(line.equalsIgnoreCase("apagar")) {
                break;
            } else if(line.equalsIgnoreCase("gravar")) {
                //gravar fatura e sair do ciclo
                repositorioFaturas.adicionarFatura(fatura);
                System.out.println("Fatura gravada.");
                break;
            }
            else if(line.equalsIgnoreCase("ver")) {
                System.out.println(fatura);
            } else {
                try
                {
                    String ref = line;
                    // Aplicar desconto
                    Produto prod = produtos.obterProduto(ref);
                    fatura.adicionar(prod);
                }
                catch(ProdutoInexistente e)
                {
                    System.out.println(e);
                }
                //referencia está em 'ref', adiciona produto
                //a fatura
            }
            
        } 
            
        }
        
        //imprime repositorio de faturas
        
    }
    
}
