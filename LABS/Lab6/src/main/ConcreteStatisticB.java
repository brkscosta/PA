/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author BRKsCosta
 */
public class ConcreteStatisticB implements IStatistic {

    public ConcreteStatisticB() {
    }

    @Override
    public double compute(Iterable<GradeEntry> grades) {

        int sum = 0;
        double avg = 0.0;
        double sumMinusAverage = 0.0;
        double result = 0.0;

        List<GradeEntry> arr = new ArrayList<>();
        arr.addAll((Collection<? extends GradeEntry>) grades);
        sumMinusAverage = sum(arr) - average(arr);
        return result = 1/(arr.size() - 1) * Math.pow(sumMinusAverage, 2);
    }

    private double average(List<GradeEntry> list) {
        double average = (double) sum(list) / (double) list.size();
        return average;
    }

    private int sum(List<GradeEntry> arr) {
        int sum = 0;

        for (GradeEntry line : arr) {
            sum = sum + line.getStudentGrade();

        }
        return sum;
    }


}
