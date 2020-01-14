package inappropriateintimacy;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Utilizador
 */
public class Pessoa {
   private int numeroTelefone;
   private String emailAdreess;
   private String nif;
   private String name;

    public Pessoa(int numeroTelefone, String emailAdreess, String nif, String name) {
        this.numeroTelefone = numeroTelefone;
        this.emailAdreess = emailAdreess;
        this.nif = nif;
        this.name = name;
    }

    public int getNumeroTelefone() {
        return numeroTelefone;
    }

    public String getEmailAdreess() {
        return emailAdreess;
    }

    public String getNif() {
        return nif;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "numeroTelefone=" + numeroTelefone + ", emailAdreess=" + emailAdreess + ", nif=" + nif + ", name=" + name + '}';
    }
   
}
