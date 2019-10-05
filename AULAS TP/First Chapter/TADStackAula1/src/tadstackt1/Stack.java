/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tadstackt1;



/**
 *
 * @author PM-Uninova
 */
public interface Stack<E>  {

	//numero de elementos presentes na pilha 
	public int size( ); 
	//nao contem elementos?
	public boolean isEmpty( ); 
	//devolve proximo objecto a sair, sem remover
	public E peek( ) throws EmptyStackException; 
	//empilha novo elemento
	public void push(E element) ;			
	//desempilha proximo elemento                                                                                     
	public E pop()   	   	
		throws EmptyStackException;  
        
   
}

