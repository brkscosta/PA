/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patterns.Stategy;

import Model.Link;
import Model.WebCrawler;
import Model.WebCrawlerException;
import Model.WebPage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author BRKsCosta
 */
public class SearchPages implements IBreakCriteria {

    private WebCrawler model;
    private int countHttpsLinks = 0;
    private int countPageNotFound = 0;

    public SearchPages(WebCrawler model) {
        this.model = model;
    }
    
    @Override
    public Iterable<WebPage> serchPages(WebPage webPage)
            throws WebCrawlerException {

        try {
            // Contar numero de WebPages contadas
            int countMaxVisitedPage = 0;
            
            Queue<WebPage> webPagesToVisit = new LinkedList<>();
            
            if (model.getNumPages() == 0) {
                return model.getPagesList();
            }
            
            if (model.checkIfHasWebPage(webPage) == false) {
                // Insert the webPage in the graph
                model.graph.insertVertex(webPage);
            }
            
            webPagesToVisit.add(webPage);
            model.getPagesList().add(webPage);
            
            // Increment countMaxVisitedPage by 1
            countMaxVisitedPage++;
            this.countHttpsLinks = model.countHttpsProtocols(webPage.getPersonalURL());
            this.countPageNotFound = model.getPagesNotFound(webPage);
            
            while (!webPagesToVisit.isEmpty()) {
                WebPage visitedWebPage = webPagesToVisit.poll();
                System.out.println("Link da página root: " + visitedWebPage.getPersonalURL() + "\nIncident WebPages:\n[");
                
                // Get all incident links for
                Queue<Link> allIncidentWebLinks = visitedWebPage.getAllIncidentWebPages(visitedWebPage.getPersonalURL());
                
                for (Link link : allIncidentWebLinks) {
                    
                    if (countMaxVisitedPage == model.getNumPages()) {
                        return model.getPagesList();
                    }
                    
                    countHttpsLinks += model.countHttpsProtocols(link.getLinkName());
                    
                    // Insert a new WebPage in the graph
                    WebPage webPageInserting = new WebPage(link.getLinkName());
                    model.graph.insertVertex(webPageInserting);
                    
                    countPageNotFound += model.getPagesNotFound(webPageInserting);
                    
                    model.getPagesList().add(webPageInserting);
                    webPagesToVisit.add(webPageInserting);
                    System.out.println("Link da sub-página: " + webPageInserting.getPersonalURL());
                    
                    // Insert a new Link between WebPages
                    model.graph.insertEdge(visitedWebPage, webPageInserting, link);
                    
                    // Increment countMaxVisitedPage by 1
                    countMaxVisitedPage++;
                }
                System.out.println("]\n");
            }

            return model.getPagesList();
        } catch (IOException ex) {
            model.getLogger().writeToLog("Error Search Pages algorithm: " + ex.getMessage());
        }
        return null;
    }
    
   
    
}
