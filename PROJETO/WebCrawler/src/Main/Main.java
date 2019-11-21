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
    public static void main(String[] args) throws WebCrawlerException, IOException {

        String moodle = "https://moodle.ips.pt/1920/";
        String gTranslate = "https://translate.google.pt/";
        WebCrawler crawler = new WebCrawler(gTranslate, 700);
        crawler.start();
        System.out.println("Num Links " + crawler.countLinks());
        System.out.println("Num WebPages " + crawler.countWebPages());
        //System.out.println(crawler);

    }

}
