/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solucaoTemplate;

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

    @Override
    public String imprimeCabecalho() {
        return String.format("%s\nRELATORIO-%d-%s\n\n", getData().toString(), getId(), titulo);
    }

    @Override
    public String imprimeFinal() {
        return String.format("\n%s\n", getAutor());

    }

}
