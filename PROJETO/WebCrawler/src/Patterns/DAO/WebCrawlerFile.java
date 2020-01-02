/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patterns.DAO;

import Model.Link;
import Model.WebPage;
import static Patterns.DAO.WebCrawlerJson.FILENAME;
import static Patterns.FactoryMVC.FactoryMVC.view;
import Patterns.Singleton.LoggerWriter;
import com.brunomnsilva.smartgraph.graph.Edge;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author BRKsCosta
 */
public class WebCrawlerFile implements IWebCrawlerDAO {

    private LoggerWriter logger = LoggerWriter.getInstance();

    public static final String FILENAME = "Webcrawler.txt";

    private Collection<Edge<Link, WebPage>> inMemory;

    public WebCrawlerFile() {
        this.inMemory = new ArrayList();
    }

    @Override
    public void saveAll() {
        this.saveFile();
    }

    @Override
    public Collection<Edge<Link, WebPage>> readAll() {
        return inMemory;
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

}
