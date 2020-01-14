/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Solution;

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

    public ArrayList<Solution.Condutor> getCondutores() {
        return listCondutores;
    }

    public void imprimeCondutoresInicial(char inicial) {
        for (Condutor condutor : listCondutores) {
            if (condutor.getNome().charAt(0) == inicial) {
                System.out.println(" Condutor " + condutor.getNome());
                System.out.println(" Camiao " + condutor.getCamiaoMatriculaId());
            }
        }
    }

    public Condutor pesquisaCondutorCamiao(String matricula) {
        for (Condutor condutor : listCondutores) {
            String m = condutor.getCamiaoMatriculaId();
            if (m.equals(matricula)) {
                return condutor;
            }
        }
        return null;
    }

    public String pesquisaNomeCondutorMatricula(String matricula) {
        Condutor condutor = pesquisaCondutorCamiao(matricula);
        return (condutor != null ? condutor.getNome() : "não encontrado");

    }

    public void addCondutor(Condutor condutor) {
        listCondutores.add(condutor);

    }

}
