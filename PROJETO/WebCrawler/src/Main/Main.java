/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Model.WebCrawler;
import Model.WebCrawlerException;
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
        
        try {
            
            String moodle = "https://moodle.ips.pt/1920/";
            WebCrawler crawler = new WebCrawler(moodle);
            crawler.start(moodle, 500);
            System.out.println("Num Links " + crawler.countLinks());
            System.out.println("Num Titles " + crawler.countTitles());
            crawler.breadthOrder("https://moodle.ips.pt/1920/course/index.php?categoryid=7#maincontent", 50);
            //System.out.println(crawler);

        } catch (WebCrawlerException e) {
            System.out.println(e.getMessage());
        }

    }

}
