package Model;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private final int statusCode;

    /**
     * Build a new object of this type
     *
     * @param url Base URL to search
     */
    public WebPage(String url) {

        // Instantiate the personalUrl
        this.personalURL = url;

        // Create a connection to the url
        Connection connection = Jsoup.connect(this.personalURL);

        // Get the status code
        this.statusCode = connection.response().statusCode();

        // Check status code 
        if (statusCode == 400) // Set page title how not found
        {
            this.titleName = EStatusCode.NOTFOUND.name();
        } else {
            this.titleName = EStatusCode.OK.name();
        }

    }

    /**
     * Get a name of the title
     *
     * @return Name of title
     */
    public String getTitleName() {
        return titleName;
    }

    public void setPersonalURL(String personalURL) {
        this.personalURL = personalURL;
    }

    public String getPersonalURL() {
        return personalURL;
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
                    + ", incidentWebPages="
                    + getAllIncidentWebPages(this.personalURL).size() + ", statusCode=" + statusCode + '}' + "\n";
        } catch (WebCrawlerException ex) {
            Logger.getLogger(WebPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WebPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    // StatusCode ENUM
    public static enum EStatusCode {

        OK, NOTFOUND;

        public String getStatusTitleName(int code) {
            switch (code) {
                case 200:
                    return this.getTitleName(this.OK);
                case 404:
                    return this.getTitleName(this.NOTFOUND);
            }

            return "";
        }

        private String getTitleName(EStatusCode statusString) {
            switch (statusString) {
                case OK:
                    return "Page OK";
                case NOTFOUND:
                    return "Page not found";
            }

            return "";
        }

    }

}
