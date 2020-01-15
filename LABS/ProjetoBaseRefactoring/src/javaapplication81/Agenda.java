/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication81;

/**
 *
 * @author patricia.macedo
 */
public class Agenda {

    private int numeros[];
    private String nomes[];
    private int numeroAtual;
    private String cabecalho;
    private DigitalizadorAgenda digitalizador;

    public Agenda(String cabecalho) {
        numeros = new int[20];
        nomes = new String[20];
        this.cabecalho = cabecalho;
        digitalizador = new DigitalizadorAgenda(cabecalho);
    }

    public Agenda() {
        numeros = new int[20];
        nomes = new String[20];
        digitalizador = new DigitalizadorAgenda("Agenda");
    }

    public boolean adicionaContacto(String nome, int numero) {
        int i;
        for (i = 0; i < numeros.length && numeros[i] != 0; i++) {
            if (nomes[i].equals(nome)) {
                return false;
            }
        }
        nomes[i] = nome;
        numeros[i] = numero;
        return true;
    }

    public int getNumeroContacto(String nome) {
        int i;

        for (i = 0; i < numeros.length && numeros[i] != 0; i++) {
            if (nomes[i].equals(nome)) {
                numeroAtual = numeros[i];
                return numeroAtual;
            }
        }
        return -1;
    }

    public void setNumeroContacto(String nome, int numero) {
        int i;
        for (i = 0; i < numeros.length && numeros[i] != 0; i++) {
            if (nomes[i].equals(nome)) {
                numeros[i] = numero;
            }
        }
    }

    public int[] getNumeros() {
        return numeros;
    }

    public String[] getNomes() {
        return nomes;
    }

  
    public String imprime() {
        String txt = digitalizador.convertToText(numeros, nomes);
        return txt;
    }
}
