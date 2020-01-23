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
    public Iterable<WebPage> searchPages(WebPage webPage) {
        try {
            Queue<WebPage> webPagesToVisit = new LinkedList<>();

            if (this.model.getNumCriteria() == 0) {
                return null;
            }

            if (this.model.checkIfHasWebPage(webPage) == false) {
                // Insert the webPage in the graph
                this.model.insertPage(webPage);
                logW.writeToLog(webPage.getTitleName() + " | "
                        + webPage.getPersonalURL() + " | " + webPage.getTitleName()
                        + " | " + webPage.getNumberLinks());
            }

            webPagesToVisit.add(webPage);
            this.model.insertInPageList(webPage);
            
            while (!webPagesToVisit.isEmpty()) {
                Queue<Link> incidentLinks = new LinkedList();
                
                WebPage visitedWebPage = webPagesToVisit.poll();
                
                if (visitedWebPage.getStatusCode() != 404) {
                    incidentLinks = visitedWebPage.getAllIncidentWebPages(visitedWebPage.getPersonalURL());
                    
                    boolean hasLowerOutboundNumber = incidentLinks.size() < model.getNumCriteria();
                    
                    // First check if the webpage has more outbounds than the criteria number
                    // If it is lower, insert in vertex
                    // If it is greater or equal, don't insert
                    if(hasLowerOutboundNumber){
                        for (Link incidentLink : incidentLinks) {

                            WebPage subWebPageInserting = new WebPage(incidentLink.getLinkName());
                            webPagesToVisit.offer(subWebPageInserting);

                            this.model.insertPage(subWebPageInserting);
                            this.model.insertLink(visitedWebPage, subWebPageInserting, incidentLink);
                        }
                    }
                }
                //webPagesToVisit.poll();
            }
            
            
            
            
            
            
            /*if (this.model.checkIfHasWebPage(webPage) == false) {
                // Insert the webPage in the graph
                this.model.insertPage(webPage);
                logW.writeToLog(webPage.getTitleName() + " | "
                        + webPage.getPersonalURL() + " | " + webPage.getTitleName()
                        + " | " + webPage.getNumberLinks());
            }

            webPagesToVisit.add(webPage);
            this.model.insertInPageList(webPage);

            while (!webPagesToVisit.isEmpty()) {

                WebPage visitedWebPage = webPagesToVisit.poll();
                Queue<Link> incidentLinks = new LinkedList();

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

        } catch (WebCrawlerException | IOException ex) {
            Logger.getLogger(SearchExpandedPages.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
