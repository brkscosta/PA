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
public class QueueFullException extends RuntimeException {

    public QueueFullException() {
        super(" The queue is Full");
    }

    public QueueFullException(String message) {
        super(message);
    }
    
    
    
}
