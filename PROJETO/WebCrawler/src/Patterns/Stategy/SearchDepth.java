/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patterns.Stategy;

import Model.Link;
import Model.WebCrawler;
import Model.WebPage;
import com.brunomnsilva.smartgraph.graph.Digraph;

/**
 *
 * @author BRKsCosta
 */
public class SearchDepth implements IBreakCriteria {
    
    private WebCrawler model;
    
    public SearchDepth(WebCrawler model) {
        this.model = model;
    }

    @Override
    public Iterable<WebPage> serchPages(WebPage webPage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

}
