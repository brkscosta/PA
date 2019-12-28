/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factoryM.myShapes;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

/**
 *
 * @author patricia.macedo
 */
public class MySphere extends MyShape {
 final PhongMaterial greenMaterial = new PhongMaterial(Color.AQUAMARINE);

    private int radius;

    public MySphere(int radius, int x, int y) {
        this.radius = radius;
        Sphere s = new Sphere(radius);
        s.setMaterial(greenMaterial);
        super.figure=s;
        super.figure.setTranslateX(x);
        super.figure.setTranslateY(y);
    }

}
