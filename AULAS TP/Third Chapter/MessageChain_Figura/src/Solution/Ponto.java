/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Solution;

import messagechain.*;
import javafx.scene.paint.Color;

/**
 *
 * @author Utilizador
 */
public class Ponto { //Data Class
   private int x,y;
   

    public Ponto(int x, int y) {
        this.x = x;
        this.y = y;
       
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x +","+y+")";
    }

   
   
    
}
