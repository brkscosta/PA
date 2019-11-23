package Model;

import Graph.Edge;
import java.io.IOException;
import Graph.Graph;
import Graph.MyDiGraph;
import Graph.Vertex;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private int numPages = 0;

    /**
     *
     * Create a object of <i><p>
     * Web Crawler </p></i> type with a DiGraph instance s
     *
     * @param string Base URL
     * @param numberOfLinks Max number of link requested by user
     */
    public WebCrawler(String string, int numberOfLinks) throws IOException {
        this.startURL = string;
        this.webCrawler = new MyDiGraph();
        this.webPage = new WebPage(string);
        this.numPages = numberOfLinks;
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
     * This method start the crow of a website
     *
     * @throws Model.WebCrawlerException To validate some bad inputs outputs
     * @throws java.io.IOException
     */
    public void start() throws WebCrawlerException, IOException {
        Iterable<WebPage> BFS = this.BFS(webPage);
        print("\n ========= Estatísticas ========= \n");
        print(" »»»»» Páginas Visitadas (%d) ««««« \n\n %s", this.countWebPages(), BFS);
        print(" »»»»» Páginas não encontradas (%d) «««««", this.getPagesNotFound(BFS.iterator().next()));
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

        int count = 0;

        for (Vertex<WebPage> page : webCrawler.vertices()) {
            if (page.element().getStatusCode() == 404) {
                count++;
            }
        }
        return count;
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

        //Contar máximo de WebPages
        int countMaxVisitedPage = 0;

        //Insere página no grafo
        this.insertWebPage(webPage);

        List<WebPage> output = new ArrayList<>();

        Set<WebPage> visited = new HashSet<>();
        Queue<WebPage> queue = new LinkedList<>();

        // Get all links
        Queue<Link> allIncidentWebPages = webPage.getAllIncidentWebPages(webPage.getPersonalURL());
        System.out.println("Links da página root: " + webPage.getPersonalURL()
                + " \n" + allIncidentWebPages);
        output.add(webPage);
        visited.add(webPage);
        queue.add(webPage);

        // Array para adicionar a uma lista de links já visitados
        List<Link> visitedIncidentLinks = new ArrayList();

        while (!allIncidentWebPages.isEmpty()) {

            //Entra nos links associados a essa página
            Link removedLinkToEnter = allIncidentWebPages.poll();
            System.out.println("Link da próxima página a retirar: " + removedLinkToEnter.getLinkName());

            // Adiciona a lista de links já visitados
            visitedIncidentLinks.add(removedLinkToEnter);

            // Cria um novo Vertex
            WebPage newGeneretedVertex = new WebPage(removedLinkToEnter.getLinkName());
            insertWebPage(newGeneretedVertex);

            // Faz ligação entre as WebPages
            webCrawler.insertEdge(webPage, newGeneretedVertex, removedLinkToEnter);

            // Começa novo processo de geração de novas páginas
            Queue<Link> processedLink = newGeneretedVertex.getAllIncidentWebPages(newGeneretedVertex.getPersonalURL());
            print("Links dessa Página: %d "
                    + "\n Geração de links novo vertice: %s", processedLink.size(), processedLink);

            // Adiciona nova WebPage gerada a fila
            queue.offer(newGeneretedVertex);

            //Adicionar a lista de outputs
            output.add(newGeneretedVertex);
            countMaxVisitedPage = output.size();

            if (countMaxVisitedPage > this.numPages) {
                return output;
            }

            // Remove o objeto WebPage da fila
            queue.poll();

        }

        return output;
    }

    /**
     * Just insert a WebPage on the graph
     *
     * @param webPage
     * @throws WebCrawlerException
     */
    public void insertWebPage(WebPage webPage) throws WebCrawlerException {

        if (webPage == null) {
            throw new WebCrawlerException("Cannot be null");
        }
        webCrawler.insertVertex(webPage);
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
