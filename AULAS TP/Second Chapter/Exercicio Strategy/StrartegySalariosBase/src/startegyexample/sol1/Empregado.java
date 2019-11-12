/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startegyexample.sol1;

import startegyexample.sol3.*;

/**
 *
 * @author Utilizador
 */
public class Empregado {
    public enum TYPE_EMP {
        VENDEDOR, ADMINISTRATIVO, DIRETOR
    };

    private TYPE_EMP tipo;
    private String name;
    private double salarioBase;
    private double subsidioAlmoco;
    private double ratio; // comissão e taxa (valor entre 0-1)

    public Empregado(TYPE_EMP tipo, String name, double salarioBase, double subsidioAlmoco, double ratio) {
        this.tipo = tipo;
        this.name = name;
        this.salarioBase = salarioBase;
        this.subsidioAlmoco = subsidioAlmoco;
        this.ratio = ratio;

    }

    public double calcularSalario(int nDias, double extra) {
        switch (tipo) {

        case VENDEDOR:
            return (salarioBase + ratio * extra);
        case ADMINISTRATIVO:
            return salarioBase + this.subsidioAlmoco * nDias;
        case DIRETOR:
            return salarioBase + this.subsidioAlmoco * nDias + ratio * extra;
        }

        return 0;

    }
}
