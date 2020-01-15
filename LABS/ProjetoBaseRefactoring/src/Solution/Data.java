/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Solution;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author brkscosta
 */
public class Data {
    
    private String nome;
    private int numeroAtual;
    private List<String> numeros;

    public Data(String nome, int numero) {
        this.nome = nome;
        this.numeroAtual = numero;
        this.numeros = new ArrayList<>();
    }
    
    public int getNumeroAtual() {
        return numeroAtual;
    }

    public void setNumeroAtual(int numeroAtual) {
        this.numeroAtual = numeroAtual;
    }

    public List<String> getNumeros() {
        return numeros;
    }

    public void setNumeros(List<String> numeros) {
        this.numeros = numeros;
    }

    public Data() {
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumero() {
        return numeroAtual;
    }

    public void setNumero(int numero) {
        this.numeroAtual = numero;
    }

    @Override
    public String toString() {
        return "Daniel{" + "nome=" + nome + ", numero=" + numeros + '}';
    }
    
}
