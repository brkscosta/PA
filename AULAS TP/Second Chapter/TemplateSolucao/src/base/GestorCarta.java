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
public class GestorCarta extends GestorDocumento {

    private String destinatario;

    public GestorCarta(int id, Date data, String texto, String autor, String destinatario) {
        super(id, data, texto, autor);
        this.destinatario = destinatario;
    }

    String imprimeFormatado() {
        String str = String.format("%d\n%s \n \n\tExmo Sr.%s\n\n", getId(), getData(), destinatario);
        str += getTexto();
        str += String.format("\n \nCumprimentos\n %s\n", getAutor());
        return str;
    }

}
