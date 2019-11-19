/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Graph.Edge;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import Graph.Graph;
import Graph.GraphEdgeList;
import Graph.Vertex;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Connection.Response;

/**
 *
 * @author BRKsCosta
 */
public class WebCrawler {

    private String start_url = "";
    private int numPages = 0;
    private final Graph<Title, Link> graph;
    
    public enum Criteria {
        MAX_PAGES;
        
        public int getUnit(int maxPages) {
            switch (this) {
                case MAX_PAGES:
                    return maxPages;
            }
            return 0;
        }
    };

    public WebCrawler(String string, int numPages) {
        this.start_url = string;
        this.graph = new GraphEdgeList();
        this.numPages = numPages;
    }

    /**
     * Check a title if exists on graph
     *
     * @param title title to be searched
     * @return Element of type <code>Link></code>
     * @throws WebCrawlerException Launch exception case some parameters are bad
     */
    private Title chekTitle(Title title) throws WebCrawlerException {
        if (title == null) {
            throw new WebCrawlerException("Title cannot be null");
        }

        Title find = null;
        for (Vertex<Title> v : graph.vertices()) {
            if (v.element().equals(title)) { //equals was overriden in Airport!!
                find = v.element();
            }
        }

        if (find == null) {
            throw new WebCrawlerException("Title with code (" + title.getTitleName() + ") does not exist");
        }

        return find;
    }

    /**
<<<<<<< HEAD
     * Return the status code from a URL
     *
     * @param url Site URL
     * @exception Input Output exception
     * @return Page status code in integer.
     */

=======
     * Return the status code from a page
     *
     * @param url Site URL
     * @exception Input Output exception
     */
>>>>>>> master
    private int getStatusCode(String url) throws IOException {
        Response response = Jsoup.connect(url).execute();
        int statusCode = response.statusCode();
        return statusCode;
    }

    /**
     * This method start the crow of a website
     *
     * @param startURL The base URL to be searched
     * @param numPages Number of pages (links) to be returned
     * @throws java.io.IOException Bad Input Outputs
     * @throws Model.WebCrawlerException To validate some bad inputs outputs
     */
    public void start(String startURL, int numPages) throws IOException, WebCrawlerException {
        addLinks(startURL);
        breathFirst(startURL, numPages);
    }

    /**
     * Return a list of titles
     *
     * @param baseURL The base URL to be searched
     * @exception IOException Input Output exception
     * @exception WebCrawlerException Some exception from inputs
     */
    private List<Title> addTitle(String baseURL) throws IOException, WebCrawlerException {

        if ("".equals(baseURL)) {
            throw new WebCrawlerException("URL não pode ser vazio");
        }

        Document doc = Jsoup.connect(baseURL).get();
        Elements links = doc.select("a[href]");

        List<Title> titles = new ArrayList<>();

        for (Element link : links) {
            Title newTitle = new Title(link.text());
            graph.insertVertex(newTitle);
            titles.add(newTitle);
        }

        return titles;
    }

    /**
     * Enter in link and process all links associated
     *
     * @param baseURL The base URL to be searched
     * @param numTimes The max of links to be searched
     * @exception IOException Input Output exception
     * @exception WebCrawlerException Some exception from inputs
     * @return <code>void</code>
     */
    @SuppressWarnings("null")
    private void breathFirst(String baseURL, int numTimes) throws WebCrawlerException, IOException {

        if ("".equals(baseURL) || baseURL.isEmpty() || baseURL == null) {
            throw new WebCrawlerException("URL cannot be empty or null");
        }
        
        // TODO
        List<Link> linksStoreds = getLinks(baseURL);
        

    }

    /**
     * For every link found it retrives a title associated
     *
     * @param url Base site URL
     * @exception IOException Input Output exception
     * @exception WebCrawlerException Some exception from inputs
     */
    private void addLinks(String url) throws IOException, WebCrawlerException {

        if ("".equals(url) || url.isEmpty()) {
            throw new WebCrawlerException("URL cannot be empty or null");
        }

        Document doc = Jsoup.connect(url).get();
        Title mainTitle = new Title(doc.title());
        graph.insertVertex(mainTitle);

        List<Title> addTitleList = addTitle(url);
        List<Link> listOfLinks = getLinks(url);
        Iterator<Link> iterator = listOfLinks.iterator();

        while (iterator.hasNext()) {
            addTitleList.forEach((subTitle) -> {
                graph.insertEdge(mainTitle, subTitle, iterator.next());
            });
        }

    }

    /**
     * Return all links in a specific website
     *
     * @param baseURL Base site URL
     * @exception IOException Input Output exception
     * @exception WebCrawlerException Some exception from inputs
     * @return Return a list of links in a specific website
     */
    private List<Link> getLinks(String baseURL) throws IOException, WebCrawlerException {

        if ("".equals(baseURL)) {
            throw new WebCrawlerException("URL não pode ser vazio");
        }

        Document doc = Jsoup.connect(baseURL).get();
        Elements links = doc.select("a[href]");
        List<Link> list = new ArrayList<>();

        for (Element link : links) {
            String href = link.attr("href");
            href = processLink(href, baseURL);
            list.add(new Link(href));
        }

        return list;
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
     * Count titles from a specifc website
     *
     * @return Number of titles (Vertex)
     */
    public int countTitles() {
        return graph.numVertices();
    }

    /**
     * Get a link between two a given titles
     *
     * @param title Fisrt title
     * @param title2 Second title
     * @return list of links between two titles
     */
    private List<Link> getLinkBetween(Title title, Title title2)
            throws WebCrawlerException {

        if (title == null || title2 == null) {
            throw new WebCrawlerException("Title cannot be null");
        }

        Title a1 = chekTitle(title);
        Title a2 = chekTitle(title2);

        List<Link> links = new ArrayList<>();

        try {
            for (Edge<Link, Title> edge : graph.edges()) {
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

    /**
     * Process diferent types of url's
     *
     * @param link The specific link
     * @param start_url The base site URL
     * @return return a processed link in <code>string</code>
     */
    private String processLink(String link, String start_url) {
        try {
            URL u = new URL(start_url);
            if (link.startsWith("./")) {
                link = link.substring(2, link.length());
                link = u.getProtocol() + "://" + u.getAuthority() + stripFilename(u.getPath()) + link;
            } else if (link.startsWith("#")) {
                link = start_url + link;
            } else if (link.startsWith("javascript:")) {
                link = null;
            } else if (link.startsWith("../") || (!link.startsWith("http://") && !link.startsWith("https://"))) {
                link = u.getProtocol() + "://" + u.getAuthority() + stripFilename(u.getPath()) + link;
            }
            return link;
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    /**
     * Just strip filename on link
     * 
     * @param path The URL of a website
     * @return Position of filename
     */
    private String stripFilename(String path) {
        int pos = path.lastIndexOf("/");
        return pos <= -1 ? path : path.substring(0, pos + 1);
    }
    
    /**
     * 
     * @return To format list of links associated of an Title
     */
    @Override
    public String toString() {
        //Falta acertar toString
        String output = "";
        int count = 0;
        try {
            for (Vertex<Title> vertex : graph.vertices()) {
                List<Title> titles = addTitle(start_url);
                Title mainTitle = titles.get(0);

                List<Link> list = getLinkBetween(mainTitle, vertex.element());
                for (Edge<Link, Title> edge : graph.edges()) {

                    output += "\t" + mainTitle.getTitleName() + " | "
                            + vertex.element() + "\n";
                    output += "\t\t " + edge.element().getLinkName() + "\n";
                }
            }
        } catch (WebCrawlerException | IOException ex) {
            Logger.getLogger(WebCrawler.class.getName()).log(Level.SEVERE, null, ex);
        }
        output += "\n";
        return output;

    }

}
