/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookdao;

import java.util.List;

/**
 *
 * @author patricia.macedo
 */
public interface BookDAO {
    @SuppressWarnings("NonPublicExported")
    
    /**
     * Guarda o livro num formato de ficheiro específico
     * @param b Objeto livro
     */
    public void save(Book b);

    /**
     * Lê um livro a partir de um ficheiro já carregado
     * @param isbn Identificador do livro
     * @return retorna um objeto do tipo livro
     */
    public Book loadBook(Book isbn);
    
    /**
     * Lê todos os livros a partir de um ficheiro já carregado
     * @return Retona uma lista com todos os livros
     */
    
    public List<Book> readAll();

    public boolean delete(Book isbn);
    
    public Book read(String isbn);
    
}
