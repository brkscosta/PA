/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import main.FunitureFactory;

/**
 *
 * @author BRKsCosta
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        List<Furniture> shoppingCart = new ArrayList<>();
        
        //Alterar tipo defabrica pretendida
        FunitureFactory abstractFactory = new VictorianFactory();

        Chair chair = abstractFactory.createChair();
        Sofa sofa = abstractFactory.createSofa();
        CoffeTable coffe = abstractFactory.createCoffeTable();
        
        shoppingCart.add(chair);
        shoppingCart.add(sofa);
        shoppingCart.add(coffe);
        
        Collections.sort(shoppingCart, (Furniture o1, Furniture o2) -> 
                ((int)(o1.getPrice() - o2.getPrice())));

        double total = 0.0;
        for (Furniture furniture : shoppingCart) {
            System.out.println(furniture);
            total += furniture.getPrice();
        }
        System.out.println(total);
    }

}
