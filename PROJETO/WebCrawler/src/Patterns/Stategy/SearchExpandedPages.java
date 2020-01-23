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
import Patterns.Singleton.LoggerWriter;
import com.brunomnsilva.smartgraph.graph.Vertex;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is another strategy stop criteria class to search the WebPages
 *
 * @author BRKsCosta
 */
public class SearchExpandedPages implements ISearchCriteria {

    private WebCrawler model;
    private LoggerWriter logW = LoggerWriter.getInstance();

    public SearchExpandedPages(WebCrawler model) {
        this.model = model;
    }

    @Override
    public Iterable<WebPage> searchPages(WebPage webPage) throws WebCrawlerException{
        try {
            Queue<WebPage> webPagesToVisit = new LinkedList<>();

            if (this.model.getNumCriteria() == 0) {
                return this.model.getPagesList();
            }

            if (this.model.checkIfHasWebPage(webPage) == false) {
                // Insert the webPage in the graph
                this.model.insertPage(webPage);
                
                this.logW.webPageInsertWriteToLog(webPage, webPage, this.model.getGraph().incidentEdges(this.model.getEqualWebPageVertex(webPage.getPersonalURL())).size());
            }
            
            this.model.insertInPageList(webPage);
            this.model.countHttpProtocols(webPage.getPersonalURL());
            this.model.getPagesNotFound(webPage);
            System.out.println("Link da página root: " + webPage.getPersonalURL() + "\nIncident WebPages:\n[");
            
            // We need to add all the incidentLinks from the rootPage to thew webPagesToVisit variable
            for (Link incidentLink : webPage.getAllIncidentWebPages(webPage.getPersonalURL())) {
                WebPage subWebPageInserting = new WebPage(incidentLink.getLinkName());
                webPagesToVisit.add(subWebPageInserting);
                this.model.insertPage(subWebPageInserting);
                this.model.insertLink(webPage, subWebPageInserting, incidentLink);
                this.model.insertInPageList(subWebPageInserting);
                this.logW.webPageInsertWriteToLog(subWebPageInserting, webPage, this.model.getGraph().incidentEdges(this.model.getEqualWebPageVertex(webPage.getPersonalURL())).size());
                System.out.println("Link da página root: " + subWebPageInserting.getPersonalURL() + "\nIncident WebPages:\n[");
            }
            
            while (!webPagesToVisit.isEmpty()) {
                Queue<Link> incidentLinks;
                WebPage visitedWebPage = webPagesToVisit.poll();
                System.out.println("Link da página root: " + visitedWebPage.getPersonalURL() + "\nIncident WebPages:\n[");
                
                if (visitedWebPage.getStatusCode() != 404) {
                    
                    incidentLinks = visitedWebPage.getAllIncidentWebPages(visitedWebPage.getPersonalURL());
                    
                    // First check if the webpage has more outbounds than the criteria number
                    // If it is lower, insert in vertex
                    // If it is greater or equal, don't insert
                    System.out.println("num criteria -> " + model.getNumCriteria());
                    System.out.println("size -> " + visitedWebPage.getNumberLinks());
                    
                    
                    if(incidentLinks.size() < model.getNumCriteria()){
                        for (Link incidentLink : incidentLinks) {
                            
                            // This WebPage can already exists with that link
                            Vertex<WebPage> vertexWebPageFound = this.model.getEqualWebPageVertex(incidentLink.getLinkName());

                            // Check if it exists already a WebPage with that link
                            if (vertexWebPageFound != null) {
                                // Insert a new Link between WebPages
                                this.model.insertLink(visitedWebPage, vertexWebPageFound.element(), incidentLink);
                            } else {
                                WebPage subWebPageInserting = new WebPage(incidentLink.getLinkName());
                                webPagesToVisit.add(subWebPageInserting);

                                this.model.insertPage(subWebPageInserting);
                                this.model.insertLink(visitedWebPage, subWebPageInserting, incidentLink);

                                System.out.println("Link da sub-página: " + subWebPageInserting.getPersonalURL());
                                
                                this.model.countHttpProtocols(subWebPageInserting.getPersonalURL());
                                this.model.getPagesNotFound(subWebPageInserting);

                                this.logW.webPageInsertWriteToLog(webPage, webPage, this.model.getGraph().incidentEdges(this.model.getEqualWebPageVertex(webPage.getPersonalURL())).size());
                            }
                            
                        }
                    }
                }
            }
            System.out.println("]\n");
            
            /*if (this.model.checkIfHasWebPage(webPage) == false) {
                // Insert the webPage in the graph
                this.model.insertPage(webPage);
            }

            webPagesToVisit.add(webPage);
            this.model.insertInPageList(webPage);

            while (!webPagesToVisit.isEmpty()) {

                WebPage visitedWebPage = webPagesToVisit.poll();
                Queue<Link> incidentLinks;

                if (visitedWebPage.getStatusCode() != 404) {
                    incidentLinks = visitedWebPage.getAllIncidentWebPages(visitedWebPage.getPersonalURL());

                    if (incidentLinks.size() <= model.getNumCriteria()) {
                        for (Link incidentLink : incidentLinks) {

                            WebPage subWebPageInserting = new WebPage(incidentLink.getLinkName());
                            webPagesToVisit.offer(subWebPageInserting);

                            this.model.insertPage(subWebPageInserting);
                            this.model.insertLink(visitedWebPage, subWebPageInserting, incidentLink);

                        }
                    }

                    webPagesToVisit.poll();
                }
            }*/
           return this.model.getPagesList();
        } catch (WebCrawlerException | IOException ex) {
            Logger.getLogger(SearchExpandedPages.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
