/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patterns.Stategy;

import Model.WebPage;

/**
 * This interface decides the algorithm to use with the Strategy pattern.
 * @author BRKsCosta and danielcordeiro
 */
public interface ISearchCriteria {
    
    /**
     * This algorithm search the pages in different type of stop criteria
     * @param webPage A WebPage object
     * @return A iterable collection containing all WebPages on the graph.
     */
    public Iterable<WebPage> searchPages(WebPage webPage);
}
