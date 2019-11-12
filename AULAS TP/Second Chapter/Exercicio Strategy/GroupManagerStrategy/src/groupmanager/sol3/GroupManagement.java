/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupmanager.sol3;


import groupmanager.model.Student;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Utilizador
 */
public abstract class GroupManagement {

   

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

   
  
    @Override
    public String toString() {
        return "GroupManagement{" + "population=" + population + '}';
    }

    public int getPopulationSize() {
        return population.size();
    }

    public Collection<Student> getPopulation() {
        return population;
    }
      public  abstract Set<Student> createGrupo(int value_ref) throws GroupException ;


}




