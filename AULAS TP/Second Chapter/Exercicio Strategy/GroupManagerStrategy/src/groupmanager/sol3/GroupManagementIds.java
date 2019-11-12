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
public class GroupManagementIds extends GroupManagement {

    public GroupManagementIds() {
    }

    public GroupManagementIds(Iterable<Student> set) {
        super(set);
    }


    public Set<Student> createGrupo(int value_ref) throws GroupException {
           HashSet<Student> group = new HashSet();

        for (Student std : getPopulation()) {
                   if (std.getId() < value_ref) {
                        group.add(std);
                    }
                }

        return group;
    }
}
