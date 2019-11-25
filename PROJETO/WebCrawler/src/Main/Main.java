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

        String stackOverflow = "https://stackoverflow.com/";
        String google = "https://www.google.com/";
        String youTube = "https://www.youtube.com/watch?v=yF3JWJksP9I";
        WebCrawler crawler = new WebCrawler(google, 20, WebCrawler.StopCriteria.PAGES);
        crawler.start();

    }

}
