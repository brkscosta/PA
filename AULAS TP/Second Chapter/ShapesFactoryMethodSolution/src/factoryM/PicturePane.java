/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factoryM;


import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author PM
 */
public class PicturePane extends VBox {

   
    private Button btn2D, btn3D, btn1, btn2, btn3;
    private PictureScene pictureScene;
  
   
  
    public PicturePane(int dim) {
        
        this.pictureScene= new PictureScene(dim);
        initComponents();
    }

    private void initComponents() {
       
       
        HBox buttons = new HBox(5);
        btn2D = new Button("Init Picture2D");
        btn3D = new Button("Init Picture3D");
        btn1 = new Button("Circle");
        btn2 = new Button("Rectangle");
        btn3 = new Button("Square");

        buttons.getChildren().addAll(btn2D, btn3D, btn1, btn2, btn3);
        getChildren().add(buttons);
        getChildren().add(pictureScene.getSubscene());
        setTriggersButtons();
    }

    

    private void setTriggersButtons() {
        btn1.setOnAction(e -> {
            pictureScene.addShape("CIRCULO");
            pictureScene.incX();

        });
        btn2.setOnAction(e -> {
            pictureScene.addShape("RETANGULO");
            pictureScene.incY();

        });
        btn3.setOnAction(e -> {
            pictureScene.addShape("QUADRADO");
            pictureScene.incX();
            pictureScene.incY();

        });
        btn2D.setOnAction(e -> {
            pictureScene.scene2D();
        });
        btn3D.setOnAction(e -> {
           pictureScene.scene3D();
        });

    }
   

}
