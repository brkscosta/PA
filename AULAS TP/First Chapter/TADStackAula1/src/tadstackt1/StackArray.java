/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tadstackt1;

import java.util.Arrays;

public class StackArray<E> implements Stack<E> {

    private E[] elems;
    private int size;
    private final static int MAX = 500;

    public StackArray(int capacity) {
        elems = (E[]) new Object[capacity];
        size = 0;
    }

    public StackArray() {
        this(MAX);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
       //completar
       return this.size == 0;
    }

    @Override
    public E peek() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException("Vazio");
        }
        return elems[size - 1];
    }

    @Override
    public void push(E elem) throws FullStackException {
        if (size == elems.length) {
            throw new FullStackException("");
        }
        elems[size++] = elem;
    }

    @Override
    public E pop() throws EmptyStackException {
     //completar
     if (isEmpty())
         throw new EmptyStackException("Pilha Vazia");
     
     return (E) this.elems[--size];
    }

    @Override
    public String toString() {
        return "StackArray{" + "elems = " + Arrays.toString(elems) + '}';
    }
    
   
    
}
