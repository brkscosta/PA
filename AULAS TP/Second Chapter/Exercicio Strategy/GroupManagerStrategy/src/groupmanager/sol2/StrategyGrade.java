/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupmanager.sol2;


import groupmanager.model.Student;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Utilizador
 */
public class StrategyGrade implements Strategy {

    public StrategyGrade() {
    }

   
   
    @Override
    public Set<Student> createGrupo(List<Student> population,int value_ref) throws GroupException {
           HashSet<Student> group = new HashSet();

        for (Student std : population) {
                    if (std.getClassGrade() == value_ref) {
                        group.add(std);
                    }
                }

        return group;
    }
}
