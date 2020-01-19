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
 * @author patricia.macedo
 */
public class Agenda {

    private Data data;
    private String cabecalho;
    private List<Data> contatos;

    private List<Data> agenda = new ArrayList();

    public Agenda(String cabecalho) {
        this.cabecalho = cabecalho;
        data = new Data();
        this.contatos = new ArrayList();

    }

    public Agenda() {
        data = new Data();
    }

    private int returnNameIndex(String nome, int numero) {

        int i;
        
        for(Data data: this.contatos){
            if (data.getNome().equals(nome)) {
                return this.contatos.indexOf(data);
            }
        }

        Data dan = new Data(nome, numero);

        contatos.add(dan);

        return -1;
    }

    public List<Data> getContatos() {
        return contatos;
    }

    public void setContatos(List<Data> contatos) {
        this.contatos = contatos;
    }

    public boolean adicionaContacto(String nome, int numero) {
        Data newEntry = new Data(nome, numero);
        contatos.add(newEntry);
        return true;
    }

    public int getNumeroContacto(String nome) {

        int i = returnNameIndex(nome, 0);

        Data get = contatos.get(i);
        return get.getNumeroAtual();
        
    }

    public void setNumeroContacto(String nome, int numero) {

        int i = returnNameIndex(nome, 0);

        Data get = contatos.get(i);

        get.setNumeroAtual(numero);

    }

    public String convertToText() {
        String str = cabecalho;
        for (Data contato : contatos) {
            str += "\n Nome: " + contato.getNome() + "\t Numero Tlf: " + contato.getNumeroAtual();
        }

        return str;
    }

    public String imprime() {
        String txt = convertToText();
        return txt;
    }

}
