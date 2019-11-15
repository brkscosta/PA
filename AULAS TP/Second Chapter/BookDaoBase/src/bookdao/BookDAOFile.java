/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookdao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author patricia.macedo
 */
public class BookDAOFile implements BookDAO {

    public static final String FILENAME = "books.txt";

    private List<Book> inMemory;

    public BookDAOFile() {
    }

    private void saveFile() {
        try {
            FileOutputStream fileOut
                    = new FileOutputStream(FILENAME);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            out.writeObject(inMemory);
            out.close();

            fileOut.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
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

            inMemory = (List<Book>) in.readObject();
            in.close();

            fileIn.close();

        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void save(Book b) {
        //TODO
        /*
        Verificar se liro ja existe
         */
        int index = -1;

        for (int i = 0; i < inMemory.size(); i++) {
            if (inMemory.get(i).getIsbn().compareToIgnoreCase(b.getIsbn()) == 0) {
                index = i;
            }
        }

        if (index != -1) {
            inMemory.set(index, b);
        } else {
            inMemory.add(b);
        }

        this.saveFile();
    }

    @Override
    public Book loadBook(Book b) {

        for (Book book : inMemory) {
            if (book.getIsbn().compareToIgnoreCase(b.getIsbn()) == 0) {
                return book;
            }
        }
        return null;
    }

    @Override
    public List<Book> readAll() {
        return inMemory;
    }

    @Override
    public boolean delete(Book b) {

        int index = -1;

        for (int i = 0; i < inMemory.size(); i++) {
            if (inMemory.get(i).getIsbn().compareToIgnoreCase(b.getIsbn()) == 0) {
                index = i;
            }
        }

        if (index != -1) {
            inMemory.remove(index);
            saveFile();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Book read(String isbn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}
