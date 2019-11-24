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
    String moodle = "https://moodle.ips.pt/1920/";
    String stackOverflow = "https://stackoverflow.com/";
    WebPage web404;

    @Before
    public void setUp() throws IOException, WebCrawlerException {
        this.webCrawlerInstance = new WebCrawler(this.moodle, 2, WebCrawler.StopCriteria.PAGES);
        
        // Test pages not found
        String page404 = "https://deviup.com.br:3001/api/lembrete/2";
        this.web404 = new WebPage(page404);
        webCrawlerInstance.webCrawler.insertVertex(web404);
        webCrawlerInstance.start();
    }

    /**
     * Test a page when is not found
     *
     * @throws IOException
     * @throws WebCrawlerException
     */
    @Test
    public void testPageNotFound() throws IOException, WebCrawlerException {
        assertEquals(1, webCrawlerInstance.getPagesNotFound(web404));
    }

    @Test
    public void countLinks() throws IOException, WebCrawlerException {
        int result = webCrawlerInstance.countLinks();
        int expResult = 1;
        assertEquals(expResult, result);
    }

    @Test
    public void countWebPages() {
        int result = webCrawlerInstance.countWebPages();
        int expResult = 3;
        assertEquals(expResult, result);
    }

}
