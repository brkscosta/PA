/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import java.util.Date;

/**
 *
 * @author Utilizador
 */
public class GestorRelatorio extends GestorDocumento {

    private String titulo;

    public GestorRelatorio(int id, Date data, String texto, String autor, String titulo) {
        super(id, data, texto, autor);
        this.titulo = titulo;
    }

    public String imprimeFormatado() {
        String str = String.format("%s\nRELATORIO-%d-%s\n\n", getData().toString(), getId(), titulo);
        str += getTexto();
        str += String.format("\n%s\n", getAutor());
        return str;
    }
}
