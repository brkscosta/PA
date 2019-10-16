/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab0;

/**
 *
 * @author BRKsCosta
 */
public interface Queue<T> {
    
    public void enqueue(T elem) throws QueueFullException;
    
    public T dequeue() throws QueueEmptyException;
    
    public T peek() throws QueueEmptyException;
    
    public boolean isEmpty();
}
