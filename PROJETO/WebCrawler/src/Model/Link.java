/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Iterator;

/**
<<<<<<< Updated upstream
 *
 * @author BRKsCosta
=======
 * Class that represents de Edge {@link Graph.Edge} on graph.
 * 
 * @author BRKsCosta 
>>>>>>> Stashed changes
 */
public class Link {
    
    private String linkName = "";

    public Link(String name) {
        this.linkName = name;
    }
    
    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    @Override
    public String toString() {
        return "Link Name: " + linkName + "\n";
    }

    
}
