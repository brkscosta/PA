/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hidedelegate;

import java.util.ArrayList;

/**
 *
 * @author Utilizador
 */
public class GestorCondutores {

    private ArrayList<Condutor> listCondutores;

    public GestorCondutores() {
        this.listCondutores = new ArrayList();

    }

    public void imprimeCondutoresInicial(char inicial) {
        for (Condutor condutor : listCondutores) {
            if (condutor.getNome().charAt(0) == inicial) {
                System.out.println(" Condutor " + condutor.getNome());
                System.out.println(" Camiao " + condutor.getCamiao().getMatricula().getId());
            }
        }
    }

    public Condutor pesquisaCondutorCamiao(String matricula) {
        for (Condutor condutor : listCondutores) {
           String m=condutor.getCamiao().getMatricula().getId();
            if(m.equals(matricula)){
                return condutor;
            }
        }
        return null;
    }

    public void addCondutor(Condutor condutor) {
        listCondutores.add(condutor);

    }
    
    public void getCondutor(){
        
    }

}
