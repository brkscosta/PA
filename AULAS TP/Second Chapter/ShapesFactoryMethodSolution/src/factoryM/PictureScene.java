/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factoryM;

import factoryM.myShapes.MyShape;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;

/**
 *
 * @author PM
 */
public class PictureScene {

    private PerspectiveCamera camera;
    private SubScene subscene;
    private int dim;
    private int x, y; //coordenadas de posição
    private Group group;

    private ShapeCreator creator;//factory method (creator)

    public PictureScene(int dim) {
        this.dim = dim;
        this.creator = new Shapes2DCreator();
        initComponents();
    }
    private void createScene() {
        group = new Group();
        subscene.setRoot(group);
        subscene.setCamera(camera);
    }
    public void scene2D() {
        creator = new Shapes2DCreator();
        createScene();
    }
    public void scene3D() {
        creator = new Shapes3DCreator();
        createScene();
    }
    public void addShape(String type) {
        MyShape shape = creator.getShape(type, x, y);
        group.getChildren().add(shape.getFigure());
    }

    public SubScene getSubscene() {
        return subscene;
    }

    private void initComponents() {
        camera = new PerspectiveCamera();
        x = this.dim / 2;
        y = this.dim / 2;
        group = new Group();
        subscene = new SubScene(group, this.dim, this.dim);
    }

    public void incX() {
        x = (x + 70) % (this.dim - 50) + 50;
    }

    public void incY() {
        y = (y + 55) % (this.dim - 50) + 50;
    }

}
