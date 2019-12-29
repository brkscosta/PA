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
public class MyPrisma extends MyShape {

    private int height, with, depth;
    final PhongMaterial redMaterial = new PhongMaterial(Color.RED);

    public MyPrisma(int height, int with, int depth, int x, int y) {
        this.height = height;
        this.with = with;
        this.depth = depth;
        Box b = new Box(with, height, depth);
        b.setTranslateX(x);
        b.setTranslateY(y);
        redMaterial.setSpecularColor(Color.BISQUE);
        b.setMaterial(redMaterial);
        b.setRotationAxis(Rotate.Y_AXIS);
        b.setRotate(15);
        super.figure = b;   
    }

}
