/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package templatebase;

import java.util.Date;

/**
 *
 * @author Utilizador
 */
<<<<<<< HEAD
public abstract class GestorDocumento {
=======
public class GestorDocumento {
>>>>>>> 2380f3999bd41e4d52f5b880fbb8f36d20dd0500
    private int id;
    private Date data;
    private String texto;
    private String autor;

    public GestorDocumento(int id, Date data, String texto, String autor) {
        this.id = id;
        this.data = data;
        this.texto = texto;
        this.autor = autor;
    }

    public int getId() {
        return id;
    }

    public Date getData() {
        return data;
    }

    public String getTexto() {
        return texto;
    }

    public String getAutor() {
        return autor;
    }
    
<<<<<<< HEAD
    public void templateMethod() {
        
        escreverCabecalho();
        escreverRodape();
        
        String output = "";
        
        output = "ID: " + this.id + "Data: \n" + this.data +
                " \n\n" + this.texto + "Autor: \n" + this.autor;
        
    }
       
    public abstract String escreverCabecalho();
    
    public abstract String escreverRodape();
    
=======
       
>>>>>>> 2380f3999bd41e4d52f5b880fbb8f36d20dd0500
}
