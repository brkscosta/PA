package Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

// My packages
import Interfaces.*;
import Exceptions.*;

@SuppressWarnings("null")
/**
 * Model to be created to build de graph <code>Vertex</code>
 * {@link Graph.Vertex} is the type WebPage and <code>Edge</code>
 * {@link Graph.Edge} is the type of Link
 *
 * @author BRKsCosta and Daniel Cordeiro
 */
public class WebCrawler {
    
    // Default attributes
    private String startURL = "";
    private final Graph<WebPage, Link> webCrawler;
    private WebPage rootWebPage;
    
    // StopCriteria
    private int numStopCriteria = 0;
    private StopCriteria stopCriteriaChoosed;

    public enum StopCriteria{
        PAGES, DEPTH
    }
    
    
    /**
     *
     * Create a object of <i><p>
     * Web Crawler </p></i> type with a DiGraph instance s
     *
     * @param baseUrl the root url
     * @param criteriaNumber number of stop criteria
     * @param stopCriteria type of stop criteria
     */
    public WebCrawler(String baseUrl, int criteriaNumber, StopCriteria stopCriteria) throws IOException {
        
        // Assigned values given
        this.startURL = baseUrl;
        this.numStopCriteria = criteriaNumber;
        this.stopCriteriaChoosed = stopCriteria;
        
        // Instanciate new values
        this.webCrawler = new MyDiGraph<>();
        this.rootWebPage = new WebPage(baseUrl);
    }

    /**
     * Check a title if exists on graph
     *
     * @param title title to be searched
     * @return Element of type <code>Link></code>
     * @throws WebCrawlerException Launch exception case some parameters are bad
     */
    public WebPage findWebPage(WebPage webpage) throws WebCrawlerException {
        if (webpage == null) {
            throw new WebCrawlerException("Title cannot be null");
        }

        WebPage webPageFound = null;
        for (Vertex<WebPage> v : webCrawler.vertices()) {
            if (v.element().equals(webpage)) {
                webPageFound = v.element();
            }
        }

        if (webPageFound == null) {
            throw new WebCrawlerException("WebPage with code ("
                    + webpage.getTitleName() + ") does not exist");
        }

        return webPageFound;
    }

    /**
     * This method start the crow of a website
     *
     * @throws Model.WebCrawlerException To validate some bad inputs outputs
     * @throws java.io.IOException
     */
    public void start() throws WebCrawlerException, IOException {
        // Use different ways gettins BFS order
        Iterable<WebPage> BFS;
        if (stopCriteriaChoosed == StopCriteria.PAGES){
            BFS = this.BFSByPages(rootWebPage);
        }else{
            BFS = this.BFSByDepth(rootWebPage);
        }
        
        print("\n ========= Estatísticas ========= \n");
        print(" »»»»» Páginas Visitadas (%d) ««««« \n\n %s", this.countWebPages(), BFS);
        print(" »»»»» Páginas não encontradas (%d) «««««", this.getPagesNotFound(BFS.iterator().next()));
        print(" »»»»» Ligações entre páginas (%d) «««««", this.countLinks());

    }

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    /**
     * Count number of pages not found
     *
     * @param myWebPage
     * @return Counter of pages
     * @throws IOException
     */
    public int getPagesNotFound(WebPage myWebPage) throws IOException, WebCrawlerException {

        int count = 0;

        for (Vertex<WebPage> page : webCrawler.vertices()) {
            if (page.element().getStatusCode() == 404) {
                count++;
            }
        }
        return count;
    }

    /**
     * Enter in link and process all links associated
     *
     * @param webPage WebPage object
     * @exception IOException Input Output exception
     * @exception WebCrawlerException Some exception from inputs
     * @return <code>void</code>
     */
    @SuppressWarnings("UnnecessaryReturnStatement")
    public Iterable<WebPage> BFSByPages(WebPage webPage)
            throws WebCrawlerException, IOException {

        // Variables
        // Contar numero de WebPages contadas
        int countMaxVisitedPage = 0;
        List<WebPage> BFSList = new ArrayList<>();
        Queue<WebPage> WebPagesToVisit = new LinkedList<>();
        
        if (this.numStopCriteria == 0){
            System.out.println("Web Crawler não tem nenhuma Web Page");
            return BFSList;
        }else if(this.numStopCriteria >= 1){
            WebPagesToVisit.add(webPage);
            
            // Insert the webPage in the webCrawler
            webCrawler.insertVertex(webPage);
            
            // Increment countMaxVisitedPage by 1
            countMaxVisitedPage ++;
        }
        
        while (!WebPagesToVisit.isEmpty()){
            WebPage visitedWebPage = WebPagesToVisit.poll();
            System.out.println("Link da página root: " + visitedWebPage.getPersonalURL() + "\nIncident WebPages:\n[");
            
            // PRINT SOMETHING
            
            // Get all incident links for 
            Queue<Link> allIncidentWebLinks = visitedWebPage.getAllIncidentWebPages(visitedWebPage.getPersonalURL());
            
            for(Link link: allIncidentWebLinks){
                
                if (countMaxVisitedPage == this.numStopCriteria){
                    return BFSList;
                }
                
                // Insert a new WebPage in the webCrawler
                WebPage webPageInserting = new WebPage(link.getLinkName());
                //this.insertWebPage(webPageInserting); PARA QUÊ TER ISTO SE SÓ TEMOS PARA ESTE.... NEM VALE A PENA
                webCrawler.insertVertex(webPageInserting);
                WebPagesToVisit.add(webPageInserting);
                System.out.println("Link da página: " + webPageInserting.getPersonalURL());
                
                // Insert a new Link between WebPages
                webCrawler.insertEdge(visitedWebPage, webPageInserting, link);
                
                // Increment countMaxVisitedPage by 1
                countMaxVisitedPage ++;
            }
            System.out.println("]\n");
        }
        
        return BFSList;
        
        
        
        
        
/*
        while(countMaxVisitedPage != this.numStopCriteria){
            // Insert the webPage in the webCrawler
            this.insertWebPage(webPage);
            WebPagesToVisit.add(webPage);
            
            // Increment countMaxVisitedPage by 1
            countMaxVisitedPage ++;
            
            // Get all incident links for 
            Queue<Link> allIncidentWebPages = webPage.getAllIncidentWebPages(webPage.getPersonalURL());
            
            // Iterate in all incidentLinks and add to webCrawler new WebPages until it gets to the numberOfPages in numStopCriteria
            for(Link link: allIncidentWebPages){
                
                if (countMaxVisitedPage == this.numStopCriteria){
                    return BFSList;
                }
                
                // Insert a new WebPage in the webCrawler
                WebPage webPageInserting = new WebPage(link.getLinkName());
                this.insertWebPage(webPageInserting);
                
                // Insert a new Link between WebPages
                webCrawler.insertEdge(webPage, webPageInserting, link);
                
                // Increment countMaxVisitedPage by 1
                countMaxVisitedPage ++;
            }
            // Recursive for all incidentWebPages
            // Iterate in all incidentLinks and add to webCrawler new WebPages until it gets to the numberOfPages in numStopCriteria
            for(Link link: allIncidentWebPages){
                
            }
                
            
        }
        
        return BFSList;        
        
        
        
        
        
        
        
        
        
        //Insere página no grafo
        this.insertWebPage(webPage);

        Set<WebPage> visited = new HashSet<>();
        //Queue<WebPage> queue = new LinkedList<>();

        // Get all links
        Queue<Link> allIncidentWebPages = webPage.getAllIncidentWebPages(webPage.getPersonalURL());

        System.out.println("Links da página root: " + webPage.getPersonalURL()
                + " \n" + allIncidentWebPages);

        listOfWebPages.add(webPage);

        // List to add the visited links
        List<Link> visitedIncidentLinks = new ArrayList();

        while (!allIncidentWebPages.isEmpty()) {

            // Goes inside the links associate to the page
            Link removedLinkToEnter = allIncidentWebPages.poll();
            System.out.println("Link da próxima página a retirar: " + removedLinkToEnter.getLinkName());

            // Add to the visited list
            visitedIncidentLinks.add(removedLinkToEnter);

            // Create a new WebPage
            WebPage newGeneretedVertex = new WebPage(removedLinkToEnter.getLinkName());
            //insertWebPage(newGeneretedVertex);
            webCrawler.insertVertex(newGeneretedVertex);

            // Add the Edge between the WebPages
            webCrawler.insertEdge(webPage, newGeneretedVertex, removedLinkToEnter);

            // It Starts the new process of generating pages
            Queue<Link> processedLink = newGeneretedVertex.getAllIncidentWebPages(newGeneretedVertex.getPersonalURL());
            print("Links dessa Página: %d "
                    + "\n Geração de links novo vertice: %s", processedLink.size(), processedLink);

            // Add to the BFS List -> listOfWebPages variable
            listOfWebPages.add(newGeneretedVertex);

            countMaxVisitedPage = listOfWebPages.size();

            if (countMaxVisitedPage > this.numPages) {

                while (!processedLink.isEmpty()) {

                    Link poll = processedLink.poll();

                    WebPage childLinks = new WebPage(poll.getLinkName());
                    listOfWebPages.add(childLinks);
                    countMaxVisitedPage = listOfWebPages.size();
                    System.out.println("Gerando fillhos: " + childLinks);

                    insertWebPage(childLinks);
                    webCrawler.insertEdge(newGeneretedVertex, childLinks, poll);

                    if (countMaxVisitedPage > this.numPages) {
                        return listOfWebPages;
                    }

                }

            }

        }

        return listOfWebPages;*/
    }
    
    public Iterable<WebPage> BFSByDepth(WebPage webPage)
            throws WebCrawlerException, IOException {
        
        
        
        
        
        return null;
    }

    /**
     * Just insert a WebPage on the graph
     *
     * @param webPage
     * @throws WebCrawlerException
     */
    public void insertWebPage(WebPage webPage) throws WebCrawlerException {

        if (webPage == null) {
            throw new WebCrawlerException("Cannot be null");
        }
        webCrawler.insertVertex(webPage);
    }

    /**
     * Counter of links
     *
     * @return Number of links (Edges)
     */
    public int countLinks() {
        return webCrawler.numEdges();
    }

    /**
     * Count titles from a specific website
     *
     * @return Number of titles (Vertex)
     */
    public int countWebPages() {
        return webCrawler.numVertices();
    }

}
