/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factoryM.myShapes;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;

/**
 *
 * @author patricia.macedo
 */
public class MyCube extends MyShape {

    private int  with;
    final PhongMaterial blueMaterial = new PhongMaterial(Color.DARKCYAN);

    public MyCube(int with, int x, int y) {    
        this.with = with;      
        Box b = new Box(with, with, with);
        b.setTranslateX(x);
        b.setTranslateY(y);

        blueMaterial.setSpecularColor(Color.BLUE);
        b.setMaterial(blueMaterial);
        b.setRotationAxis(Rotate.Y_AXIS);
        b.setRotate(15);
        super.figure = b;

    }

}
