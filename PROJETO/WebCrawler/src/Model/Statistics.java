/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 * Storage statistics
 *
 * @author BRKsCosta
 */
public class Statistics {

    private int countHttpsLinks = 0;
    private int countPageNotFound = 0;
    private int countWebPages = 0;
    private int countLinks = 0;

    public void setCountWebPages(int countWebPages) {
        this.countWebPages = countWebPages;
    }

    public void setCountLinks(int countLinks) {
        this.countLinks = countLinks;
    }
    
    public Statistics() {
    }

    public int getCountHttpsLinks() {
        return countHttpsLinks;
    }

    public void setCountHttpsLinks(int countHttpsLinks) {
        this.countHttpsLinks = countHttpsLinks;
    }

    public int getCountPageNotFound() {
        return countPageNotFound;
    }

    public void setCountPageNotFound(int countPageNotFound) {
        this.countPageNotFound = countPageNotFound;
    }

    public void incrementPageNotFound() {
        this.countPageNotFound++;
    }

    public void incrementHttpsLinks() {
        this.countHttpsLinks++;
    }

    public int getCountWebPages() {
        return countWebPages;
    }

    public int getCountLinks() {
        return countLinks;
    }
    
    
}
