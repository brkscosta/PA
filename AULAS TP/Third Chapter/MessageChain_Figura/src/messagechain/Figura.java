/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messagechain;

import java.util.ArrayList;

/**
 *
 * @author Utilizador
 */
public class Figura {//Data Clump & Innapropriate Intimacy & Message Chain

    private ArrayList<Linha> linhas;

    public Figura() {
        this.linhas = new ArrayList();

    }

    public void moveFigura(int dx, int dy) {
       
        for (Linha l : linhas) { 
           l.getP1().setX(l.getP1().getX()+dx);
           l.getP1().setY(l.getP1().getY()+dy);
           l.getP2().setX(l.getP2().getX()+dx);
           l.getP2().setY(l.getP2().getY()+dy);
        }

    }

    public boolean pesquisaPonto(Ponto ponto) {

        for (Linha l : linhas) {
            if (l.getP1().getX()==ponto.getX() && l.getP1().getY()==ponto.getY())
                return true;
            if (l.getP2().getX()==ponto.getX() && l.getP2().getY()==ponto.getY())
                return true;
            
            }
        
        return false;
    }

   public void addLinha(Linha l) {
        linhas.add(l);

    }

    @Override
    public String toString() {
        return "Figura{" + "linhas=" + linhas + '}';
    }

   

}
