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
 * @author BRKsCosta
 */
public class SearchDepth implements ISearchCriteria {

    private WebCrawler model;

    public SearchDepth(WebCrawler model) {
        this.model = model;
    }

    @Override
    public Iterable<WebPage> searchPages(WebPage webPage) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
