/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patterns.Stategy;

import Model.WebCrawler;
import Model.WebPage;

/**
 *
 * @author danielcordeiro
 */
public class SearchIterative implements ISearchCriteria {

    private WebCrawler model;

    public SearchIterative(WebCrawler model) {
        this.model = model;
    }

    @Override
    public Iterable<WebPage> searchPages(WebPage webPage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
