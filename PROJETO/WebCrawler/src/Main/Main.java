/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import Model.WebCrawler;
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
    public static void main(String[] args) throws IOException {
        
        String moodle = "https://moodle.ips.pt/1920/";
        String si = "https://www.si.ips.pt/ests_si/web_page.Inicial";
        WebCrawler crawler = new WebCrawler(si);        
        //crawler.checkPageResponse(g2a);
        crawler.start(si);
    }
    
}
