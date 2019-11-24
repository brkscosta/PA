/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Model.WebCrawler;
import Exceptions.WebCrawlerException;
import Interfaces.Vertex;
import Model.WebPage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        String stackOverflow = "https://stackoverflow.com/";
        String google = "https://www.google.com/";
        String youTube = "https://www.youtube.com/watch?v=yF3JWJksP9I";
        String crawlerTest = "https://crawler-test.com/";
        WebCrawler crawler = new WebCrawler(youTube, 5, WebCrawler.StopCriteria.PAGES);
        crawler.start();
        
    }

}
