/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookdao;

/**
 *
 * @author patricia.macedo
 */
public class BookDaoMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        BookDAO bookDao = new BookDAOSerialization();
        Book b = new Book("5181", "Rua Sesamo", "Rita");
        Book b2 = new Book("5183", "Rua Sesamo3", "Ana");
        Book b3 = new Book("5184", "Rua Sesamo1", "Tomas");
        Book b4 = new Book("51835", "Rua Sesamo6", "Luis");
        bookDao.save(b);
        bookDao.save(b2);
        bookDao.save(b3);
        bookDao.save(b4);
        System.out.println(bookDao.readAll());

       
    }

    
}
