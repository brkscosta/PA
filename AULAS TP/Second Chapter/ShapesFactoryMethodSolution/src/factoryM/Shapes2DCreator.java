/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factoryM;

import factoryM.myShapes.ShapeException;
import factoryM.myShapes.MyCircle;
import factoryM.myShapes.MyRectangle;
import factoryM.myShapes.MyShape;
import factoryM.myShapes.MySquare;



/**
 *
 * @author Utilizador
 */
public class Shapes2DCreator extends ShapeCreator {
    
    @Override
    public MyShape getShape(String type, int x, int y) {
        switch (type) {
            case "QUADRADO":
                return new MySquare(30, x, y);
            case "CIRCULO":
                MyCircle c = new MyCircle(30, x, y);
                return c;
            case "RETANGULO":
                return new MyRectangle(30, 50, x, y);
            default:
                throw new ShapeException(type);
        }

    }
}
