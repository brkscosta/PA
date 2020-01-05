/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patterns.Stategy;

import Model.WebPage;

/**
 *
 * @author BRKsCosta and danielcordeiro
 */
public interface ISearchCriteria {
    public Iterable<WebPage> searchPages(WebPage webPage);
}
