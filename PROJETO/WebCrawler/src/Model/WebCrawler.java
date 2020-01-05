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
import Views.HomeView;
import Views.HomeView.StopCriteria;
import java.util.Collection;

@SuppressWarnings("null")
/**
 * Model to be created to build de graph Link <code>Vertex</code> WebPage and
 * <code>Edge</code> Use the methods setChanged() and notifyObservers()
 *
 * @author BRKsCosta and Daniel Cordeiro
 */
public class WebCrawler extends Observable implements IOriginator, Serializable {

    private LoggerWriter logger = LoggerWriter.getInstance();

    private ISearchCriteria searchCriteria; //

    // Pertinent variables to the DiGraph structure:
    private WebPage rootWebPage;
    private Vertex<WebPage> rootVertex;
    private StopCriteria stopCriteriaChoosed; // PAGES, DEPTH, ITERATIVE.
    private List<WebPage> pagesList = new ArrayList<>();

    private Graph<WebPage, Link> graph;

    // Statistics
    private int countHttpsLinks = 0;
    private int countPageNotFound = 0;
    private int numCriteria = 0;
    public boolean isFinished = false;

    // 2 Constructors
    public WebCrawler(Graph graph) {
        this.graph = graph;
    }

    public WebCrawler() {
        this(new MyDigraph<>());
    }

    // Getters
    public LoggerWriter getLogger() {
        return logger;
    }

    public Collection<Edge<Link, WebPage>> getAllLinks() {
        return graph.edges();
    }

    public List<WebPage> getPagesList() {
        return pagesList;
    }

    public int getNumCriteria() {
        return numCriteria;
    }

    public int getCountHttpsLinks() {
        return countHttpsLinks;
    }

    public int getCountPageNotFound() {
        return countPageNotFound;
    }

    public StopCriteria getStopCriteriaChoosed() {
        return stopCriteriaChoosed;
    }

    public Vertex<WebPage> getRootWebPage() {
        System.out.println("TESTEEEEEE -> " + graph);
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

    // Setters
    public void setNumCriteria(int numCriteria) {
        this.numCriteria = numCriteria;
    }

    public void setCountHttpsLinks(int countHttpsLinks) {
        this.countHttpsLinks = countHttpsLinks;
    }

    public void setCountPageNotFound(int countPageNotFound) {
        this.countPageNotFound = countPageNotFound;
    }

    public void setStopCriteriaChoosed(StopCriteria stopCriteriaChoosed) {
        this.stopCriteriaChoosed = stopCriteriaChoosed;
    }

    public void setRootWebPage(WebPage rootWebPage) {
        this.rootWebPage = rootWebPage;
    }

    public void setRootVertex(Vertex<WebPage> rootVertex) {
        this.rootVertex = rootVertex;
    }

    /* NOT BEING USED
    public void setSearchType(ISearchCriteria criteria) {
        this.searchCriteria = criteria;
        this.start();
    }*/

    // Methods with WebPage's
    public void clearGraph() {
        this.graph = new MyDigraph<>();
        this.isFinished = true;

        setChanged();
        notifyObservers(this.graph);
    }

    public void removePage(Vertex<WebPage> underlyingVertex) {
        graph.removeVertex(underlyingVertex);
        pagesList.remove(underlyingVertex.element());
        isFinished = true;

        setChanged();
        notifyObservers(this.graph);
    }

    public void buildWebCrawler(StopCriteria criteria, int numCriteria, String inputUrl) throws IOException {

        // Assign values
        this.numCriteria = numCriteria;
        
        if(this.numCriteria != 0){
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
    
    // Iterative method's
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

        // TODO - notify observers
        setChanged();
        notifyObservers();
    }
    
    private void searchPagesAndPrint(WebPage rootWebPage){
        Iterable<WebPage> it = searchCriteria.searchPages(rootWebPage);
        
        print("\n ========= Estatísticas ========= \n");
        print(" »»»»» Páginas Visitadas (%d) ««««« \n\n %s", this.countWebPages(), it);
        print(" »»»»» Páginas não encontradas (%d) «««««", this.countPageNotFound);
        print(" »»»»» Ligações HTTPS (%d) «««««", this.countHttpsLinks);
        print(" »»»»» Ligações entre páginas (%d) «««««", this.countLinks());

        setChanged();
        notifyObservers();
    }
    
    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    /**
     * Count https protocols
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
     * Checks if exists already a webPage like the param inside the webPage
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
    
    public Vertex<WebPage> getEqualWebPageVertex(String linkName){
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
