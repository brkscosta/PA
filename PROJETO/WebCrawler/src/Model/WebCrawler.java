package Model;

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
import java.util.logging.Level;
import java.util.logging.Logger;
import Patterns.Memento.IOriginator;
import Patterns.Memento.IMemento;
import Patterns.Stategy.ISearchCriteria;
import Views.HomeView.StopCriteria;

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
public class WebCrawler extends Observable implements IOriginator, Serializable, Cloneable {

    private LoggerWriter logger = LoggerWriter.getInstance();

    private ISearchCriteria searchCriteria; // 
    
    // Pertinent variables to the DiGraph structure:
    private String startURL = ""; // main root url
    public WebPage rootWebPage; // main root WebPage
    private StopCriteria stopCriteriaChoosed; // PAGES, DEPTH, ITERATIVE.
    private List<WebPage> pagesList = new ArrayList<>();
    
    public Graph<WebPage, Link> graph;
    
    // Statistics
    private int countHttpsLinks = 0;
    private int countPageNotFound = 0;
    private int numPages = 0;
    public boolean isFinished = false;

    
    // 2 Constructors
    public WebCrawler(WebCrawler webCrawler){
        this.graph = webCrawler.getGraph();
    }
    public WebCrawler() {
        this(new MyDigraph<>());
    }
    
    // Getters 
    public LoggerWriter getLogger() {
        return logger;
    }
    
    public int getNumPages() {
        return numPages;
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
        for (Vertex<WebPage> v : graph.vertices()) {
            if (v.element().equals(rootWebPage)) {
                return v;
            }
        }
        return null;
    }
    
    public String getStartURL() {
        return startURL;
    }
    
    public Graph<WebPage, Link> getGraph(){
        return this.graph;
    }

    // Setters
    public void setNumPages(int numPages) {
        this.numPages = numPages;
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
    
    public void setStartURL(String startURL) {
        this.startURL = startURL;
    }
    
    public void setSearchType(ISearchCriteria criteria) {
        this.searchCriteria = criteria;
        this.start();
    }

    // Methods with WebPage's
    
    public WebPage createWebPage() throws IOException {
        return new WebPage(startURL);
    }

    public void removePage(Vertex<WebPage> underlyingVertex) {
        graph.removeVertex(underlyingVertex);
        pagesList.remove(underlyingVertex.element());
        isFinished = true;

        setChanged();
        notifyObservers();
    }

    public void buildWebCrawler(String criteria, int numPages){
        // Tentar usar o padrao Template, há codigo repetido e só muda uma linha de código:
            case "BFS":
                model.setStartURL(view.getInputURL());
                model.setRootWebPage(model.createWebPage());
                model.setNumPages(numPages);
                model.setSearchType(new SearchPages(model));
                if(model.getNumPages() > 0)
                    view.setColorRootPage(model.getRootWebPage());
                view.updateGraph();
                break;
            case "DFS":
                model.setStartURL(view.getInputURL());
                model.setRootWebPage(model.createWebPage());
                model.setNumPages(numPages);
                model.setSearchType(new SearchDepth(model));
                //view.setColorRootPage(model.getRootWebPage());
                break;
            default:
                model.setStartURL(view.getInputURL());
                model.setRootWebPage(model.createWebPage());
                model.iterative(model.getRootWebPage().element());
                //view.setColorRootPage(model.getRootWebPage());
                caretaker.requestSave();
                break;
        }
    }
    
    /**
     * This method start the crow of a website
     *
     */
    public void start() {

        Iterable<WebPage> it;

        it = searchCriteria.searchPages(rootWebPage);
        setChanged();
        notifyObservers();

        print("\n ========= Estatísticas ========= \n");
        print(" »»»»» Páginas Visitadas (%d) ««««« \n\n %s", this.countWebPages(), it);
        print(" »»»»» Páginas não encontradas (%d) «««««", this.countPageNotFound);
        print(" »»»»» Ligações HTTPS (%d) «««««", this.countHttpsLinks);
        print(" »»»»» Ligações entre páginas (%d) «««««", this.countLinks());

    }
    
    // Build iterative WebCrawler 
    public Iterable<WebPage> iterative(WebPage rootWebPage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    // Iterative method's
    public void insertNewSubWebPageCrawler(Vertex<WebPage> subRoot){
        // Começar a inserir uma nova sub-árvore apartir deste vértice
        
        
    }

    
    // Necessario este print??????? TODO
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
     * @param graph
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
            return new WebCrawlerMemento(graph, countHttpsLinks, countPageNotFound,
                    stopCriteriaChoosed, pagesList);
        } catch (IOException | CloneNotSupportedException ex) {
            Logger.getLogger(WebCrawler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public void restore(IMemento savedState) {

        WebCrawlerMemento save = (WebCrawlerMemento) savedState;
        
        // Use all the state inside the argument savedState
        this.graph = save.graphMemento;
        this.isFinished = true; // Just for testing, TODO

        setChanged();
        notifyObservers();
    }

    // Private Memento with all the states and getters and setters needed
    private class WebCrawlerMemento implements IMemento {

        //private Graph<WebPage, Link> graphMemento;
        private Vertex<WebPage> webPage;
        private int countHttpsLinksMemento;
        private int countPageNotFoundMemento;
        private Date createdAt;
        private List<WebPage> pageListMemento;
        private StopCriteria stopCriteriaChoosed;

        public WebCrawlerMemento(Vertex<WebPage> webPage,
                int countHttpsLinksMemento, int countPageNotFoundMemento,
                StopCriteria stopCriteriaChoosed, List<WebPage> pageList) throws IOException, CloneNotSupportedException {
            
            //this.webPage = new Vertex<>();
            //this.webPage.element() = STILL IN WORK
            
            //this.graphMemento = new MyDigraph<>();
            //this.graphMemento = graphMemento; // Aqui temos de por o Vertice WebPage. Não vamos puder ter 
            this.countHttpsLinksMemento = countHttpsLinksMemento;
            this.countPageNotFoundMemento = countPageNotFoundMemento;
            this.stopCriteriaChoosed = stopCriteriaChoosed;
            this.pageListMemento = new ArrayList<>(pageList);
            this.createdAt = new Date();
        }
        
        // Getters
        public Graph<WebPage, Link> getGraphMemento() {        
            return graphMemento;
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
            return String.format("WebCrawler Memento created at %s",
                    createdAt.toString());
        }
    }

}
