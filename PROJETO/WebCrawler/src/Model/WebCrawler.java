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
 * @author BRKsCosta and danielcordeiro
 */
public class WebCrawler extends Observable implements IOriginator, Serializable {

    private final LoggerWriter logger = LoggerWriter.getInstance();

    private ISearchCriteria searchCriteria;

    // Pertinent variables to the DiGraph structure:
    private WebPage rootWebPage;

    // Iterative variables
    private WebPage subRootWebPageChoosed;
    private List<Edge<Link, WebPage>> edgesAdded;
    private List<Vertex<WebPage>> vertexsAdded;

    private WebPage previousSubRootWebPageChoosed; // Still seing if it is needed

    private StopCriteria stopCriteriaChoosed; // PAGES, DEPTH, ITERATIVE.
    private final List<WebPage> pagesList = new ArrayList<>();

    private final Graph<WebPage, Link> graph;

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

    public List<Edge<Link, WebPage>> getEdgesAdded() {
        return edgesAdded;
    }

    public List<Vertex<WebPage>> getVertexsAdded() {
        return vertexsAdded;
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

    public void setSubRootWebPageChoosed(WebPage subRootWebPageChoosed) {
        this.subRootWebPageChoosed = subRootWebPageChoosed;
    }

    public void setEdgesAdded(List<Edge<Link, WebPage>> edgesAdded) {
        this.edgesAdded = edgesAdded;
    }

    public void setVertexsAdded(List<Vertex<WebPage>> vertexsAdded) {
        this.vertexsAdded = vertexsAdded;
    }

    /* NOT BEING USED but should be because of the Strategy pattern
    public void setSearchType(ISearchCriteria criteria) {
        this.searchCriteria = criteria;
        this.start();
    }*/
    // Methods with WebPage's
    public void clearGraph() {
        //this.graph = new MyDigraph<>();

        for (Edge<Link, WebPage> edge : this.graph.edges()) {
            this.graph.removeEdge(edge);
        }

        for (Vertex<WebPage> webPage : this.graph.vertices()) {
            this.graph.removeVertex(webPage);
        }

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

    // Iterative method's
    public void insertNewSubWebPageCrawler(Vertex<WebPage> subRoot) throws IOException {

        this.searchPagesAndPrint(subRoot.element());

        // TODO - notify observers
        setChanged();
        notifyObservers();
    }

    /*public void removeSubWebPageCrawler(WebPage subRootWebPage) {
        // Remove all the subWebCrawler
        this.graph.removeVertex(subRoot);
        
        // TODO - notify observers
        setChanged();
        notifyObservers();
    }*/

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
            return new WebCrawlerMemento(this.subRootWebPageChoosed, this.edgesAdded, this.vertexsAdded);
        } catch (IOException ex) {
            Logger.getLogger(WebCrawler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void restore(IMemento savedState) {

        WebCrawlerMemento save = (WebCrawlerMemento) savedState;

        // Use all the state inside the argument savedState
        //this.subRootWebPageChoosed = save.getRootWebPage();
        this.edgesAdded = save.getEdgesAdded();
        this.vertexsAdded = save.getVertexsAdded();
        this.isFinished = true; // Just for testing, TODO
        
        // Remove the subWebCrawler
        /*for( Vertex<WebPage> webPage : this.graph.vertices()){
            if(webPage.element() == save.getRootWebPage()){
                this.graph.removeVertex(webPage);
            }
        }*/
        
        for(Vertex<WebPage> webPage : save.getVertexsAdded()){
           System.out.println("Vertex a remover -> " + webPage.element().getPersonalURL());
           this.graph.removeVertex(webPage);
        }
        
        for(Edge<Link, WebPage> edge : save.getEdgesAdded()){
           System.out.println("Edge a remover -> " + edge.element().getLinkName());
           this.graph.removeEdge(edge);
        }
        
        setChanged();
        notifyObservers();
    }

    // Private Memento with all the states and getters and setters needed
    private class WebCrawlerMemento implements IMemento {

        // private Graph<WebPage, Link> graphMemento;
        private WebPage rootWebPage;
        private List<Edge<Link, WebPage>> edgesAdded;
        private List<Vertex<WebPage>> vertexsAdded;
        /*private int countHttpsLinksMemento;
        private int countPageNotFoundMemento;*/
        private final Date createdAt;
        //private List<WebPage> pageListMemento;

        public WebCrawlerMemento(WebPage rootWebPage, List<Edge<Link, WebPage>> edgesAdded, List<Vertex<WebPage>> vertexsAdded) throws IOException {
            //this.webPage = new Vertex<>();
            //this.webPage.element() = STILL IN WORK
            //this.graphMemento = new MyDigraph<>();
            //this.graphMemento = graphMemento; // Aqui temos de por o Vertice WebPage. Não vamos puder ter 
            this.rootWebPage = rootWebPage;
            this.edgesAdded = edgesAdded;
            this.vertexsAdded = vertexsAdded;
            this.createdAt = new Date();

            /*this.countHttpsLinksMemento = countHttpsLinksMemento;
            this.countPageNotFoundMemento = countPageNotFoundMemento;
            this.stopCriteriaChoosed = stopCriteriaChoosed;
            this.pageListMemento = new ArrayList<>(pageList);*/
        }

        // Getters
        public WebPage getRootWebPage() {
            return this.rootWebPage;
        }

        public Date getCreatedAt() {
            return this.createdAt;
        }

        public List<Edge<Link, WebPage>> getEdgesAdded() {
            return edgesAdded;
        }

        public List<Vertex<WebPage>> getVertexsAdded() {
            return vertexsAdded;
        }

        /*public int getCountHttpsLinksMemento() {
            return this.countHttpsLinksMemento;
        }
        public int getCountPageNotFoundMemento() {
            return this.countPageNotFoundMemento;
        }
        public List<WebPage> getPageListMemento() {
            return this.pageListMemento;
        }
        public StopCriteria getStopCriteriaChoosed() {
            return this.stopCriteriaChoosed;
        }*/
        @Override
        public String getDescription() {
            return String.format("WebCrawler Memento created at %s with the vertex %s", createdAt.toString(), this.rootWebPage.getPersonalURL());
        }
    }

}