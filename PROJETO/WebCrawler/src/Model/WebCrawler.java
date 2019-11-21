package Model;

import Graph.Edge;
import java.io.IOException;
import Graph.Graph;
import Graph.GraphEdgeList;
import Graph.Vertex;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

@SuppressWarnings("null")
/**
 * Model to be created to build de graph <code>Vertex</code>
 * {@link Graph.Vertex} is the type WebPage and <code>Edge</code>
 * {@link Graph.Edge} is the type of Link
 *
 * @author BRKsCosta
 */
public class WebCrawler {

    private String startURL = "";
    private final Graph<WebPage, Link> webCrawler;
    private WebPage webPage;
    private int numLinks = 0;

    /**
     *
     * Create a object of <i><p> Web Crawler </p></i> type with a DiGraph instance
     *s
     * @param string Base URL
     * @param numberOfLinks Max number of link requested by user
     */
    public WebCrawler(String string, int numberOfLinks) {
        this.startURL = string;
        this.webCrawler = new GraphEdgeList();
        this.webPage = new WebPage(string);
        this.numLinks = numberOfLinks;
    }

    /**
     * Check a title if exists on graph
     *
     * @param title title to be searched
     * @return Element of type <code>Link></code>
     * @throws WebCrawlerException Launch exception case some parameters are bad
     */
    private WebPage findWebPage(WebPage webpage) throws WebCrawlerException {
        if (webpage == null) {
            throw new WebCrawlerException("Title cannot be null");
        }

        WebPage find = null;
        for (Vertex<WebPage> v : webCrawler.vertices()) {
            if (v.element().equals(webpage)) { //equals was overriden in Airport!!
                find = v.element();
            }
        }

        if (find == null) {
            throw new WebCrawlerException("WebPage with code ("
                    + webpage.getTitleName() + ") does not exist");
        }

        return find;
    }

    /**
     * Check if exists a link on graph
     *
     * @param link The link (URL)
     * @return True if exists false otherwise
     * @throws WebCrawlerException
     */
    private boolean isExistsLink(Link link) throws WebCrawlerException {
        if (link == null) {
            throw new WebCrawlerException("Object null");
        }

        Link find = null;
        //equals was overriden in Airport!!
        //find = v.element();
        return webCrawler.edges().stream().anyMatch((v) -> (v.element().equals(link)));
    }

    /**
     * This method start the crow of a website
     *
     * @throws Model.WebCrawlerException To validate some bad inputs outputs
     * @throws java.io.IOException
     */
    public void start() throws WebCrawlerException, IOException {
        System.out.println("Print método start: \n\n" + this.BFS(webPage));
    }
    
    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
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

        //Contar máximo de links
        int countMaxLinks = 0;

        //Insere página no grafo
        this.insertWebPage(webPage);

        List<WebPage> output = new ArrayList<>();

        Set<WebPage> visited = new HashSet<>();
        Queue<WebPage> queue = new LinkedList<>();

        // Get all links
        Queue<Link> allIncidentWebPages = webPage.getAllIncidentWebPages(this.startURL);
        countMaxLinks = allIncidentWebPages.size();
        
        output.add(webPage);
        visited.add(webPage);
        queue.add(webPage);

        if (countMaxLinks >= this.numLinks) {
            return output;
        }

        // Array para adicionar a uma lista de links já visitados
        List<Link> visitedIncidentLinks = new ArrayList();

        while (!allIncidentWebPages.isEmpty()) {

            //Entra nos links associados a essa página
            Link removedLinkToEnter = allIncidentWebPages.remove();
            System.out.println("Link da próxima página a retirar: " + removedLinkToEnter.getLinkName());

            // Adiciona a lista de links já visitados
            visitedIncidentLinks.add(removedLinkToEnter);

            // Cria um novo Vertex
            WebPage newGeneretedVertex = new WebPage(removedLinkToEnter.getLinkName());
            insertWebPage(newGeneretedVertex);
            //output.add(newGeneretedVertex);
            
            // Faz ligação entre as WebPages
            webCrawler.insertEdge(webPage, newGeneretedVertex, removedLinkToEnter);
            
            // Começa novo processo de geração de novas páginas
            Queue<Link> processedLink = newGeneretedVertex.getAllIncidentWebPages(newGeneretedVertex.getPersonalURL());
            countMaxLinks += processedLink.size();
            print("Links dessa Página: %d "
                    + "\n Geração de links novo vertice: %s", processedLink.size(), processedLink);
            
            if(countMaxLinks >= this.numLinks)
                return output;
            
            // Adiciona nova WebPage gerada a fila
            queue.offer(newGeneretedVertex);
            
            //Adicionar a lista de outputs
            output.add(newGeneretedVertex);
            
            // Remove o objeto WebPage da fila
            queue.poll();
            
        }

        return output;
    }

    private void insertWebPage(WebPage webPage) throws WebCrawlerException {

        if (webPage == null) {
            throw new WebCrawlerException("Cannot be null");
        }

//        if (findWebPage(webPage) != null) {
//            throw new WebCrawlerException("Page alredy exists!");
//        }
        webCrawler.insertVertex(webPage);

    }

    /**
     * Counter of links
     *
     * @return Number of links (Edges)
     */
    public int countLinks() {
        // TESTING
        return webCrawler.numEdges();
    }

    /**
     * Count titles from a specifc website
     *
     * @return Number of titles (Vertex)
     */
    public int countTitles() {
        return webCrawler.numVertices();
    }

    /**
     * Get a link between two a given titles
     *
     * @param title First title
     * @param title2 Second title
     * @return list of links between two titles
     */
    private List<Link> getLinkBetween(WebPage title, WebPage title2)
            throws WebCrawlerException {

        if (title == null || title2 == null) {
            throw new WebCrawlerException("Title cannot be null");
        }

        WebPage a1 = findWebPage(title);
        WebPage a2 = findWebPage(title2);

        List<Link> links = new ArrayList<>();

        try {
            for (Edge<Link, WebPage> edge : webCrawler.edges()) {
                if (title == a1 && title2 == a2) {
                    Link element = edge.element();
                    links.add(element);
                }
            }
            return links;
        } catch (Exception e) {
            throw new WebCrawlerException(e.getMessage());
        }

    }
  
}
