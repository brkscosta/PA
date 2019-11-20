/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookdao;

import static bookdao.BookDAOSerialization.FILENAME;
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
public class BookDAOSerializationMultiple implements BookDAO {

    @Override
    public void save(Book b) {

        String filename = b.getIsbn() + ".ser";
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            out.writeObject(b);
            out.close();

            fileOut.close();
        } catch (IOException e) {
            Logger.getLogger(BookDAOSerialization.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @Override
    public Book read(String isbn) {

        String filename = isbn + ".ser";
        try {
            File f = new File(filename);
            if (!f.exists()) {
                return null;
            }
            Book b;
            FileInputStream fileIn = new FileInputStream(FILENAME);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            b = (Book) in.readObject();
            in.close();
            fileIn.close();
            return b;
        } catch (IOException e) {
            Logger.getLogger(BookDAOSerialization.class.getName()).log(Level.SEVERE, null, e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BookDAOSerialization.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    @Override
    public boolean delete(String isbn) {
        
        String filename = isbn + ".ser";
        
        File f = new File(filename);
        
        if(f.exists()) {
            f.delete();
            return true;
        } else {
            return false;
        }
        
    }

    @Override
    public List<Book> readAll() {

        List<Book> list = new ArrayList<>();

        File dir = new File(".");
        File[] listFiles = dir.listFiles();
        for (File f : listFiles) {
            
            if(!f.getName().endsWith(".ser")) continue;
            
            try {
               
                Book b;
                
                FileInputStream fileIn = new FileInputStream(f);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                b = (Book) in.readObject();
                in.close();
                fileIn.close();
                
                list.add(b);
                
            } catch (IOException e) {
                Logger.getLogger(BookDAOSerialization.class.getName()).log(Level.SEVERE, null, e);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BookDAOSerialization.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return list;

    }

}
