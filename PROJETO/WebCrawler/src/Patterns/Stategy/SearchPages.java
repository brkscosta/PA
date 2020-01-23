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
import Patterns.Singleton.LoggerException;
import Patterns.Singleton.LoggerWriter;
import com.brunomnsilva.smartgraph.graph.InvalidVertexException;
import com.brunomnsilva.smartgraph.graph.Vertex;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * This class is a strategy to search all pages in breadth-first.
 *
 * @author BRKsCosta and danielcordeiro
 */
public class SearchPages implements ISearchCriteria {

    private WebCrawler model;
    private LoggerWriter logW = LoggerWriter.getInstance();

    public SearchPages(WebCrawler model) {
        this.model = model;
    }

    @Override
    public Iterable<WebPage> searchPages(WebPage webPage)
            throws WebCrawlerException {

        try {

            // Contagens
            int countMaxVisitedPage = 0;

            Queue<WebPage> webPagesToVisit = new LinkedList<>();

            if (this.model.getNumCriteria() == 0) {
                return this.model.getPagesList();
            }

            if (this.model.checkIfHasWebPage(webPage) == false) {

                // Insert the webPage in the graph
                this.model.insertPage(webPage);

                //this.wiriteToLogger(webPage, webPage);
                this.logW.webPageInsertWriteToLog(webPage, webPage, this.model.getGraph().incidentEdges(this.model.getEqualWebPageVertex(webPage.getPersonalURL())).size());
            }

            webPagesToVisit.add(webPage);
            this.model.insertInPageList(webPage);

            // Increment countMaxVisitedPage by 1
            countMaxVisitedPage++;

            this.model.countHttpProtocols(webPage.getPersonalURL());
            this.model.getPagesNotFound(webPage);

            while (!webPagesToVisit.isEmpty()) {
                WebPage visitedWebPage = webPagesToVisit.poll();
                System.out.println("Link da página root: " + visitedWebPage.getPersonalURL() + "\nIncident WebPages:\n[");

                // Get all incident links in the visited WebPage -> This will always come in random order... and only for those who aren't 404 page not found TODO
                Queue<Link> allIncidentWebLinks;
                if (visitedWebPage.getStatusCode() == 404) {
                    allIncidentWebLinks = new LinkedList();
                } else {
                    allIncidentWebLinks = visitedWebPage.getAllIncidentWebPages(visitedWebPage.getPersonalURL());
                }
                
                for (Link link : allIncidentWebLinks) {

                    if (countMaxVisitedPage == this.model.getNumCriteria()) {
                        return this.model.getPagesList();
                    }

                    // This WebPage can already exists with that link
                    Vertex<WebPage> vertexWebPageFound = this.model.getEqualWebPageVertex(link.getLinkName());

                    // Check if it exists already a WebPage with that link
                    if (vertexWebPageFound != null) {
                        // Insert a new Link between WebPagess
                        this.model.insertLink(visitedWebPage, vertexWebPageFound.element(), link);
                    } else {

                        // Insert a new WebPage in the graph
                        WebPage webPageInserting = new WebPage(link.getLinkName());
                        this.model.insertPage(webPageInserting);
                        this.model.insertInPageList(webPageInserting);
                        webPagesToVisit.add(webPageInserting);

                        // Insert a new Link between WebPages
                        this.model.insertLink(visitedWebPage, webPageInserting, link);
                        
                        this.logW.webPageInsertWriteToLog(webPageInserting, visitedWebPage, this.model.getGraph().incidentEdges(this.model.getEqualWebPageVertex(webPageInserting.getPersonalURL())).size());

                        // Increment countMaxVisitedPage by 1
                        countMaxVisitedPage++;

                        System.out.println("Link da sub-página: " + webPageInserting.getPersonalURL());

                        this.model.countHttpProtocols(webPageInserting.getPersonalURL());
                        this.model.getPagesNotFound(webPageInserting);
                    }

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
