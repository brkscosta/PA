/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Model.WebCrawler;
import Exceptions.WebCrawlerException;
import java.io.IOException;

/**
 *
 * @author BRKsCosta
 */
public class Main {
   
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws WebCrawlerException, IOException {

        String moodle = "https://moodle.ips.pt/1920/";
        String stackOverFlow = "https://stackoverflow.com/";
        WebCrawler crawler = new WebCrawler(stackOverFlow, 15);
        crawler.start();
    }

}
