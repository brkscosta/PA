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
public  class GestorCarta extends GestorDocumento {
    private String destinatario;

    public GestorCarta(int id, Date data, String texto, String autor,String destinatario) {
        super(id, data, texto, autor);
        this.destinatario = destinatario;
    }

    @Override
    public String escreverCabecalho() {
        templateMethod();
        
    }

    @Override
    public String escreverRodape() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

   
    
    
}
