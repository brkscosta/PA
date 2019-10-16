/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab0;

import java.util.Arrays;

/**
 *
 * @author BRKsCosta
 */
public class QueueArray<T> implements Queue<T> {

    private T[] elems;
    private int size, capacity;
    private final static int MAX = 100;

    public QueueArray(int initCapacity) {
        this.elems = (T[]) new Object[initCapacity];
        this.size = 0;
        this.capacity = initCapacity;
    }

    public QueueArray() {
        this(MAX);
    }

    @Override
    public void enqueue(T elem) throws QueueFullException {

        if (size == capacity) {
            try {
                elems = Arrays.copyOf(elems, capacity + 10);
                this.capacity = this.capacity+10;
            } catch (OutOfMemoryError e) {
                throw new QueueFullException();
            }

        }
        elems[size] = elem;
        size++;
    }

    @Override
    public T dequeue() throws QueueEmptyException {
        if (isEmpty()) {
            throw new QueueEmptyException();
        }

        T first = elems[0];
        
        for(int i = 0; i < size - 1; i++) 
            this.elems[i] = this.elems[i + 1];
        
        size--;
        return first;
    }

    @Override
    public T peek() throws QueueEmptyException {
        if (isEmpty()) {
            throw new QueueEmptyException();
        }

        T first = elems[0];
        return first;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {

        String str = "";

        for (int i = 0; i < size; i++) {
            str += elems[i].toString();
            str += " ";
        }
        return "QueueArray{" + "elems=" + str + ", size=" + size + ", capacity=" + capacity + '}';
    }

}
