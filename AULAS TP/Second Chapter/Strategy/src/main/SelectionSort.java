/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.List;

/**
 *
 * @author BRKsCosta
 */
public class SelectionSort implements SortAlgorithm {

    @Override
    public List<Integer> execute(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            int selectionIndex = i;
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j) < list.get(selectionIndex)) {
                    selectionIndex = j;
                }
            }

            int temp = list.get(selectionIndex);
            list.set(selectionIndex, list.get(selectionIndex + 1));
            list.set(selectionIndex, temp);
        }
        return list;
    }

}
