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
public class BubbleSort implements SortAlgorithm {

    @Override
    public List<Integer> execute(List<Integer> list) {
        for(int i = 0; i < list.size() - 1; i++){
            for (int j = 0; j < list.size() - 1; j++) {
                if(list.get(j) > list.get(j + 1)){
                    int temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
        return list;
    }
    
}
