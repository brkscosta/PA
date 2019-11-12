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
public class ListSorted {
    
    private List<Integer> list;

    private SortAlgorithm strategy;
    
    public ListSorted(List<Integer> list) {
        this.list = list;
        this.strategy = new BubbleSort();
    }
    
    public void setAlgorithm(SortAlgorithm algorithm) {
        this.strategy = algorithm;
    }
    
//    public List<Integer> bubbleSort() {
//       throw new UnsupportedOperationException("Não suportado ainda");
//    }
//    
//    public List<Integer> selecrionSort() {
//       throw new UnsupportedOperationException("Não suportado ainda");
//    }
    
    public List<Integer> sort() {
        return strategy.execute(list);
    }
    
}
