/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author BRKsCosta
 */
public abstract class CoffeTable implements Furniture {
    
    private double price;
    private String name;
    private double height;

    public CoffeTable(double price, String name, double heigth) {
        this.price = price;
        this.name = name;
        this.height = heigth;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public String getName() {
        return name;
    }

    public double isHasLegs() {
        return height;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "CoffeTable{" + "price=" + price + ", name=" + name + ", height=" + height + '}';
    }
    
    
    
}
