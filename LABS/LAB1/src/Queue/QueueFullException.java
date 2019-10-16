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
public class QueueFullException extends RuntimeException {

    public QueueFullException() {
        super("The queue is has reached its max capacity.");
    }
    
}
