/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queue;

/**
 *
 * @author patricia.macedo
 */
public class QueueLinked<E> implements Queue<E> {

    private int nElem, capacity;
    private Node head, tail;

    public QueueLinked() {
        this.head = new Node(null, null, null);
        this.tail = new Node(null, null, head);
        this.head.next = tail;
    }

    public QueueLinked(int capacity) {

    }

    @Override
    public int size() { // O(n)
        int count = 0;

        Node current = head.next;
        
        if(current == tail)
            return 0;

        while (current != tail.prev) {
            current = current.next;
            count++;
        }

        return count;
    }

    @Override
    public boolean isEmpty() { // O(n)

        return size() == 0;
    }

    @Override
    public void enqueue(E elem) throws QueueFullException { // O(n)

        Node newNode = new Node(elem, tail, tail.prev);

        tail.prev.next = newNode;
        tail.prev = newNode;

    }

    @Override
    public E dequeue() throws QueueEmptyException { // O(n)
        if (isEmpty()) {
            throw new QueueEmptyException("Queue Empty");
        }

        Node remove = head.next;

        head.next = remove.next; //Ou header.next.next
        remove.next = head;

        return remove.elem;

    }

    @Override
    public E peek() throws QueueEmptyException {
        if (isEmpty()) {
            throw new QueueEmptyException("Queue Empty");
        }

        return this.head.next.elem;
    }

    @Override
    public void clear() {
        head.next = tail;
        tail.prev = head;
    }

    private class Node {

        private E elem;
        private Node next, prev;

        public Node(E elem, Node next, Node prev) {
            this.elem = elem;
            this.next = next;
            this.prev = prev;
        }
    }

}
