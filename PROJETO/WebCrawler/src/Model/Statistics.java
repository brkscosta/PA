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

    //<editor-fold defaultstate="collapsed" desc="variables">
    private int countHttpsLinks = 0;
    private int countPageNotFound = 0;
    private int countWebPages = 0;
    private int countLinks = 0;
//</editor-fold>
    
    /**
     * Default Constructor
     */
    public Statistics() {
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getters">
    /**
     * the count of links with http protocol
     * @return a number
     */
    public int getCountHttpsLinks() {
        return countHttpsLinks;
    }
    
    /**
     * the count of pages not found
     * @return a number
     */
    public int getCountPageNotFound() {
        return countPageNotFound;
    }
    
    /**
     * the count of web pages
     * @return a number
     */
    public int getCountWebPages() {
        return countWebPages;
    }
    
    /**
     * Count of link
     * @return a number
     */
    public int getCountLinks() {
        return countLinks;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Setters">
    
    /**
     * sets new value
     * @param countWebPages a number
     */
    public void setCountWebPages(int countWebPages) {
        this.countWebPages = countWebPages;
    }
    /**
     * sets new value
     * @param countLinks a number
     */
    public void setCountLinks(int countLinks) {
        this.countLinks = countLinks;
    }
    /**
     * sets new value
     * @param countHttpsLinks a number
     */
    public void setCountHttpsLinks(int countHttpsLinks) {
        this.countHttpsLinks = countHttpsLinks;
    }
    /**
     * sets new value
     * @param countPageNotFound a number
     */
    public void setCountPageNotFound(int countPageNotFound) {
        this.countPageNotFound = countPageNotFound;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Increment Methods">
    
    /**
     * This method increments 1 number
     */
    public void incrementPageNotFound() {
        this.countPageNotFound++;
    }
    
    /**
     * This method increments 1 number
     */
    public void incrementHttpsLinks() {
        this.countHttpsLinks++;
    }
//</editor-fold>
    
}
