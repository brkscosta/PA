package Model;

import Patterns.Memento.Originator;
import Patterns.Memento.Memento;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import com.brunomnsilva.smartgraph.graph.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Observable;
import Patterns.Singleton.LoggerWriter;
import java.util.Date;
import java.io.Serializable;

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
public class WebCrawler extends Observable implements Originator, Serializable {

    private LoggerWriter logger = LoggerWriter.getInstance();

    private String startURL = "";
    public Graph<WebPage, Link> graph = new DigraphEdgeList<>();
    private int countHttpsLinks;
    private int countPageNotFound;
    public WebPage rootWebPage;
    private StopCriteria stopCriteriaChoosed;
    private List<WebPage> pagesList = new ArrayList<>();

    public WebCrawler() {
        this.countHttpsLinks = 0;
        this.countPageNotFound = 0;
    }

    public WebPage createWebPage() throws IOException {
        return new WebPage(startURL);
    }

    public int getCountHttpsLinks() {
        return countHttpsLinks;
    }

    public void setCountHttpsLinks(int countHttpsLinks) {
        this.countHttpsLinks = countHttpsLinks;
    }

    public int getCountPageNotFound() {
        return countPageNotFound;
    }

    public void setCountPageNotFound(int countPageNotFound) {
        this.countPageNotFound = countPageNotFound;
    }

    public Vertex<WebPage> getRootWebPage() {
        for (Vertex<WebPage> v : graph.vertices()) {
            if (v.element().equals(rootWebPage)) {
                return v;
            }
        }
        return null;
    }

    public void setRootWebPage(WebPage rootWebPage) {
        this.rootWebPage = rootWebPage;
    }

    public StopCriteria getStopCriteriaChoosed() {
        return stopCriteriaChoosed;
    }

    public void setStopCriteriaChoosed(StopCriteria stopCriteriaChoosed) {
        this.stopCriteriaChoosed = stopCriteriaChoosed;
    }

    public void removePage(Vertex<WebPage> underlyingVertex) {
        
        graph.removeVertex(underlyingVertex);
        setChanged();
        notifyObservers();
    }

    public enum StopCriteria {
        PAGES, DEPTH, ITERATIVE;
    }

    public String getStartURL() {
        return startURL;
    }

    public void setStartURL(String startURL) {
        this.startURL = startURL;
    }

    /**
     * This method start the crow of a website
     *
     * @param criteria
     * @param numPages
     * @throws java.io.IOException
     */
    public void start(StopCriteria criteria, int numPages) throws WebCrawlerException, IOException {

        // Use different ways gettins BFS order
        Iterable<WebPage> it;
        if (null == criteria) {
            throw new WebCrawlerException("Null criteria!");
        } else {
            switch (criteria) {
                case PAGES:
                    it = this.BFSByPages(createWebPage(), numPages);
                    setChanged();
                    notifyObservers();
                    break;
                case DEPTH:
                    it = this.BFSByDepth(createWebPage());
                    setChanged();
                    notifyObservers();
                    break;
                default:
                    it = this.itertive(createWebPage());
                    setChanged();
                    notifyObservers();
                    break;
            }
        }

        print("\n ========= Estatísticas ========= \n");
        print(" »»»»» Páginas Visitadas (%d) ««««« \n\n %s", this.countWebPages(), it);
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
     */
    public int getPagesNotFound(WebPage myWebPage) throws IOException, WebCrawlerException {

        if (myWebPage.getStatusCode() == 404) {
            setChanged();
            notifyObservers();
            return 1;
        }
        return 0;
    }

    /**
     * Enter in link and process all links associated
     *
     * @param webPage WebPage object
     * @param numPages
     * @exception IOException Input Output exception
     * @exception WebCrawlerException Some exception from inputs
     * @return <code>void</code>
     */
    @SuppressWarnings("UnnecessaryReturnStatement")
    public Iterable<WebPage> BFSByPages(WebPage webPage, int numPages)
            throws WebCrawlerException, IOException {

        // Contar numero de WebPages contadas
        int countMaxVisitedPage = 0;
       
        Queue<WebPage> webPagesToVisit = new LinkedList<>();

        if (numPages == 0) {
            setChanged();
            notifyObservers();
            return pagesList;
        }

        if (this.checkIfHasWebPage(webPage) == false) {
            // Insert the webPage in the graph
            graph.insertVertex(webPage);
            setChanged();
            notifyObservers();
        }

        webPagesToVisit.add(webPage);
        pagesList.add(webPage);

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

                if (countMaxVisitedPage == numPages) {
                    return pagesList;
                }

                countHttpsLinks += this.countHttpsProtocols(link.getLinkName());

                // Insert a new WebPage in the graph
                WebPage webPageInserting = new WebPage(link.getLinkName());
                graph.insertVertex(webPageInserting);
                setChanged();
                notifyObservers();

                countPageNotFound += this.getPagesNotFound(webPageInserting);

                pagesList.add(webPageInserting);
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

        return pagesList;
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

    private Iterable<WebPage> itertive(WebPage rootWebPage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet");
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
    
    @Override
    public Memento save(String url) {
        try {
            return new WebCrawlerMemento(graph, countHttpsLinks, countPageNotFound,
                    url, stopCriteriaChoosed, pagesList);
        } catch (IOException ex) {
            LoggerWriter.getInstance().writeToLog(ex.getMessage());
        }
        return null;
    }

    @Override
    public void restore(Memento savedState) {

        WebCrawlerMemento save = (WebCrawlerMemento) savedState;
        this.graph = save.graphMemento;
        this.rootWebPage = save.rootWebPageMemento;

        setChanged();
        notifyObservers();
    }

    private class WebCrawlerMemento implements Memento {

        private String startURLMemento = "";
        private Graph<WebPage, Link> graphMemento;
        private int countHttpsLinksMemento;
        private int countPageNotFoundMemento;
        private WebPage rootWebPageMemento;
        private Date createdAt;
        private List<WebPage> pageListMemento;
        private StopCriteria stopCriteriaChoosed;

        public WebCrawlerMemento(Graph<WebPage, Link> graphMemento,
                int countHttpsLinksMemento, int countPageNotFoundMemento,
                String url, StopCriteria stopCriteriaChoosed, List<WebPage> pageList) throws IOException {
            this.graphMemento = graphMemento;
            this.countHttpsLinksMemento = countHttpsLinksMemento;
            this.countPageNotFoundMemento = countPageNotFoundMemento;
            this.rootWebPageMemento = new WebPage(url);
            this.stopCriteriaChoosed = stopCriteriaChoosed;
            this.pageListMemento = new ArrayList<>(pageList);
            this.createdAt = new Date();

        }

        @Override
        public String getDescription() {
            return String.format("WebCrawler Memento created at %s",
                    createdAt.toString());
        }

    }

}
