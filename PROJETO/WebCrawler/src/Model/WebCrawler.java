package Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import com.brunomnsilva.smartgraph.graph.*;
import com.brunomnsilva.smartgraph.graphview.SmartGraphPanel;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.UnexpectedException;
import java.util.Collection;
import java.util.Observable;

@SuppressWarnings("null")
/**
 * Model to be created to build de graph <code>Vertex</code>
 * {@link Graph.Vertex} is the type WebPage and <code>Edge</code>
 * {@link Graph.Edge} is the type of Link
 *
 * Use the methods setChanged() and notifyObservers()
 *
 * @author BRKsCosta and Daniel Cordeiro
 */
public class WebCrawler extends Observable {

    // Default attributes
    private String startURL = "";
    public final Graph<WebPage, Link> graph;
    private int countHttpsLinks;
    private int countPageNotFound;
    public final WebPage rootWebPage;
    public SmartGraphPanel<WebPage, Link> graphView;

    // StopCriteria
    private int numStopCriteria = 0;
    private StopCriteria stopCriteriaChoosed;

    public enum StopCriteria {
        PAGES, DEPTH
    }

    /**
     *
     * Create a object of <i><p>
     * Web Crawler </p></i> type with a DiGraph instance s
     *
     * @param baseUrl the root URL
     * @param criteriaNumber number of stop criteria
     * @param stopCriteria type of stop criteria
     * @throws java.io.IOException
     */
    public WebCrawler(String baseUrl, int criteriaNumber, StopCriteria stopCriteria) throws IOException {
        // Assigned values given
        this.startURL = baseUrl;
        this.numStopCriteria = criteriaNumber;
        this.stopCriteriaChoosed = stopCriteria;
        this.graph = new DigraphEdgeList();
        this.rootWebPage = new WebPage(baseUrl);
       
    }

    /**
     * This method start the crow of a website
     *
     * @throws java.io.IOException
     */
    public void start() throws WebCrawlerException, IOException {

        // Use different ways gettins BFS order
        Iterable<WebPage> BFS;
        if (stopCriteriaChoosed == StopCriteria.PAGES) {
            BFS = this.BFSByPages(rootWebPage);
        } else {
            BFS = this.BFSByDepth(rootWebPage);
        }

        print("\n ========= Estatísticas ========= \n");
        print(" »»»»» Páginas Visitadas (%d) ««««« \n\n %s", this.countWebPages(), BFS);
        print(" »»»»» Páginas não encontradas (%d) «««««", this.countPageNotFound);
        print(" »»»»» Ligações HTTPS (%d) «««««", this.countHttpsLinks);
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
     * @throws Exceptions.WebCrawlerException
     */
    public int getPagesNotFound(WebPage myWebPage) throws IOException, WebCrawlerException {

        if (myWebPage.getStatusCode() == 404) {
            return 1;
        }
        return 0;
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
      
        // Contar numero de WebPages contadas
        int countMaxVisitedPage = 0;
        List<WebPage> BFSList = new ArrayList<>();
        Queue<WebPage> webPagesToVisit = new LinkedList<>();

        if (this.numStopCriteria == 0) {
            return BFSList;
        }

        if (this.checkIfHasWebPage(webPage) == false) {
            // Insert the webPage in the graph
            graph.insertVertex(webPage);
            
        }

        webPagesToVisit.add(webPage);
        BFSList.add(webPage);

        // Increment countMaxVisitedPage by 1
        countMaxVisitedPage++;
        countHttpsLinks = this.countHttpsProtocols(webPage.getPersonalURL());
        countPageNotFound = this.getPagesNotFound(webPage);

        while (!webPagesToVisit.isEmpty()) {
            WebPage visitedWebPage = webPagesToVisit.poll();
            System.out.println("Link da página root: " + visitedWebPage.getPersonalURL() + "\nIncident WebPages:\n[");

            // Get all incident links for 
            Queue<Link> allIncidentWebLinks = visitedWebPage.getAllIncidentWebPages(visitedWebPage.getPersonalURL());

            for (Link link : allIncidentWebLinks) {

                if (countMaxVisitedPage == this.numStopCriteria) {
                    return BFSList;
                }

                countHttpsLinks += this.countHttpsProtocols(link.getLinkName());

                // Insert a new WebPage in the graph
                WebPage webPageInserting = new WebPage(link.getLinkName());
                graph.insertVertex(webPageInserting);
                countPageNotFound += this.getPagesNotFound(webPageInserting);

                BFSList.add(webPageInserting);
                webPagesToVisit.add(webPageInserting);
                System.out.println("Link da sub-página: " + webPageInserting.getPersonalURL());

                // Insert a new Link between WebPages
                graph.insertEdge(visitedWebPage, webPageInserting, link);
                setChanged();
                notifyObservers();
                // Increment countMaxVisitedPage by 1
                countMaxVisitedPage++;
            }
            System.out.println("]\n");
        }

        return BFSList;
    }

    /**
     * Checks if exists already a webPage like the param inside the webPage
     *
     * @param webPage we want to check
     * @return if exists the webPage
     */
    private boolean checkIfHasWebPage(WebPage webPage) {
        for (Vertex<WebPage> page : graph.vertices()) {
            if (page.element() == webPage) {
                return true;
            }
        }
        return false;
    }

    /**
     * Count https protocols
     *
     * @param startURL site URL
     * @return Number of pages founded
     * @throws MalformedURLException
     */
    private int countHttpsProtocols(String startURL) throws MalformedURLException {
        int count = 0;
        URL u = new URL(startURL);
        if (u.getProtocol().equals("https")) {
            count++;
        }
        return count;
    }

    /**
     * This method goes
     *
     * @param webPage
     * @return
     * @throws WebCrawlerException
     * @throws IOException
     */
    public Iterable<WebPage> BFSByDepth(WebPage webPage)
            throws WebCrawlerException, IOException {

        //TODO
        throw new UnexpectedException("Not supported");
    }

    /**
     * Counter of links
     *
     * @return Number of links (Edges)
     */
    public int countLinks() {
        return graph.numEdges();
    }

    /**
     * Count titles from a specific website
     *
     * @return Number of titles (Vertex)
     */
    public int countWebPages() {
        return graph.numVertices();
    }
    
    
    public WebPage getRootWebPage(){
        
        for (Vertex<WebPage> vertice : graph.vertices()){
            if(vertice.element().getTitleName().equals(rootWebPage.getTitleName())) {
                setChanged();
                notifyObservers();
                return vertice.element();
            }
        }
        return null;
        
    }

}
