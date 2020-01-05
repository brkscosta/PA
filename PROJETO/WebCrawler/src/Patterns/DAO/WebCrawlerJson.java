/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patterns.DAO;

import Model.Link;
import Model.WebCrawler;
import Model.WebPage;
import static Patterns.FactoryMVC.FactoryMVC.view;
import Patterns.Singleton.LoggerWriter;
import com.brunomnsilva.smartgraph.graph.Edge;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author BRKsCosta and danielcordeiro
 */
public class WebCrawlerJson implements IWebCrawlerDAO {

    public static final String FILENAME = "Webcrawler.json";
    private LoggerWriter logger = LoggerWriter.getInstance();
    private Collection<Edge<Link, WebPage>> inMemory;

    public WebCrawlerJson() {
        this.inMemory = new ArrayList();
    }

    @Override
    public void saveAll() {
        this.saveFile();
    }

    private void saveFile() {
        try {
            FileOutputStream fileOut
                    = new FileOutputStream(FILENAME);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            out.writeObject(inMemory);
            out.close();

            fileOut.close();
        } catch (IOException ex) {
            logger.writeToLog(ex.getMessage());
            view.showErrorStackTraceException(ex.getMessage());
        }
    }

    private void loadFile() {
        try {

            File f = new File(FILENAME);
            if (!f.exists()) {
                inMemory = new ArrayList<>();
                return;
            }

            FileInputStream fileIn
                    = new FileInputStream(FILENAME);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            inMemory = (Collection<Edge<Link, WebPage>>) in.readObject();
            in.close();

            fileIn.close();

        } catch (IOException | ClassNotFoundException ex) {
            logger.writeToLog(ex.getMessage());
            view.showErrorStackTraceException(ex.getMessage());
        }
    }

    @Override
    public Collection<Edge<Link, WebPage>> readAll() {
        return inMemory;
    }

}
