/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patterns.Stategy;

import Model.Link;
import Model.WebCrawler;
import Model.WebPage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * This class is responsible to search pages with the user interaction.
 * 
 * @author BRKsCosta and danielcordeiro
 */
public class SearchIterative implements ISearchCriteria {

    private WebCrawler model;
    private int countHttpsLinks = 0;
    private int countPageNotFound = 0;
    
    public SearchIterative(WebCrawler model) {
        this.model = model;
    }

    @Override
    public Iterable<WebPage> searchPages(WebPage webPage) {
        try {
            this.countHttpsLinks = this.model.countHttpsProtocols(webPage.getPersonalURL());
            this.countPageNotFound = this.model.getPagesNotFound(webPage);
            
            System.out.println("Link da página subRoot: " + webPage.getPersonalURL() + "\nIncident WebPages:\n[");

            // Get all incident links in the visited WebPage -> This will always come in random order... and only for those who aren't 404 page not found TODO
            Queue<Link> allIncidentWebLinks;
            
            if(webPage.getStatusCode() == 404){
                allIncidentWebLinks = new LinkedList();
            }else{
                allIncidentWebLinks = webPage.getAllIncidentWebPages(webPage.getPersonalURL());
            }

            for (Link link : allIncidentWebLinks) {

                countHttpsLinks += this.model.countHttpsProtocols(link.getLinkName());

                // Insert a new WebPage in the graph
                WebPage webPageInserting = new WebPage(link.getLinkName());
                this.model.getGraph().insertVertex(webPageInserting);

                countPageNotFound += this.model.getPagesNotFound(webPageInserting);

                this.model.getPagesList().add(webPageInserting);
                System.out.println("Link da sub-página: " + webPageInserting.getPersonalURL());

                // Insert a new Link between WebPages
                model.getGraph().insertEdge(webPage, webPageInserting, link);
            }
            System.out.println("]\n");
            
            return model.getPagesList();
        } catch (IOException ex) {
            model.getLogger().writeToLog("Error Search Pages algorithm: " + ex.getMessage());
        }
        return null;
    }

}
