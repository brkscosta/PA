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
public class GestorRelatorio extends GestorDocumento {

       
    private String titulo;
    
    public GestorRelatorio(int id, Date data, String texto, String autor,String  titulo) {
        super(id, data, texto, autor);
        this.titulo=titulo;
    }
 public String imprimeFormatado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}
