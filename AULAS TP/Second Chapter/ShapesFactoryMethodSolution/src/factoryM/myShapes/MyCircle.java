/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factoryM.myShapes;

import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Circle;

/**
 *
 * @author patricia.macedo
 */
public class MyCircle extends MyShape {
  public MyCircle(int radius, int x, int y) {
        
        super.figure = new Circle(radius);
        super.figure.setTranslateX(x);
        super.figure.setTranslateY(y);
    }
    
      

}
