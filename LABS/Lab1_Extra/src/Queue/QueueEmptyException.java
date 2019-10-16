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
public class QueueEmptyException extends RuntimeException {

    public QueueEmptyException() {
        super("The queue has no elements to retrieve.");
    }
    
}
