/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab0;

/**
 *
 * @author BRKsCosta
 */
class QueueFullException extends Exception {

    public QueueFullException() {
        super(" The queue is Full");
    }

    public QueueFullException(String message) {
        super(message);
    }
}
