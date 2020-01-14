/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messagechain;

/**
 *
 * @author Utilizador
 */
public class Linha { //Data Class
    private Ponto p1,p2;

    public Linha(Ponto p1, Ponto p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Ponto getP1() {
        return p1;
    }

    public Ponto getP2() {
        return p2;
    }

    @Override
    public String toString() {
        return p1 + "-" + p2;
    }
    
    
}
