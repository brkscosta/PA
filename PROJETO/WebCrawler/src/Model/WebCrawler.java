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

    private String startURL = "";
    private final Graph<WebPage, Link> webCrawler;
    private WebPage initialWebPage;
    private int numPages = 0;

    /**
     *
     * Create a object of <i><p>
     * Web Crawler </p></i> type with a DiGraph instance s
     *
     * @param string Base URL
     * @param numberOfLinks Max number of link requested by user
     */
    public WebCrawler(String string, int numberOfLinks) throws IOException {
        this.startURL = string;
        this.webCrawler = new MyDiGraph();
        this.initialWebPage = new WebPage(string);
        this.numPages = numberOfLinks;
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
        Iterable<WebPage> BFS = this.BFS(initialWebPage);
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
    public Iterable<WebPage> BFS(WebPage webPage)
            throws WebCrawlerException, IOException {

        //Contar máximo de WebPages
        int countMaxVisitedPage = 0;

        //Insere página no grafo
        this.insertWebPage(webPage);

        List<WebPage> listOfWebPages = new ArrayList<>();

        Set<WebPage> visited = new HashSet<>();
        //Queue<WebPage> queue = new LinkedList<>();

        // Get all links
        Queue<Link> allIncidentWebPages = webPage.getAllIncidentWebPages(webPage.getPersonalURL());

        System.out.println("Links da página root: " + webPage.getPersonalURL()
                + " \n" + allIncidentWebPages);

        listOfWebPages.add(webPage);
        //visited.add(webPage);
        //queue.add(webPage);

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

//            while (!processedLink.isEmpty()) {
//
//                Link poll = processedLink.poll();
//
//                // Create a new WebPage
//                WebPage newGeneretedChildVertex = new WebPage(removedLinkToEnter.getLinkName());
//                insertWebPage(newGeneretedVertex);
//
//                // Add the Edge between the WebPages
//                webCrawler.insertEdge(webPage, newGeneretedChildVertex, removedLinkToEnter);
//
//            }

            // Add the new page to the queue
            //queue.offer(newGeneretedVertex);

            // Add to the BFS List -> listOfWebPages variable
            listOfWebPages.add(newGeneretedVertex);
            countMaxVisitedPage = listOfWebPages.size();

            if (countMaxVisitedPage > this.numPages) {
                return listOfWebPages;
            }

            // Removes the WebPage from the queue
            //queue.poll();
        }

        return listOfWebPages;
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
