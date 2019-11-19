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
public class GestorDocumento {
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
    
       
}
