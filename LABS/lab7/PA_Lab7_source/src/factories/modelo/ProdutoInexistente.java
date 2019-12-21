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
public class ProdutoInexistente extends Exception {

    public ProdutoInexistente(String referencia) {
        super("O produto com a referência (" + referencia + ") não existe.");
    }
    
}
