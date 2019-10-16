/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Queue;

/**
 *
 * @author brunomnsilva
 */
public class QueueLinkedList<E> implements Queue<E> {

    private final ListNode header, trailer;
    private int size;
    
    public QueueLinkedList() {
        this.trailer = new ListNode(null, null, null); //header not instanciated yet!
        this.header = new ListNode(null, null, this.trailer);
        this.trailer.prev = this.header;
        this.size = 0;
    }
    
    @Override
    public int size() { 
        return this.size; 
    }
   
    @Override
    public boolean isEmpty() {
        return (this.size == 0);
    }

    @Override
    public void enqueue(E elem) throws QueueFullException {
        
        try {
            ListNode newNode = new ListNode(elem, this.trailer.prev, this.trailer);
            
            this.trailer.prev.next = newNode;
            this.trailer.prev = newNode;
            
            this.size++;
            
        } catch (OutOfMemoryError e) {
            throw new QueueFullException();
        }
        
    }

    @Override
    public E dequeue() throws QueueEmptyException {
        if( this.header.next == this.trailer ) throw new QueueEmptyException();
        
        E front = this.header.next.elem;   
        
        this.header.next = this.header.next.next;
        this.header.next.prev = this.header;
        
        this.size--;
        
        return front;
    }

    @Override
    public E peek() throws QueueEmptyException {
        if( this.header.next == this.trailer ) throw new QueueEmptyException();
        
        E front = this.header.next.elem;        
        
        return front;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("QueueLinkedList{size=" + this.size + ", front|[");
        
        ListNode node = this.header.next;
        
        while(node != this.trailer) {
            sb.append( node.elem );
            if(node != this.trailer.prev) { //don't place last comma
                sb.append( "," );
            }
            node = node.next;
        }
        
        sb.append("]|end}");
        return sb.toString();
    }
    
    private class ListNode {
        E elem;
        ListNode next, prev;

        public ListNode(E elem, ListNode prev, ListNode next) {
            this.elem = elem;
            this.next = next;
            this.prev = prev;
        }

    }
}
