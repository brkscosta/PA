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
import Main.Main;
import java.net.MalformedURLException;
import java.net.URL;

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
    public final Digraph<WebPage, Link> webCrawler;
    private WebPage rootWebPage;
    private int countHttpsLinks;
    private int countPageNotFound;

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
     * This method start the crow of a website
     *
     * @throws Exceptions.WebCrawlerException
     * @throws java.io.IOException
     */
    public void start() throws WebCrawlerException, IOException {
        // Use different ways gettins BFS order
        Iterable<WebPage> BFS;
        if (stopCriteriaChoosed == StopCriteria.PAGES) {
            //Vertex<WebPage> root = webCrawler.insertVertex((new WebPage(rootWebPage.getPersonalURL())));
            //BFS = bredthFirstTranversalVersion2(root);
            //System.out.println(bredthFirstTranversalVersion2(root));
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

        // Variables
        // Contar numero de WebPages contadas
        int countMaxVisitedPage = 0;
        List<WebPage> BFSList = new ArrayList<>();
        Queue<WebPage> webPagesToVisit = new LinkedList<>();

        if (this.numStopCriteria == 0) {
            System.out.println("Web Crawler não tem nenhuma Web Page");
            return BFSList;
        }

        if (this.checkIfHasWebPage(webPage) == false) {
            // Insert the webPage in the webCrawler
            webCrawler.insertVertex(webPage);
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

                // Insert a new WebPage in the webCrawler
                WebPage webPageInserting = new WebPage(link.getLinkName());
                webCrawler.insertVertex(webPageInserting);
                countHttpsLinks += this.getPagesNotFound(webPageInserting);

                BFSList.add(webPageInserting);
                webPagesToVisit.add(webPageInserting);
                System.out.println("Link da sub-página: " + webPageInserting.getPersonalURL());

                // Insert a new Link between WebPages
                webCrawler.insertEdge(visitedWebPage, webPageInserting, link);

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
        for (Vertex<WebPage> page : webCrawler.vertices()) {
            if (page.element() == webPage) {
                return true;
            }
        }
        return false;
    }

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
        return null;
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
