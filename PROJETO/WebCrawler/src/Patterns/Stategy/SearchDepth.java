/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patterns.Stategy;

import Model.Link;
import Model.WebCrawler;
import Model.WebPage;
import com.brunomnsilva.smartgraph.graph.Vertex;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author BRKsCosta
 */
public class SearchDepth implements ISearchCriteria {

    private WebCrawler model;
    private int countHttpsLinks = 0;
    private int countPageNotFound = 0;
    
    public SearchDepth(WebCrawler model) {
        this.model = model;
    }

    @Override
    public Iterable<WebPage> searchPages(WebPage webPage) {
        try {
            // Variable that counts the number of links that are far away from root
            int countLevelReached = 0;
            
            Queue<WebPage> webPagesToVisit = new LinkedList<>();
            
            if (this.model.getNumCriteria() == 0) {
                return this.model.getPagesList();
            }
            
            // First of all set the depth level 1 to the webPage root and set the attribute that it is the last WebPage of the level 1, Obvious, it is the root!!
            webPage.setDepth(1);
            webPage.setIsLastOfALevel(true);
            
            countLevelReached++;
            
            webPagesToVisit.add(webPage);
            this.model.getPagesList().add(webPage);
            
            this.countHttpsLinks = this.model.countHttpsProtocols(webPage.getPersonalURL());
            this.countPageNotFound = this.model.getPagesNotFound(webPage);
            
            while (!webPagesToVisit.isEmpty()){
                
                // Check if it is still in the wanted level 
                if (countLevelReached == this.model.getNumCriteria()) {
                        return this.model.getPagesList();
                }
                
                // Picks a WebPage to visit
                WebPage visitedWebPage = webPagesToVisit.poll();
                System.out.println("Link da página root: " + visitedWebPage.getPersonalURL() + "\nIncident WebPages:\n[");
                
                // Get all incident links in the visited WebPage -> This will always come in random order... and only for those who aren't 404 page not found TODO
                Queue<Link> allIncidentWebLinks;
                
                if(visitedWebPage.getStatusCode() == 404){
                    allIncidentWebLinks = new LinkedList();
                    // TODO -> SET COLOR TO THE 404 VERTEX'S
                }else{
                    allIncidentWebLinks = visitedWebPage.getAllIncidentWebPages(visitedWebPage.getPersonalURL());
                }
                
                // This variable will help us to know when it reaches to the last element from the queue allIncidentWebLinks
                int incidentLinksAdded = 0;
                
                // Lets take out from the allIncidentWebLinks variable the links to exixting vertex's -> This will make sure that we clear a level for the unvisited vertices
                for (Link link : allIncidentWebLinks){
                    
                    // This WebPage can already exists with that link
                    Vertex<WebPage> vertexWebPageFound = this.model.getEqualWebPageVertex(link.getLinkName());
                    if(vertexWebPageFound != null){
                        // Insert a new Link between WebPages
                        this.model.getGraph().insertEdge(visitedWebPage, vertexWebPageFound.element(), link);
                        
                        countHttpsLinks += this.model.countHttpsProtocols(link.getLinkName());
                        countPageNotFound += this.model.getPagesNotFound(vertexWebPageFound.element());
                        
                        allIncidentWebLinks.remove(link);
                    }
                }
                
                for (Link link : allIncidentWebLinks){
                    
                    // Insert a new WebPage in the graph
                    WebPage webPageInserting = new WebPage(link.getLinkName());
                    webPageInserting.setDepth(countLevelReached + 1);
                    
                    // Conditional Struture to check if it is visiting the last WebPage of the previous level AND the webPageInserting is the last incident of that last WebPage
                    if(visitedWebPage.getIsLastOfALevel() == true && incidentLinksAdded == allIncidentWebLinks.size() - 1){
                        webPageInserting.setIsLastOfALevel(true);
                        countLevelReached++; // finished the BFS of this level
                    }
                    
                    this.model.getGraph().insertVertex(webPageInserting);
                    this.model.getPagesList().add(webPageInserting);
                    webPagesToVisit.add(webPageInserting);
                    incidentLinksAdded++;
                    
                    countHttpsLinks += this.model.countHttpsProtocols(link.getLinkName());
                    countPageNotFound += this.model.getPagesNotFound(webPageInserting);
                    
                    System.out.println("Link da sub-página: " + webPageInserting.getPersonalURL());
                    
                    // Insert a new Link between WebPages
                    this.model.getGraph().insertEdge(visitedWebPage, webPageInserting, link);
                }
                System.out.println("]\n");
            }

            return this.model.getPagesList();
        } catch (IOException ex) {
            this.model.getLogger().writeToLog("Error Search Pages algorithm: " + ex.getMessage());
        }
        return null;
            
    }
}
