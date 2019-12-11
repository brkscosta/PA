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
public class PizzariaContinente extends Pizzaria {

    public PizzariaContinente(){
        this.setIVA_NORMAL(23);
        this.setIVA_REDUZIDO(13);
    }
    
    @Override
    public Produto obterProduto(String referencia) throws ProdutoInexistente
    {
        switch(referencia)
        {
            case "6001":
                return new Produto("Pizza Margarita Individual", 8, this.getIVA_NORMAL());
            case "6002":
                return new Produto("Pizza Bacon Individual", 9, this.getIVA_NORMAL());
            case "6003":
                return new Produto("Coca-Cola 33cl", 1.25, this.getIVA_NORMAL());    
            case "6004":
                return new Produto("Agua 33cl", 0.8, this.getIVA_NORMAL());
            case "6005":
                return new ProdutoComposto("Menu Margarita Coca-Cola", this.getIVA_NORMAL(),
                        this.obterProduto("6001"), this.obterProduto("6003"));
            case "6006":
                return new ProdutoComposto("Menu Margarita Agua", this.getIVA_NORMAL(),
                        this.obterProduto("6001"), this.obterProduto("6004"));
            default:
                throw new ProdutoInexistente(referencia);
        }
    }
}
