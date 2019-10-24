/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA_application;

/**
 *
 * @author Utilizador
 */
public class Local  {
    private String nome;

    public Local(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome + ' ';
    }
    
}
