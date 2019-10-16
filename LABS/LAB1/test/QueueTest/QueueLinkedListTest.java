/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QueueTest;

import Queue.QueueEmptyException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import Queue.QueueLinkedList;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author BRKsCosta
 */
public class QueueLinkedListTest {

    private QueueLinkedList queueInstance;

    public QueueLinkedListTest() {
    }

    @Before
    public void setUp() {
        this.queueInstance = new QueueLinkedList();
    }

    @Test
    public void isEmpty_True_onCreate() {

        assertEquals("A implementação não retorna vazio após a sua criação", true, queueInstance.isEmpty());
    }

    @Test
    public void isEmpty_false_hasElements() {
        queueInstance.enqueue(1);
        queueInstance.enqueue(2);
        assertEquals("A implementação retorna false", false, queueInstance.isEmpty());
    }

    @Test
    public void isEmpty_true_elementsWasRemoved() {
        queueInstance.enqueue(4);
        queueInstance.dequeue();
        assertEquals("A implementação retorna true apos elementos serem todos removidos", true, queueInstance.isEmpty());

    }

    @Test
    public void enqueue_true_checkFIFO() {
        queueInstance.enqueue(100);
        queueInstance.enqueue(200);
        queueInstance.enqueue(300);
        queueInstance.enqueue(400);
        queueInstance.enqueue(500);
        System.out.println(queueInstance.toString());
        assertEquals("1º elemento sai bem", (Integer) 100, queueInstance.dequeue());
        assertEquals("2º elemento sai bem", (Integer) 200, queueInstance.dequeue());
        assertEquals("3º elemento sai bem", (Integer) 300, queueInstance.dequeue());
        assertEquals("4º elemento sai bem", (Integer) 400, queueInstance.dequeue());
        assertEquals("5º elemento sai bem", (Integer) 500, queueInstance.dequeue());
    }

    @Test(expected = QueueEmptyException.class)
    public void daqueue_True_emptyQueueException() {
        queueInstance.dequeue();
    }

    public void testAgainstLinkedList(int n) {
        
        java.util.Queue<Integer> javaQueue = new LinkedList<>();
        java.util.Random random = new Random();

        for (int i = 0; i < n; i++) {
            if (queueInstance.isEmpty() || random.nextInt(3) != 0) {
                int elem = random.nextInt();
                queueInstance.enqueue(elem);
                javaQueue.offer(elem);
            } else
                assertEquals("Verificando metodos de tirar elementos", javaQueue.poll(), queueInstance.dequeue());
                assertEquals("Verificando isEmpty ambas implementações", javaQueue.isEmpty(), queueInstance.isEmpty());
                assertEquals("Verificando size ambas implementações", javaQueue.size(), queueInstance.size());
        }
    }

    @Test
    public void com10(){
        testAgainstLinkedList(10);
    }
    
    @Test
    public void com50(){
        testAgainstLinkedList(50);
    }
}
