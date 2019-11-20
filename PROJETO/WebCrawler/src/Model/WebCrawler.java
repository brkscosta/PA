/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Graph.Edge;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import Graph.Graph;
import Graph.GraphEdgeList;
import Graph.Vertex;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Connection.Response;

@SuppressWarnings("null")
/**
 * Model to be created to build de graph
 * 
 * @author BRKsCosta
 */
public class WebCrawler {

    private String start_url = "";
    private final Graph<WebPage, Link> graph;

    public enum Criteria {
        MAX_PAGES;

        public int getUnit(int maxPages) {
            switch (this) {
                case MAX_PAGES:
                    return maxPages;
            }
            return 0;
        }
    };

    /**
     *
     * Create a object of <i><p>
     * Web Crawler</p></i> type with a DiGraph instance
     *
     * @param string Base URL
     */
    public WebCrawler(String string) {
        this.start_url = string;
        this.graph = new GraphEdgeList();
    }

    /**
     * Check a title if exists on graph
     *
     * @param title title to be searched
     * @return Element of type <code>Link></code>
     * @throws WebCrawlerException Launch exception case some parameters are bad
     */
    private WebPage checkTitle(WebPage title) throws WebCrawlerException {
        if (title == null) {
            throw new WebCrawlerException("Title cannot be null");
        }

        WebPage find = null;
        for (Vertex<WebPage> v : graph.vertices()) {
            if (v.element().equals(title)) { //equals was overriden in Airport!!
                find = v.element();
            }
        }

        if (find == null) {
            throw new WebCrawlerException("Title with code (" + title.getTitleName() + ") does not exist");
        }

        return find;
    }

    /**
     * Check if exists a link on graph
     *
     * @param link The link (URL)
     * @return True if exists false otherwise
     * @throws WebCrawlerException
     */
    private boolean isExistsLink(Link link) throws WebCrawlerException {
        if (link == null) {
            throw new WebCrawlerException("Object null");
        }

        Link find = null;
        //equals was overriden in Airport!!
        //find = v.element();
        return graph.edges().stream().anyMatch((v) -> (v.element().equals(link)));
    }

   
    /**
     * This method start the crow of a website
     *
     * @param startURL The base URL to be searched
     * @param numPages Number of pages (links) to be returned
     * @throws java.io.IOException Bad Input Outputs
     * @throws Model.WebCrawlerException To validate some bad inputs outputs
     */
    public void start(String startURL, int numPages) throws IOException, WebCrawlerException {
        breadthOrder(startURL, numPages);
    }

    /**
     * Enter in link and process all links associated
     *
     * @param baseURL The base URL to be searched
     * @param maxNumLinks The max of links to be searched
     * @exception IOException Input Output exception
     * @exception WebCrawlerException Some exception from inputs
     * @return <code>void</code>
     */
    @SuppressWarnings("UnnecessaryReturnStatement")
    public void breadthOrder(String baseURL, int maxNumLinks) throws WebCrawlerException, IOException {
        
        // TODO
    
    }

    /**
     * Counter of links
     *
     * @return Number of links (Edges)
     */
    public int countLinks() {
        // TESTING
        return graph.numEdges();
    }

    /**
     * Count titles from a specifc website
     *
     * @return Number of titles (Vertex)
     */
    public int countTitles() {
        return graph.numVertices();
    }

    /**
     * Get a link between two a given titles
     *
     * @param title Fisrt title
     * @param title2 Second title
     * @return list of links between two titles
     */
    private List<Link> getLinkBetween(WebPage title, WebPage title2)
            throws WebCrawlerException {

        if (title == null || title2 == null) {
            throw new WebCrawlerException("Title cannot be null");
        }

        WebPage a1 = checkTitle(title);
        WebPage a2 = checkTitle(title2);

        List<Link> links = new ArrayList<>();

        try {
            for (Edge<Link, WebPage> edge : graph.edges()) {
                if (title == a1 && title2 == a2) {
                    Link element = edge.element();
                    links.add(element);
                }
            }
            return links;
        } catch (Exception e) {
            throw new WebCrawlerException(e.getMessage());
        }

    }

    /**
     *
     * @return To format list of links associated of an Title
     */
    @Override
    public String toString() {
        
        
        String output = "";
        
        return output;

    }

}
