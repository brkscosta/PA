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
public class Camiao {
    public Matricula matricula;
    public String modelo;

    public Camiao(Matricula matricula, String modelo) {
        this.matricula = matricula;
        this.modelo = modelo;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public String getModelo() {
        return modelo;
    }
    
}
