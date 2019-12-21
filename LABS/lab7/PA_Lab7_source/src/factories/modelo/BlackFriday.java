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
public class BlackFriday extends CampanhaDescontos {
    
    private final int DESCONTO = 40;

    public BlackFriday(Pizzaria pizzaria){
        super.setPizzaria(pizzaria);
    }
    
    @Override
    public void aplicarDesconto(Produto p) {
        p.aplicarDesconto(DESCONTO);
    }
    
}
