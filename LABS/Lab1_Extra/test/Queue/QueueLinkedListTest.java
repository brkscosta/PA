/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Queue;

import Queue.QueueLinkedList.ListNode;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;
import Queue.QueueLinkedList.ListNode;
import java.util.ArrayList;

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
        for (int i = 0; i < 5; i++) {
            queueLinkedList.enqueue(i);
            queueLinkedList.dequeue();
            assertEquals("O valor esperado do peek ", 0, queueLinkedList.peek());
        }

    }

    @Test
    public void size_iterativo() {
        for (int i = 0; i < 5; i++) {
            queueLinkedList.enqueue(i);
        }
        assertEquals("O valor esperado do peek ", 5, queueLinkedList.size());
    }

    @Test
    public void size_recursive() {

        queueLinkedList.enqueue(1);
        queueLinkedList.enqueue(2);
        queueLinkedList.enqueue(3);
        ListNode current = this.queueLinkedList.header.next.next;
        int a = queueLinkedList.recursiveSize(current);
        assertEquals("Implementação size recursivo ", 3, a);
    }

}
