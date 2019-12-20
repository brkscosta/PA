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
public class GestorCarta extends GestorDocumento {

    private String destinatario;

    public GestorCarta(int id, Date data, String texto, String autor, String destinatario) {
        super(id, data, texto, autor);
        this.destinatario = destinatario;
    }

    @Override
    public String imprimeCabecalho() {
        return String.format("%d\n%s \n \n\tExmo Sr.%s\n\n", getId(), getData(), destinatario);
    }

    @Override
    public String imprimeFinal() {
        return String.format("\n \nCumprimentos\n %s\n", getAutor());

    }

}
