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
public abstract class CampanhaDescontos implements PizzariaFactory{

    private Pizzaria pizzaria;

    public void setPizzaria(Pizzaria pizzaria) {
        this.pizzaria = pizzaria;
    }

    public Pizzaria getPizzaria() {
        return pizzaria;
    }
    
    public abstract void aplicarDesconto(Produto p);
    
    @Override
    public Produto obterProduto(String referencia) throws ProdutoInexistente
    {
        Produto p = pizzaria.obterProduto(referencia);
        aplicarDesconto(p);
        return p;
    }
}
