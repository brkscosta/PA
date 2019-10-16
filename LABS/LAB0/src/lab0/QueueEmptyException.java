package lab0;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author patricia.macedo
 */
public class QueueEmptyException extends RuntimeException {

    public QueueEmptyException() {
        super("The Queue is empty");
    }

    public QueueEmptyException(String message) {
        super(message);
    }
    
}
