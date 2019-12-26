/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factoryM;

import factoryM.myShapes.MyShape;
import java.util.ArrayList;
import javafx.scene.Group;

import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.layout.Pane;

/**
 *
 * @author Utilizador
 */
public abstract class ShapeCreator {
    // FACTORY METHOD
    protected abstract MyShape getShape(String type, int x, int y);

}
