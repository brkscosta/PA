/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookdao;

/**
 *
 * @author brunomnsilva
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Book b1 = new Book("12345", "PA for dummies", "José Manuel", 2019);
        Book b2 = new Book("12347", "ATAD for dummies", "José Manuel", 2019);
        Book b3 = new Book("12349", "CBD for dummies", "José Manuel", 2019);
        
       
        //BookDAO dao = new BookDAOSerialization();
        BookDAO dao = new BookDAOSerializationMultiple();
        
        dao.save(b1);
        dao.save(b2);
        dao.save(b3);
        
        System.out.println(dao.readAll());

//        Book b = dao.read("12349");
//        System.out.println("Leitura do livro: " + b);

//          dao.delete("12349");
    }
    
}
