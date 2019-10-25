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
public class Ponte {

    private String nome;
    private int cost;

    public Ponte(String nome, int cost) {
        this.nome = nome;
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Ponte{" + "nome = " + nome + ", cost = " + cost + '}' + "\n";
    }
}
