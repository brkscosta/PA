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
public  class GestorCarta extends GestorDocumento {
=======
public class GestorCarta extends GestorDocumento{
>>>>>>> 2380f3999bd41e4d52f5b880fbb8f36d20dd0500
    private String destinatario;

    public GestorCarta(int id, Date data, String texto, String autor,String destinatario) {
        super(id, data, texto, autor);
        this.destinatario = destinatario;
    }

<<<<<<< HEAD
    @Override
    public String escreverCabecalho() {
        templateMethod();
        
    }

    @Override
    public String escreverRodape() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

   
=======
    String imprimeFormatado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
>>>>>>> 2380f3999bd41e4d52f5b880fbb8f36d20dd0500
    
    
}
