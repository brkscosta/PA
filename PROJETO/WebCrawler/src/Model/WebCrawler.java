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
import java.util.logging.Level;
import java.util.logging.Logger;
import Patterns.Memento.IMemento;
import Patterns.Memento.IOriginator;
import Patterns.Stategy.ISearchCriteria;
import Patterns.Stategy.SearchDepth;
import Patterns.Stategy.SearchIterative;
import Patterns.Stategy.SearchPages;
import Views.HomeView.StopCriteria;
import java.util.List;

@SuppressWarnings("null")
/**
 * Model to be created to build de graph Link <code>Vertex</code> WebPage and
 * <code>Edge</code> Use the methods setChanged() and notifyObservers().
 *
 * @author BRKsCosta and danicelcordeiro
 */
public class WebCrawler extends Observable implements IOriginator, Serializable {

    private LoggerWriter logger = LoggerWriter.getInstance();

    private ISearchCriteria searchCriteria; //

    // Pertinent variables to the DiGraph structure:
    private WebPage rootWebPage;
    private Vertex<WebPage> rootVertex;
    private StopCriteria stopCriteriaChoosed; 
    private List<WebPage> pagesList = new ArrayList<>();
    private List<Edge<Link, WebPage>> pageLinks;
    private Graph<WebPage, Link> graph;

    // Statistics
    private int countHttpsLinks = 0;
    private int countPageNotFound = 0;
    private int numCriteria = 0;
    public boolean isFinished = false;

    // 2 Constructors
    public WebCrawler(Graph graph) {
        this.graph = graph;
        pageLinks = new ArrayList<>();
    }

    public WebCrawler() {
        this(new MyDigraph<>());
        pageLinks = new ArrayList<>();
    }

    // Getters
    public LoggerWriter getLogger() {
        return logger;
    }

    public List<Edge<Link, WebPage>> getAllLinks() {
        
        for (Edge<Link, WebPage> edge : graph.edges()) {
            pageLinks.add(edge);
        }
        
        return pageLinks;
    }

    public List<WebPage> getPagesList() {
        return pagesList;
    }

    /**
     * Get the number criteria
     *
     * @return A number
     */
    public int getNumCriteria() {
        return numCriteria;
    }

    /**
     * Get the number of pages HTTPS links founded.
     *
     * @return A number
     */
    public int getCountHttpsLinks() {
        return countHttpsLinks;
    }

    /**
     * Get the number of pages not found
     *
     * @return A number
     */
    public int getCountPageNotFound() {
        return countPageNotFound;
    }

    /**
     * Get the stop criteria.
     *
     * @return Return a ENUM.
     */
    public StopCriteria getStopCriteriaChoosed() {
        return stopCriteriaChoosed;
    }

    /**
     * Get the root WebPage on the graph
     *
     * @return A vertex of the type WebPage.
     */
    public Vertex<WebPage> getRootWebPage() {
        for (Vertex<WebPage> v : graph.vertices()) {
            if (v.element().equals(this.rootWebPage)) {
                return v;
            }
        }
        return null;
    }

    /**
     * Get a instance of the graph
     *
     * @return A instance of the graph
     */
    public Graph<WebPage, Link> getGraph() {
        return this.graph;
    }

    // Setters
    /**
     * Set the number of web pages to how criteria
     *
     * @param numCriteria A number
     */
    public void setNumCriteria(int numCriteria) {
        this.numCriteria = numCriteria;
    }

    /**
     * Set the count of HTTPS pages
     *
     * @param countHttpsLinks A number
     */
    public void setCountHttpsLinks(int countHttpsLinks) {
        this.countHttpsLinks = countHttpsLinks;
    }

    /**
     * Set the pages not found
     *
     * @param countPageNotFound A number
     */
    public void setCountPageNotFound(int countPageNotFound) {
        this.countPageNotFound = countPageNotFound;
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

    /**
     * Set the root vertex from the graph
     *
     * @param rootVertex A vertex
     */
    public void setRootVertex(Vertex<WebPage> rootVertex) {
        this.rootVertex = rootVertex;
    }

    /**
     * Clear the graph instance
     */
    public void clearGraph() {
        this.graph = new MyDigraph<>();
        this.isFinished = true;

        setChanged();
        notifyObservers(this.graph);
    }

    /**
     * Remove a specific webpage on the graph
     *
     * @param underlyingVertex A vertex.
     */
    public void removePage(Vertex<WebPage> underlyingVertex) {
        graph.removeVertex(underlyingVertex);
        pagesList.remove(underlyingVertex.element());
        isFinished = true;

        setChanged();
        notifyObservers(this.graph);
    }

    /**
     * This method is responsible to select a type of search to the WebPages
     *
     * @param criteria The type of search
     * @param numCriteria The stop criteria
     * @param inputUrl The page to search
     * @throws IOException
     */
    public void buildWebCrawler(StopCriteria criteria, int numCriteria, String inputUrl) throws IOException {

        // Assign values
        this.numCriteria = numCriteria;

        if (this.numCriteria != 0) {
            this.rootWebPage = new WebPage(inputUrl);
            this.graph.insertVertex(this.rootWebPage);
        }

        switch (criteria) {
            case PAGES:
                this.searchCriteria = new SearchPages(this);
                break;
            case DEPTH:
                this.searchCriteria = new SearchDepth(this);
                break;
            default: // Iterative
                this.searchCriteria = new SearchIterative(this);
                break;
        }

        this.searchPagesAndPrint(this.rootWebPage);
    }

    /**
     * The aux method to iterative mode that insert the new web pages when the
     * user click.
     *
     * @param subRoot The parent node
     * @throws IOException
     */
    public void insertNewSubWebPageCrawler(Vertex<WebPage> subRoot) throws IOException {
        // Começar a inserir uma nova sub-árvore apartir deste vértice

        /*Vertex<WebPage> subRootFound = null;
        
        // Find the subRoot vertex inside the graph. It will always find because the event happened
        for(Vertex<WebPage> vertex : this.graph.vertices()){
            if(vertex.element() == subRoot.element()){
                subRootFound = vertex;
            }
        }*/
        this.searchPagesAndPrint(subRoot.element());

        // notify observers
        setChanged();
        notifyObservers();
    }

    /**
     * This method helps to see the pages that was generated.
     *
     * @param rootWebPage A object of the type WebPage
     */
    private void searchPagesAndPrint(WebPage rootWebPage) {
        Iterable<WebPage> it = searchCriteria.searchPages(rootWebPage);

        print("\n ========= Estatísticas ========= \n");
        print(" »»»»» Páginas Visitadas (%d) ««««« \n\n %s", this.countWebPages(), it);
        print(" »»»»» Páginas não encontradas (%d) «««««", this.countPageNotFound);
        print(" »»»»» Ligações HTTPS (%d) «««««", this.countHttpsLinks);
        print(" »»»»» Ligações entre páginas (%d) «««««", this.countLinks());

        setChanged();
        notifyObservers();
    }

    /**
     * This method is to print in a specif format.
     *
     * @param msg The string
     * @param args Multiple type and parameters.
     */
    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    /**
     * Count HTTPS protocols
     *
     * @param startURL site URL
     * @return Number of pages founded
     * @throws MalformedURLException
     */
    public int countHttpsProtocols(String startURL) throws MalformedURLException {
        int count = 0;
        URL u = new URL(startURL);
        if (u.getProtocol().equals("https")) {
            count++;
        }
        return count;
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
            return 1;
        }
        return 0;
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

    // Implement all IOriginator methods
    @Override
    public IMemento save() {
        // Creates a new private Memento Object and returns it
        try {
            return new WebCrawlerMemento(this.rootWebPage, countHttpsLinks, countPageNotFound, stopCriteriaChoosed,
                    pagesList);
        } catch (IOException ex) {
            Logger.getLogger(WebCrawler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public void restore(IMemento savedState) {

        WebCrawlerMemento save = (WebCrawlerMemento) savedState;

        // Use all the state inside the argument savedState
        this.rootWebPage = save.getRootWebPage();
        this.isFinished = true; // Just for testing, TODO

        setChanged();
        notifyObservers();
    }

    public void exportFile(String fileType) {
        System.out.println(getAllLinks());
    }

    // Private Memento with all the states and getters and setters needed
    private class WebCrawlerMemento implements IMemento {

        // private Graph<WebPage, Link> graphMemento;
        private WebPage rootWebPage;
        private int countHttpsLinksMemento;
        private int countPageNotFoundMemento;
        private Date createdAt;
        private List<WebPage> pageListMemento;
        private StopCriteria stopCriteriaChoosed;

        public WebCrawlerMemento(WebPage rootWebPage,
                int countHttpsLinksMemento, int countPageNotFoundMemento,
                StopCriteria stopCriteriaChoosed, List<WebPage> pageList) throws IOException {

            //this.webPage = new Vertex<>();
            //this.webPage.element() = STILL IN WORK
            //this.graphMemento = new MyDigraph<>();
            //this.graphMemento = graphMemento; // Aqui temos de por o Vertice WebPage. Não vamos puder ter 
            this.rootWebPage = rootWebPage;
            this.countHttpsLinksMemento = countHttpsLinksMemento;
            this.countPageNotFoundMemento = countPageNotFoundMemento;
            this.stopCriteriaChoosed = stopCriteriaChoosed;
            this.pageListMemento = new ArrayList<>(pageList);
            this.createdAt = new Date();
        }

        // Getters
        public WebPage getRootWebPage() {
            return this.rootWebPage;
        }

        public int getCountHttpsLinksMemento() {
            return countHttpsLinksMemento;
        }

        public int getCountPageNotFoundMemento() {
            return countPageNotFoundMemento;
        }

        public Date getCreatedAt() {
            return createdAt;
        }

        public List<WebPage> getPageListMemento() {
            return pageListMemento;
        }

        public StopCriteria getStopCriteriaChoosed() {
            return stopCriteriaChoosed;
        }

        @Override
        public String getDescription() {
            return String.format("WebCrawler Memento created at %s", createdAt.toString());
        }
    }

}
