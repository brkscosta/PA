/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookdao;

import java.util.List;

/**
 *
 * @author brunomnsilva
 */
public interface BookDAO {

    /**
     * Saves the book in the current persistence storage.
     *
     * @param b
     */
    public void save(Book b);

    /**
     * Retrieves from the current storage the book with given isbn.
     *
     * @param isbn
     * @return
     */
    public Book read(String isbn);

    /**
     * Removes a book from the current storage persistence.
     * 
     * @param isbn
     * @return 
     */
    public boolean delete(String isbn);
    
    /**
     * Retrieves all books from current storage persistence.
     * 
     * @return 
     */
    public List<Book> readAll();

}
