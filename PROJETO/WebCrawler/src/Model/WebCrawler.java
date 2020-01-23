package Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.brunomnsilva.smartgraph.graph.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Observable;
import Patterns.Singleton.LoggerWriter;
import java.util.Date;
import java.io.Serializable;
import Patterns.Memento.IMemento;
import Patterns.Memento.IOriginator;
import Patterns.Stategy.ISearchCriteria;
import Patterns.Stategy.SearchDepth;
import Patterns.Stategy.SearchExpandedPages;
import Patterns.Stategy.SearchIterative;
import Patterns.Stategy.SearchPages;
import Views.HomeView.StopCriteria;

/**
 * This class is responsible for creating our WebCrawler model based on the
 * implemented MyDigraph class, where the vertices are represented by the
 * concrete web page (class WebPage) and the parts by a link on page (class
 * Link).
 *
 * @see Model.Link
 * @see Model.WebPage
 *
 * @author BRKsCosta and danielcordeiro
 */
public class WebCrawler extends Observable implements IOriginator, Serializable {

    @SuppressWarnings("null")

    //<editor-fold defaultstate="collapsed" desc="Variables">
    private final LoggerWriter logger = LoggerWriter.getInstance();
    private ISearchCriteria searchCriteria;
    private WebPage rootWebPage;

    // Iterative variables
    private WebPage subRootWebPageChoosed;
    private WebPage previousSubRootWebPageChoosed; // Still seing if it is needed

    private StopCriteria stopCriteriaChoosed; // PAGES, DEPTH, Extended,ITERATIVE.

    private final List<WebPage> pagesList = new ArrayList<>();
    private final Graph<WebPage, Link> graph;

    // Statistics
    Statistics statistcs;
    
    public boolean isFinished = false;
    private int numCriteria = 0;

//</editor-fold>
    public WebCrawler(Graph graph) {
        this.graph = graph;
        this.statistcs = new Statistics();
    }

    public WebCrawler() {
        this(new MyDigraph<>());
    }

    // <editor-fold defaultstate="collapsed" desc=" Getters ">

    public Statistics getStatistcs() {
        return statistcs;
    }
    
    /**
     * Get a instance of the logger
     *
     * @return A Logger object
     */
    public LoggerWriter getLogger() {
        return logger;
    }

    /**
     * Get all links
     *
     * @return A collection of edges
     */
    public List<Edge<Link, WebPage>> getAllLinks() {
        return (List<Edge<Link, WebPage>>) graph.edges();
    }

    /**
     * Get page list
     *
     * @return A list of pages
     */
    public List<WebPage> getPagesList() {
        return pagesList;
    }

    /**
     * Get the number of pages
     *
     * @return A number
     */
    public int getNumCriteria() {
        return numCriteria;
    }

    /**
     * Get stop criteria
     *
     * @return
     */
    public StopCriteria getStopCriteriaChoosed() {
        return stopCriteriaChoosed;
    }

    /**
     * Get root web page
     *
     * @return The root web page
     */
    public Vertex<WebPage> getRootWebPage() {

        for (Vertex<WebPage> v : graph.vertices()) {
            if (v.element().equals(this.rootWebPage)) {
                return v;
            }
        }
        return null;
    }

    public Graph<WebPage, Link> getGraph() {
        return this.graph;
    }

// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Setters ">
    /**
     * Set the number of web pages to how criteria
     *
     * @param numCriteria A number
     */
    public void setNumCriteria(int numCriteria) {
        this.numCriteria = numCriteria;
    }

    /**
     * Set sub root pages choosed
     *
     * @param subRootWebPageChoosed A WebPage object
     */
    public void setSubRootWebPageChoosed(WebPage subRootWebPageChoosed) {
        this.subRootWebPageChoosed = subRootWebPageChoosed;
    }

    /**
     * Set a stop criteria
     *
     * @param stopCriteriaChoosed A ENUM of the type StopCriteria
     */
    public void setStopCriteriaChoosed(StopCriteria stopCriteriaChoosed) {
        this.stopCriteriaChoosed = stopCriteriaChoosed;
    }

    /**
     * Set the root webpage
     *
     * @param rootWebPage A object of the type webpage
     */
    public void setRootWebPage(WebPage rootWebPage) {
        this.rootWebPage = rootWebPage;
    }

// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Methods ">
    
    /**
     * Clear the all elements in the graph.
     */
    public void clearGraph() {

        this.graph.edges().forEach((edge) -> {
            this.graph.removeEdge(edge);
        });

        this.graph.vertices().forEach((webPage) -> {
            this.graph.removeVertex(webPage);
        });

        this.isFinished = true;

        setChanged();
        notifyObservers();
    }

    /**
     * Insert new WebPage on the graph
     *
     * @param webPage WebPage Object
     */
    public void insertPage(WebPage webPage) {
        this.graph.insertVertex(webPage);
    }
    
    /**
     * This method add's a new WebPage to the pageList attribute
     * @param webPage 
     */
    public void insertInPageList(WebPage webPage){
        this.pagesList.add(webPage);
    }
    
    /**
     * Insert new link between to the current webpage to another
     * @param visitedWebPage Current webpage
     * @param webPageInserting The incident webpage
     * @param link The link that connect the both
     */
    public void insertLink(WebPage visitedWebPage, WebPage webPageInserting, Link link) {
        this.graph.insertEdge(visitedWebPage, webPageInserting, link);
    }

    /**
     * This method is responsible to select a type of search to the WebPages
     *
     * @param criteria The type of search
     * @param numCriteria The stop criteria
     * @param inputUrl The page to search
     */
    public void buildWebCrawler(StopCriteria criteria, int numCriteria, String inputUrl) {

        // Assign values
        this.setNumCriteria(numCriteria);

        if (numCriteria != 0) {
            this.rootWebPage = new WebPage(inputUrl);
            this.insertPage(this.rootWebPage);
        }

        switch (criteria) {
            case PAGES:
                this.searchCriteria = new SearchPages(this);
                break;
            case DEPTH:
                this.searchCriteria = new SearchDepth(this);
                break;
            case EXPANDED:
                this.searchCriteria = new SearchExpandedPages(this);
                break;
            default: // Iterative
                this.searchCriteria = new SearchIterative(this);
                break;
        }

        this.printStatistics(this.rootWebPage);
    }

    /**
     * The aux method to iterative mode that insert the new web pages when the
     * user click.
     *
     * @param subRoot The parent node
     * @throws IOException
     */
    public void insertNewSubWebPageCrawler(Vertex<WebPage> subRoot) throws IOException {

        this.printStatistics(subRoot.element());

        setChanged();
        notifyObservers();
    }

    /**
     * This method helps to see the pages that was generated.
     *
     * @param rootWebPage A object of the type WebPage
     */
    private void printStatistics(WebPage rootWebPage) {
        Iterable<WebPage> it = searchCriteria.searchPages(rootWebPage);

        print("\n ========= Estatísticas ========= \n");
        print(" »»»»» Páginas Visitadas (%d) ««««« \n\n %s", this.countWebPages(), it);
        print(" »»»»» Páginas não encontradas (%d) «««««", this.statistcs.getCountPageNotFound());
        print(" »»»»» Ligações HTTPS (%d) «««««", this.statistcs.getCountHttpsLinks());
        print(" »»»»» Ligações entre páginas (%d) «««««", this.countLinks());

        setChanged();
        notifyObservers();
    }

    /**
     * This method prints an output
     * @param msg
     * @param args 
     */
    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    /**
     * Count HTTPS protocols
     *
     * @param startURL site URL
     * @throws MalformedURLException
     */
    public void countHttpProtocols(String startURL) throws MalformedURLException {
        URL u = new URL(startURL);
        if (u.getProtocol().equals("http")) {
            statistcs.incrementHttpsLinks();
        }
    }

    /**
     * Count number of pages not found
     *
     * @param myWebPage
     */
    public void getPagesNotFound(WebPage myWebPage) {

        if (myWebPage.getStatusCode() == 404) {
            statistcs.incrementPageNotFound();
        }
    }

    /**
     * Checks if exists already a webPage like the parameter inside the webPage
     *
     * @param webPage we want to check
     * @return if exists the webPage
     */
    public boolean checkIfHasWebPage(WebPage webPage) {
        for (Vertex<WebPage> page : graph.vertices()) {
            if (page.element() == webPage) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get if the link is equal the link web page
     *
     * @param linkName
     * @return
     */
    public Vertex<WebPage> getEqualWebPageVertex(String linkName) {
        for (Vertex<WebPage> page : graph.vertices()) {
            if (page.element().getPersonalURL().equals(linkName)) {
                return page;
            }
        }
        return null;
    }

    /**
     * Counter of links
     *
     * @return Number of links (Edges)
     */
    public int countLinks() {
        this.statistcs.setCountLinks(graph.numEdges());
        return graph.numEdges();
    }

    /**
     * Count titles from a specific website
     *
     * @return Number of titles (Vertex)
     */
    public int countWebPages() {
        this.statistcs.setCountWebPages(graph.numVertices());
        return graph.numVertices();
    }

    @Override
    public IMemento save() {
        // Creates a new private Memento Object and returns it
        return new WebCrawlerMemento(this.graph, this.rootWebPage);
    }

    @Override
    public void restore(IMemento savedState) {

        WebCrawlerMemento save = (WebCrawlerMemento) savedState;

        this.clearGraph();

        for (Vertex<WebPage> webPage : save.getGraph().vertices()) {
            System.out.println("Vertex a inserir -> " + webPage.element().getPersonalURL());
            this.graph.insertVertex(webPage.element());
        }

        for (Edge<Link, WebPage> edge : save.getGraph().edges()) {
            System.out.println("Edge a inserir -> " + edge.element().getLinkName());
            this.graph.insertEdge(edge.vertices()[0], edge.vertices()[1], edge.element());
        }

        this.isFinished = true; // Just for testing, TODO

        setChanged();
        notifyObservers();

    }

    // </editor-fold>
    
    /**
     * Private inner class to implement the memento
     */
    private class WebCrawlerMemento implements IMemento {

        private WebPage rootWebPage;
        private final Graph<WebPage, Link> graph;
        private final Date createdAt;

        public WebCrawlerMemento(Graph<WebPage, Link> graph, WebPage rootWebPage) {

            this.graph = new MyDigraph<>();

            graph.vertices().forEach((vertex) -> {
                this.graph.insertVertex(vertex.element());
            });

            graph.edges().forEach((edge) -> {
                this.graph.insertEdge(edge.vertices()[0], edge.vertices()[1], edge.element());
            });

            this.rootWebPage = rootWebPage;
            this.createdAt = new Date();
        }

        // Getters
        /**
         * Get the root web page
         *
         * @return
         */
        public WebPage getRootWebPage() {
            return this.rootWebPage;
        }

        /**
         * Get the create date
         *
         * @return
         */
        public Date getCreatedAt() {
            return this.createdAt;
        }

        public Graph<WebPage, Link> getGraph() {
            return this.graph;
        }

        @Override
        public String getDescription() {
            return String.format("WebCrawler Memento created at %s with the "
                    + "vertex %s", createdAt.toString(),
                    this.rootWebPage.getPersonalURL());
        }
    }

}
