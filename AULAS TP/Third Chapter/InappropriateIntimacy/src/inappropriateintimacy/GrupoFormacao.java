/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inappropriateintimacy;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;

/**
 *
 * @author Utilizador
 */
public class GrupoFormacao {
    private HashSet<Pessoa> elementos;
    private String nome;
    private Date date;

    public GrupoFormacao(String nome) {
        elementos= new HashSet();
        date= new Date();
        this.nome=nome;
    }

    public HashSet<Pessoa> getElementos() {
        return elementos;
    }

    public String getNome() {
        return nome;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "GrupoFormacao{" + "elementos=" + elementos + ", nome=" + nome + ", date=" + date + '}';
    }
     
    
}
