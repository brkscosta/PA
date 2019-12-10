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
public class PizzariaAcores extends PizzariaContinente {
    
    public PizzariaAcores(){
        this.setIVA_NORMAL(18);
        this.setIVA_REDUZIDO(9);
    }

    @Override
    public Produto obterProduto(String referencia) throws ProdutoInexistente {
        switch(referencia)
        {   
            case "6001":
                return super.obterProduto("6001");
            case "6002":
                return super.obterProduto("6002");
            case "6003":
                return super.obterProduto("6003");
            case "6004":
                return super.obterProduto("6004");
            case "6005":
                return super.obterProduto("6005");
            case "6006":
                return super.obterProduto("6006");
            case "7001":
                return new Produto("Pizza Queijo da Ilha Individual", 10, this.getIVA_NORMAL());
            case "7002":
                return new ProdutoComposto("Menu Queijo da Ilha Coca-Cola", this.getIVA_NORMAL(),
                        this.obterProduto("7001"), this.obterProduto("6003"));
            case "7003":
                return new ProdutoComposto("Menu Queijo da Ilha Água", this.getIVA_NORMAL(),
                        this.obterProduto("7001"), this.obterProduto("6004"));
            default:
                throw new ProdutoInexistente(referencia);
        }
    }
    
}
