/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.IOException;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;
import static org.junit.Assert.*;

// My packages
import Interfaces.*;
import Exceptions.*;
import org.junit.Before;

/**
 *
 * @author BRKsCosta
 */
public class WebCrawlerTest {

    private WebCrawler webCrawlerInstance;
    private MyDiGraph graphInstance;
    String moodle = "https://moodle.ips.pt/1920/";
    String stackOverflow = "https://stackoverflow.com/";

    @Before
    public void setUp() throws IOException, WebCrawlerException{
        this.webCrawlerInstance = new WebCrawler(this.moodle, 2);
        this.graphInstance = new MyDiGraph<WebPage, Link>();
        webCrawlerInstance.start();
    }
    
    /**
     * Test a page when is not found
     * @throws IOException
     * @throws WebCrawlerException 
     */
    @Test
    public void testPageNotFound() throws IOException, WebCrawlerException {
        
        String page404 = "https://deviup.com.br:3001/api/lembrete/2";
        WebPage web404 = new WebPage(page404);
        int expResult = web404.getStatusCode();
        System.out.println("Ver objeto WebPage: " + web404.getPersonalURL());
        if(expResult == 404)
            webCrawlerInstance.insertWebPage(web404);
        assertEquals(1, webCrawlerInstance.getPagesNotFound(web404));
    }
    
    @Test
    public void countLinks() throws IOException, WebCrawlerException {
        int result = webCrawlerInstance.countLinks();
        int expResult = 2;        
        assertEquals(expResult, result);
    }
    
    @Test
    public void countWebPages() {
        int result = webCrawlerInstance.countWebPages();
        int expResult = 3;
        assertEquals(expResult, result);
    }
    
    
    // POR TESTAR
    @Test 
    public void insertWebPage_true_insertingNewPageUrl(){
        
        try {
                // Testing a new WebCrawler without the start
                WebCrawler myNewWebCrawler = new WebCrawler(stackOverflow, 15);

                // Insert a new Vertex - WebPage
                myNewWebCrawler.insertWebPage(new WebPage("https://stackoverflow.com/jobs"));

                // Get the count of webPages
                int result = myNewWebCrawler.countWebPages();
                
                // Check if the new webCrawler has the inserted webPage
                assertEquals(1, result);
        }catch(Exception e){
            
        }
    }
    
    // POR TESTAR
    @Test
    public void findWebPage_true_searchingWebPage(){
        try{
            WebPage expectedWebPage = new WebPage("https://stackoverflow.com/jobs");   
            
            WebPage webPageFound = webCrawlerInstance.findWebPage(expectedWebPage);
            
            assertEquals(webPageFound, webPageFound.getPersonalURL());
        }catch (Exception e){
            
        }
    }
    
    
}
