/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Queue;

import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author BRKsCosta
 */
public class QueueLinkedListTest {

    private QueueLinkedList queueLinkedList;

    public QueueLinkedListTest() {
    }

    @Before
    public void setUp() {
        this.queueLinkedList = new QueueLinkedList();
    }

    @Test
    public void size_output() {
        int a;
        queueLinkedList.enqueue(1);
        assertEquals("Tamanho atual do size", a = 1, queueLinkedList.size());
        queueLinkedList.enqueue(2);
        queueLinkedList.enqueue(3);
        assertEquals("Tamanho atual do size", a = 3, queueLinkedList.size());
        queueLinkedList.dequeue();
        assertEquals("Tamanho atual do size", a = 2, queueLinkedList.size());

    }

    @Test(expected = QueueEmptyException.class)
    public void peek_getFirstValue() {
        for(int i = 0; i < 5; i++){
            queueLinkedList.enqueue(i);
            queueLinkedList.dequeue();
            assertEquals("O valor esperado do peek ", 0, queueLinkedList.peek());
        }
        
    }
    
    

}
