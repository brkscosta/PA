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
import Patterns.Stategy.IBreakCriteria;
import java.util.Date;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import Patterns.Memento.IOriginator;
import Patterns.Memento.IMemento;

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

    private IBreakCriteria searchCriteria; // 
    
    private String startURL = ""; // main root url
    public Graph<WebPage, Link> graph;
    private int countHttpsLinks;
    private int countPageNotFound;
    public WebPage rootWebPage;
    private StopCriteria stopCriteriaChoosed;
    private List<WebPage> pagesList = new ArrayList<>();
    private int numPages = 0;
    public boolean isFinished = false;

    public WebCrawler() {
        this.countHttpsLinks = 0;
        this.countPageNotFound = 0;
        this.graph = new MyDigraph<>();
    }

    public enum StopCriteria {
        PAGES, DEPTH, ITERATIVE;
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
    
    public void setSearchType(IBreakCriteria criteria) {
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


    /**
     * This method start the crow of a website
     *
     */
    public void start() {

        Iterable<WebPage> it;

        it = searchCriteria.serchPages(rootWebPage);
        setChanged();
        notifyObservers();

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

    public Iterable<WebPage> itertive(WebPage rootWebPage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            
            this.webPage = new Vertex<>();
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
