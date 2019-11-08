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
import org.jsoup.Connection.Response;

/**
 *
 * @author BRKsCosta
 */
public class WebCrawler {

    private String start_url = "";
    private int numPages = 0;
    private Graph<Title, Link> graph;

    public WebCrawler(String string) {
        this.start_url = string;
        this.graph = new GraphEdgeList();
    }

    public Response checkPageResponse(String url) throws IOException {
        Response response = Jsoup.connect(url).execute();
        return response;
    }

    public void addPage(Title title, Link link) throws WebCrawlerException {

        if (title == null || link == null) {
            throw new WebCrawlerException("Title or link is empty");
        }

        graph.insertVertex(title);
        graph.insertEdge(title, title, link);

    }

    public void start(String start_url) throws IOException {
        crawller(start_url);
    }

    private void crawller(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        String title = doc.title();
        title += "\n";
        System.out.println(title);
        
        Elements links = doc.select("a[href]");

        for (Element link : links) {
            String href = link.attr("href");
            href = processLink(href, url);
            href += " | " + link.text();
            System.out.println(href);
        }
        //System.out.println(processLink("../", url));
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
