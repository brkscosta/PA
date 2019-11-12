/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupmanager.sol3;

import groupmanager.model.Student;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Utilizador
 */
public class GroupManagementRandom extends GroupManagement {

    public GroupManagementRandom() {
    }

    public GroupManagementRandom(Iterable<Student> set) {
        super(set);
    }

    private Student getRandomStudent(ArrayList<Student> listBase) {
        long index = Math.round(Math.random() * (listBase.size()-1));
        return listBase.remove((int) index);
    }

    public Set<Student> createGrupo(int value_ref) throws GroupException {
        HashSet<Student> group = new HashSet();

        if (value_ref > getPopulationSize()) {
            throw new GroupException("error- number is not enough");
        }
        ArrayList<Student> groupBase = new ArrayList(getPopulation());
        for (int i = 0; i < value_ref; i++) {
            group.add(getRandomStudent(groupBase));
        }

        return group;
    }
}
