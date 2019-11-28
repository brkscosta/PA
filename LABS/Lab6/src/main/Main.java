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
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        GradeEntry joana = new GradeEntry("180 221 109", "Joanã Costa", 20);
        GradeEntry almerindo = new GradeEntry("180 221 259", "Almerindo Cigano", 12);
        GradeEntry joao = new GradeEntry("180 221 125", "João Costa", 17);
        GradeEntry dani = new GradeEntry("180 221 125", "Daniele Costa", 10);

        GradesDao daoS = new GradesDao();
        StudentGrades stats = new StudentGrades();

        daoS.updateGrade("180 221 109", 18);
        daoS.insert(joao);
        daoS.insert(almerindo);
        daoS.insert(joana);
        daoS.insert(dani);

        System.out.println(daoS.selectAll());
        daoS.updateGrade("180 221 109", 19);

        System.out.println(daoS.select("180 221 109"));

        // CHange statistic compute
        stats.changeStatistic(new ConcreteStatisticA());
        System.out.println("Primeira stats Média: " + stats.computeStatistic(daoS.selectAll()));

        stats.changeStatistic(new ConcreteStatisticB());
        System.out.println("Second Variancia: " + stats.computeStatistic(daoS.selectAll()));

        stats.changeStatistic(new ConcreteStatisticC());
        System.out.println("Third Mediana: " + stats.computeStatistic(daoS.selectAll()));

    }

}
