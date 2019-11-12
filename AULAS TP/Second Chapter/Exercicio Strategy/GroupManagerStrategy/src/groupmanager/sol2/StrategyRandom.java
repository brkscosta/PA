/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupmanager.sol2;


import groupmanager.model.Student;
import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Utilizador
 */
public class StrategyRandom implements Strategy {

    public StrategyRandom() {
    }

    private Student getRandomStudent(ArrayList<Student> listBase) {
        long index = Math.round(Math.random() * (listBase.size()-1));
        return listBase.remove((int) index);
    }

   
    @Override
    public Set<Student> createGrupo(List<Student> population,int value_ref) throws GroupException {
            HashSet<Student> group = new HashSet();

        if (value_ref > population.size()) {
            throw new GroupException("error- number is not enough");
        }
        ArrayList<Student> groupBase = new ArrayList(population);
        for (int i = 0; i < value_ref; i++) {
            group.add(getRandomStudent(groupBase));
        }

        return group;
    }
}
