/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patterns.Singleton;

/**
 *  This class throw an exception by logger
 * @author BRKsCosta
 */
public class LoggerException extends RuntimeException {

    public LoggerException(String message) {
        super(message);
    }
    
}
