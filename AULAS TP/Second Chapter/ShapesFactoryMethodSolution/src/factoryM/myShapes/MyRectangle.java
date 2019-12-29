/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factoryM.myShapes;

import javafx.scene.shape.Rectangle;

/**
 *
 * @author patricia.macedo
 */
public class MyRectangle extends MyShape{

    public MyRectangle(int height, int with, int x, int y) {
        super.figure= new Rectangle(with, height);
        super.figure.setTranslateX(x);
        super.figure.setTranslateY(y);
    }
  
    
}
