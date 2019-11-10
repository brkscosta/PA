/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import Graph.Graph;
import Graph.GraphEdgeList;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jsoup.Connection.Response;

/**
 *
 * @author BRKsCosta
 */
public class WebCrawler {
    
    public enum PageHttpResponse {
                
    };
    
    private String start_url = "";
    private int numPages = 0;
    private Graph<Title, Link> graph;

    public WebCrawler(String string, int numPages) {
        this.start_url = string;
        this.graph = new GraphEdgeList();
        this.numPages = numPages;
    }

    private int getStatusCode(String url) throws IOException {
        Response response = Jsoup.connect(url).execute();
        int statusCode = response.statusCode();
        return statusCode;
    }

    public void start(String start_url, int numPages) throws IOException, WebCrawlerException {
        enterLinks(start_url, numPages);
    }

    private List<Title> addTitle(String baseURL) throws IOException, WebCrawlerException {
        
        if("".equals(baseURL)) throw new WebCrawlerException("URL não pode ser vazio");

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

    private void enterLinks(String baseURL, int numTimes) throws WebCrawlerException, IOException {
        
        if("".equals(baseURL) || baseURL.isEmpty() || baseURL == null) 
            throw new WebCrawlerException("URL cannot be empty or null");
        if (numTimes == 0)
            throw new WebCrawlerException("Num times variable is null");
            
        List<Link> linksStoreds = getLinks(baseURL);
        
        
        
    }    
    
    private void addLinks(String url) throws IOException, WebCrawlerException{
        
        if("".equals(url) || url.isEmpty()) 
            throw new WebCrawlerException("URL cannot be empty or null");
        
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
        
        if("".equals(baseURL)) throw new WebCrawlerException("URL não pode ser vazio");
        
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
    
    @Override
    public String toString() {
        
        return " ";
        
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
}
