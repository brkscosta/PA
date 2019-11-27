/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author BRKsCosta
 */
public class ConcreteStatisticC implements IStatistic {

    public ConcreteStatisticC() {
    }

    @Override
    public double compute(Iterable<GradeEntry> grades) {
        
        ArrayList<GradeEntry> arrGrades = new ArrayList<>();
        arrGrades.addAll((Collection<? extends GradeEntry>) grades);
        return median(arrGrades);
    }

    static double median(ArrayList<GradeEntry> values) {
        // sort array
         Collections.sort(values, Collections.reverseOrder()); 

        double median;
        // get count of scores
        int totalElements = values.size();
        // check if total number of scores is even
        if (totalElements % 2 == 0) {
             GradeEntry sumOfMiddleElements = values.get(totalElements / 2) + values.get(totalElements / 2 - 1);
            // calculate average of middle elements
            median = ((double) sumOfMiddleElements) / 2;
        } else {
            // get the middle element
            median = (double) values.size() / 2;
        }
        return median;
    }

}
