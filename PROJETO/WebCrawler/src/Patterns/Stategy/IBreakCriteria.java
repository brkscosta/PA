/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patterns.Stategy;

import Model.WebPage;

/**
 *
 * @author BRKsCosta
 */
public interface IBreakCriteria {
    
    public Iterable<WebPage> serchPages(WebPage webPage);
    
    
}
