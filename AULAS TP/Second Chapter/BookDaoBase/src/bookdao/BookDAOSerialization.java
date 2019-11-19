/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookdao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author brunomnsilva
 */
public class BookDAOSerialization implements BookDAO {

    public static final String FILENAME = "books.data";

    private List<Book> inMemory;

    public BookDAOSerialization() {
        this.inMemory = new ArrayList<>();
        loadFile();
    }

    /*
    Save imMemory list to file
     */
    private void saveFile() {
        try {
            FileOutputStream fileOut = new FileOutputStream(FILENAME);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            out.writeObject(inMemory);
            out.close();

            fileOut.close();
        } catch (IOException e) {
            Logger.getLogger(BookDAOSerialization.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void loadFile()  {
        try {
            File f = new File(FILENAME);
            if(!f.exists()) {
                inMemory = new ArrayList<>();
                return;
            }
            
            FileInputStream fileIn = new FileInputStream(FILENAME);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            inMemory = (List<Book>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException e) {
            Logger.getLogger(BookDAOSerialization.class.getName()).log(Level.SEVERE, null, e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BookDAOSerialization.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void save(Book b) {
        int index = -1;
        for (int i = 0; i < inMemory.size(); i++) {
            if( inMemory.get(i).getIsbn().compareToIgnoreCase(b.getIsbn()) == 0) {
                index = i;
            }
        }
        
        if(index != -1) {
            inMemory.set(index, b);
        } else {
            inMemory.add(b);
        }        
        
        saveFile();
    }

    @Override
    public Book read(String isbn) {
        for (Book book : inMemory) {
            if(book.getIsbn().compareToIgnoreCase(isbn) == 0) 
                return book;
        }
        
        return null;
    }

    @Override
    public List<Book> readAll() {
        return inMemory;
    }

    @Override
    public boolean delete(String isbn) {
    
        int index = -1;
        for (int i = 0; i < inMemory.size(); i++) {
            if( inMemory.get(i).getIsbn().compareToIgnoreCase(isbn) == 0) {
                index = i;
            }
        }
        
        if(index != -1) {
            inMemory.remove(index);
            saveFile();
            return true;
        } else {
            return false;
        }
        
    }

}
