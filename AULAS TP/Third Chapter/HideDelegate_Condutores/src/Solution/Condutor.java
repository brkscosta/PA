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
public class Condutor {

    public String nome;
    public Camiao camiao;

    public Condutor(String nome, Camiao camiao) {
        this.nome = nome;
        this.camiao = camiao;
    }

    public String getNome() {
        return nome;
    }

    public Camiao getCamiao() {
        return camiao;
    }

    public String getCamiaoMatriculaId(){
        return camiao.getMatriculaId();
    }
    
    @Override
    public String toString() {
        return "Caminhão: " + this.getCamiao();
    }
}
