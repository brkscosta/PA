/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Solution;

import messagechain.*;
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
            l.move(dx, dy);
        }

    }

    public boolean pesquisaPonto(Ponto ponto) {

        for (Linha l : linhas) {
            if(l.contains(ponto.getX(), ponto.getY()))
                return true;
        }
        return false;
    }

    public void addLinha(int p, int p2, int p3, int p4) {
        Solution.Linha linha = createLinha(p, p2, p3, p4);
        linhas.add(linha);

    }

    public Linha createLinha(int p, int p2, int p3, int p4) {
        return new Linha(new Ponto(p, p2), new Ponto(p3, p4));
    }

    @Override
        public String toString() {
        return "Figura{" + "linhas=" + linhas + '}';
    }

}
