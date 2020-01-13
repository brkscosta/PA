/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxextractclass;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Utilizador
 */
public class JavaFXExtractClass extends Application {
// Large Class

    private Slider slider;
    private TextField totalTf;
    private Button multiplyBtn;
    private Button clearBtn;

    private Scene scene;
    private int total;

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();

        GridPane root = initComponents();

        Scene myScene = new Scene(root, 500, 250);

        primaryStage.setTitle("Calculator");
        primaryStage.setScene(myScene);
        primaryStage.show();
    }
    
    //Large Method
    private GridPane initComponents() {
        //... Initialize components
        total = 1;
        slider = new Slider(0, 100, 1);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMinorTickCount(20);
        slider.setMajorTickUnit(20);
        totalTf = new TextField();
        multiplyBtn = new Button("Multiply");
        clearBtn = new Button("Reset");
        multiplyBtn.setOnAction(
                event -> {
                    multiplyBy(getUserInput());
                    update();
                });
        clearBtn.setOnAction(
                event -> {
                    reset();
                    update();
                });

        totalTf.setText(total + "");
        totalTf.setEditable(false);
        //... Layout the components.
        GridPane content = new GridPane();
        content.setPadding(new Insets(15, 15, 15, 15));
        content.setVgap(10);
        content.setHgap(10);
        content.add(new Label("INPUT"), 0, 0);
        content.add(slider, 1, 0);
        content.add(multiplyBtn, 2, 0);
        content.add(new Label("TOTAL"), 0, 1);
        content.add(totalTf, 1, 1);
        content.add(clearBtn, 2, 1);
        return content;
    }

    public void update() {

        totalTf.setText(total + "");
    }

    public int getUserInput() {
        int value = (int) slider.getValue();
        return value;
    }

    public void showError(String errMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error Message");
        alert.setContentText(errMessage);
        alert.showAndWait();
    }

    public void resetInput() {
        slider.setValue(1.0);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void reset() {
        total = 1;

    }

    public void multiplyBy(Integer operand) {

        total = total * operand;

    }
}
