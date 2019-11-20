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
     * @throws IOException
     */
    public WebPage(String url) throws IOException {

        // Instantiate the personalUrl
        this.personalURL = url;

        // Create a connection to the url
        Connection connection = Jsoup.connect(this.personalURL);

        // Get the status code
        this.statusCode = connection.response().statusCode();

        // Check status code 
        EStatusCode e = null;

        String statusTitlePageName = e.getStatusTitleName(statusCode);

        // Set page title
        this.setTitleName(statusTitlePageName);

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
     * Return the status code from a URL
     *
     * @param url Site URL
     * @exception Input Output exception
     * @return Page status code in integer.
     */
    private int getStatusCode(String url) throws IOException {
        Connection.Response response = Jsoup.connect(url).execute();
        int statusCode = response.statusCode();
        return statusCode;
    }

    private List<Link> getIncidentWebPages() throws WebCrawlerException, IOException {

        //Check if page is not found
        if ("".equals(this.personalURL)) {
            throw new WebCrawlerException("URL não pode ser vazio");
        }

        Document doc = Jsoup.connect(this.personalURL).get();
        Elements links = doc.select("a[href]");
        List<Link> listIncidentsWebPages = new ArrayList<>();

        for (Element link : links) {
            String href = link.attr("href");
            href = processLink(href, this.personalURL);
            listIncidentsWebPages.add(new Link(href));
        }

        return listIncidentsWebPages;
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
     * To print title name
     *
     * @return Formated title name
     */
    @Override
    public String toString() {
        return "WebPage{" + "personalURL=" + personalURL + ", incidentWebPages=" + incidentWebPages + ", statusCode=" + statusCode + '}';
    }

    /**
     * To print title name
     *
     * @return Formated title name
     */
    

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
