/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factoryM;

import factoryM.myShapes.ShapeException;
import factoryM.myShapes.MyShape;
import factoryM.myShapes.MyPrisma;
import factoryM.myShapes.MyCube;
import factoryM.myShapes.MySphere;


/**
 *
 * @author Utilizador
 */
public class Shapes3DCreator extends ShapeCreator {
  
    @Override
    public MyShape getShape(String type, int x, int y) {
        MyShape shape = null;
        switch (type) {
            case "QUADRADO":
                return new MyCube(30, x, y);
            case "CIRCULO":
                return new MySphere(30, x, y);
            case "RETANGULO":
                return new MyPrisma(30, 60, 60, x, y);
            default:
                throw new ShapeException(type);
        }  
    }
}
