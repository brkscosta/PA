/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patterns.Singleton;

import Model.WebPage;
import java.io.*;
import java.util.Date;

/**
 * This class is responsible to write loggers on a text file
 *
 * @author BRKsCosta and danielcordeiro
 */
public final class LoggerWriter {

    //<editor-fold defaultstate="collapsed" desc="Variables">
    private static final String LOGGERFILE = "logger.txt";
    private PrintStream printStream;
    private static LoggerWriter instance = new LoggerWriter();
//</editor-fold>

    private LoggerWriter() {
        connect();
    }
    
    /**
     * This method will return the only LoggerInstance object existing in the project. Singleton pattern.
     * 
     * @return a LoggerWriter object
     */
    public static LoggerWriter getInstance() {

        return instance;
    }

    /**
     * This method will connect to a new file
     * @return true or false
     */
    public boolean connect() {
        if (printStream == null) {
            try {
                printStream = new PrintStream(new FileOutputStream(LOGGERFILE), true);
            } catch (FileNotFoundException ex) {
                printStream = null;
                return false;

            }
            return true;
        }
        return true;
    }

    //<editor-fold defaultstate="collapsed" desc="Writter Methods">
    /**
     * Write to a text file
     *
     * @param str message to write
     * @throws LoggerException
     */
    public void writeToLog(String str) throws LoggerException {
        if (printStream == null) {
            throw new LoggerException("Connection fail");
        }
        
        printStream.println(new Date().toString() + "  " + str);
        
    }
    
    /**
     * Write to a text file when inserting a webPage and a link
     *
     * @param webPageInserting
     * @param visitedWebPage
     * @param incidentsSize
     */
    public void webPageInsertWriteToLog(WebPage webPageInserting, WebPage visitedWebPage, int incidentsSize) {
        this.writeToLog(webPageInserting.getTitleName() + " | "
                + webPageInserting.getPersonalURL() + " | " + visitedWebPage.getTitleName()
                + " | " + incidentsSize);
    }
//</editor-fold>

    @Override
    public String toString() {
        return "Logger{" + "printStream=" + printStream + '}';
    }

}
