/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupmanager.sol2;



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
public  class GroupManagement {

    

    private List<Student> population;
    private Strategy strategy;

    public GroupManagement(Strategy strategy) {
        this.population = new ArrayList();
        this.strategy=strategy;
    }

    public GroupManagement(Strategy strategy,Iterable<Student> set) {
        this(strategy);
        for (Student std : set) {
            if (!this.population.contains(std)) {
                this.population.add(std);
            }
        }
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
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
      public   Set<Student> createGrupo(int value_ref) throws GroupException{
         return strategy.createGrupo(population, value_ref);
      }


}




