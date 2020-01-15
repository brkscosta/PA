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
class DigitalizadorAgenda {

    private String titulo;

    DigitalizadorAgenda(String titulo) {
        this.titulo = titulo;
    }

    public String convertToText(int[] numeros, String[] nomes) {
        String str = titulo;
        for (int i = 0; i < numeros.length && numeros[i] != 0; i++) {
            str += "\n Nome:" + nomes[i] + "\t Numero Tlf: " + numeros[i];
        }
        return str;
    }

}
