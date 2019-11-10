/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Model.WebCrawler;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
/**
 *
 * @author BRKsCosta
 */
public class Main extends Application {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        
        String moodle = "https://moodle.ips.pt/1920/";
        String si = "https://www.si.ips.pt/ests_si/web_page.Inicial";
        WebCrawler crawler = new WebCrawler(moodle);        
        //crawler.checkPageResponse(moodle);
        crawler.start(moodle);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
    }
    
}
