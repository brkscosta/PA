package Model;

/**
 * Class that represents de <code>Edge<V, E></code> on graph.
 * 
 * @author BRKsCosta
 * Class that represents de Edge {@link Graph.Edge} on graph.
 * 
 * @author BRKsCosta 
 */
public class Link {
    
    private String linkName = "";
    
    /**
     * Build a new object
     * 
     * @param name The URL from an website
     */
    public Link(String name) {
        this.linkName = name;
    }
    
    /**
     * Get the URL
     * 
     * @return return the URL
     */
    public String getLinkName() {
        return linkName;
    }
    
    /**
     * Set a new name of URL
     * 
     * @param linkName The URL
     */
    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }
    
    /**
     * Format the URL
     * @return URL formated
     */
    @Override
    public String toString() {
        return "Link Name: " + linkName + "\n";
    }

    
}
