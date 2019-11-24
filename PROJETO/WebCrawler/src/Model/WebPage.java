package Model;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

// My Packages
import Exceptions.WebCrawlerException;

/**
 * Class that represents the <code>Vertex</code> on graph.
 *
 * @author BRKsCosta
 *
 */
public class WebPage {

    private String titleName = "";
    private String personalURL = "";
    private final List<WebPage> incidentWebPages = new ArrayList<>();
    private int statusCode;

    /**
     * Build a new object of this type
     *
     * @param url Base URL to search
     * @throws java.io.IOException
     */
    public WebPage(String url) throws IOException {

        // Instantiate the personalUrl
        this.personalURL = url;

        // Create a connection to the url
        Connection connection = Jsoup.connect(this.personalURL).
                userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21").
                timeout(10000);

        // Get the status code
        this.statusCode = this.getStatusCode();

        try {
            if (statusCode == 404) {
                this.statusCode = 404;
                this.titleName = "404 - Page not found";
            } else if (statusCode == 200) {
                this.titleName = connection.get().title();
                this.statusCode = 200;
            } else {
                this.titleName = Integer.toString(getStatusCode());
            }
        } catch (IOException e) {
            Logger.getLogger(WebPage.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public void setStatusCode(int status) {
        this.statusCode = status;
    }

    /**
     * Get a name of the title
     *
     * @return Name of title
     */
    public String getTitleName() {
        return titleName;
    }

    /**
     * Set personal page URL
     *
     * @param personalURL The URL to search
     */
    public void setPersonalURL(String personalURL) {
        this.personalURL = personalURL;
    }

    /**
     * Get WebPage personal URL
     *
     * @return
     */
    public String getPersonalURL() {
        return personalURL;
    }

    /**
     * Get status code from WebPage
     *
     * @return Return the status code in a integer
     * @throws MalformedURLException
     * @throws IOException
     */
    public final int getStatusCode() throws MalformedURLException, IOException {
        URL url = new URL(this.personalURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        
        return connection.getResponseCode();
    }

    /**
     * Set a title name
     *
     * @param titleName String title name to set
     */
    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    /**
     * Find all links on WebPage
     *
     * @param personalLink Personal WebPage link
     * @return List of all links found
     * @throws WebCrawlerException
     * @throws IOException
     */
    public Queue<Link> getAllIncidentWebPages(String personalLink) throws WebCrawlerException, IOException {

        //Check if page is not found
        if ("".equals(personalLink)) {
            throw new WebCrawlerException("URL não pode ser vazio");
        }

        Document doc = Jsoup.connect(personalLink).get();
        Elements links = doc.select("a[href]");
        Queue<Link> listIncidentsWebPages = new LinkedList();

        for (Element link : links) {
            String href = link.attr("abs:href");
            String newHref = processLink(href, personalLink);
            Link newObjLink = new Link(newHref);
            listIncidentsWebPages.offer(newObjLink);

        }

        Set<Link> set = new HashSet(listIncidentsWebPages);
        listIncidentsWebPages.clear();
        listIncidentsWebPages.addAll(set);

        return listIncidentsWebPages;
    }

    /**
     * Process different types of URL
     *
     * @param link The specific link
     * @param startURL The base site URL
     * @return return a processed link in <code>string</code>
     */
    private String processLink(String link, String startURL) {
        try {
            URL u = new URL(startURL);
            if (link.startsWith("./")) {
                link = link.substring(2, link.length());
                link = u.getProtocol() + "://" + u.getAuthority() + stripFilename(u.getPath()) + link;
            } else if (link.startsWith("#")) {
                link = startURL + link;
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
     * To print WebPage object
     *
     * @return Formatted title name
     */
    @Override
    public String toString() {
        try {
            return "WebPage { " + "personalURL=" + personalURL
                    + ", incidentWebPages = "
                    + getAllIncidentWebPages(this.personalURL).size()
                    + ", statusCode = " + this.statusCode + '}' + "\n";
        } catch (WebCrawlerException ex) {
            Logger.getLogger(WebPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WebPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

}
