/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Solution;

import hidedelegate.*;

/**
 *
 * @author Utilizador
 */
public class Matricula {
    private String id;
    private int ano;
    private int mes;

    public Matricula(String id, int ano, int mes) {
        this.id = id;
        this.ano = ano;
        this.mes = mes;
    }

    public String getId() {
        return id;
    }

    public int getAno() {
        return ano;
    }

    public int getMes() {
        return mes;
    }

    @Override
    public String toString() {
        return id + " - /" + ano + "/" + mes;
    }
    
    
    
}
