/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author BRKsCosta
 */
public class WebCrawlerException extends Exception {

    public WebCrawlerException(String title_or_link_is_empty) {
        super(title_or_link_is_empty);
    }
    
}
