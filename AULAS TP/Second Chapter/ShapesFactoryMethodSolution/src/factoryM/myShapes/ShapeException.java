/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factoryM.myShapes;

/**
 *
 * @author Utilizador
 */
public class ShapeException extends RuntimeException{

    public ShapeException(String type) {
        super( type + " : this type shape does not exist");
    }
    
}
