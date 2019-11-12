/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupmanager.sol1;

import groupmanager.model.Student;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Utilizador
 */
public class GroupManagement {

    public enum TYPE_GROUP {
        RANDOM, GRADE, IDs
    };

    private List<Student> population;

    public GroupManagement() {
        this.population = new ArrayList();
    }

    public GroupManagement(Iterable<Student> set) {
        this();
        for (Student std : set) {
            if (!this.population.contains(std)) {
                this.population.add(std);
            }
        }
    }

    public void addStudent(Student std) {
        this.population.add(std);
    }

    private Student getRandomStudent(ArrayList<Student> listBase) {
      long index = Math.round(Math.random() * (listBase.size()-1));
         return listBase.remove((int) index);
    }

    public Set<Student> createGrupo(TYPE_GROUP criteria, int value_ref) throws GroupException {
        HashSet<Student> group = new HashSet();
        switch (criteria) {
            case RANDOM:
                if (value_ref > population.size()) {
                    throw new GroupException("error- number is not enough");
                }
                ArrayList<Student> groupBase = new ArrayList(this.population);
                for (int i = 0; i < value_ref; i++) {
                    group.add(getRandomStudent(groupBase));
                }
                break;
            case GRADE:
                for (Student std : this.population) {
                    if (std.getClassGrade() == value_ref) {
                        group.add(std);
                    }
                }
                break;
            case IDs:
                for (Student std : this.population) {
                    if (std.getId() < value_ref) {
                        group.add(std);
                    }
                }
                break;
        }
        return group;
    }

    @Override
    public String toString() {
        return "GroupManagement{" + "population=" + population + '}';
    }

}




