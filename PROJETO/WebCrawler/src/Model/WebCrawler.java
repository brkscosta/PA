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

    private int getStatusCode(String url) throws IOException {
        Response response = Jsoup.connect(url).execute();
        int statusCode = response.statusCode();
        return statusCode;
    }

    public void start(String start_url, int numPages) throws IOException, WebCrawlerException {
        addLinks(start_url);
        enterLinks(start_url, numPages);
    }

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

    @SuppressWarnings("null")
    private void enterLinks(String baseURL, int numTimes) throws WebCrawlerException, IOException {

        if ("".equals(baseURL) || baseURL.isEmpty() || baseURL == null) {
            throw new WebCrawlerException("URL cannot be empty or null");
        }
        
        // TODO
        List<Link> linksStoreds = getLinks(baseURL);
        

    }

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

    public int countLinks() {
        return graph.numEdges();
    }

    public int countTitles() {
        return graph.numVertices();
    }

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

    private String stripFilename(String path) {
        int pos = path.lastIndexOf("/");
        return pos <= -1 ? path : path.substring(0, pos + 1);
    }

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
