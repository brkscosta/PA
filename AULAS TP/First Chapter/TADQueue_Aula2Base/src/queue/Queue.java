package queue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author patricia.macedo
 */
public interface Queue<E>{
    
    public int size();
    public boolean isEmpty();
    public void enqueue(E elem) throws QueueFullException;
    public E dequeue() throws QueueEmptyException;
    public E peek() throws QueueEmptyException;
    public void clear();
}
