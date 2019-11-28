/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author BRKsCosta
 */
public class ConcreteStatisticA implements IStatistic {

    public ConcreteStatisticA() {
    }
     
    @Override
    public double compute(Iterable<GradeEntry> grades) {
        if(grades.iterator().hasNext() == false){
            return -1;
        }
        int soma = 0;
        int numeroDeGrades = 0;

        for(GradeEntry grade: grades){
            soma += grade.getStudentGrade();
            numeroDeGrades++;
        }

        return soma / numeroDeGrades;
    }
    
}
