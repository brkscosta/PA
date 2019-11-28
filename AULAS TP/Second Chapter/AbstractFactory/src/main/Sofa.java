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
public abstract class Sofa implements Furniture{
    
    private double price;
    private String name;
    private boolean isLeather;

    public Sofa(double price, String name, boolean hasLegs) {
        this.price = price;
        this.name = name;
        this.isLeather = hasLegs;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getName() {
        return name;
    }

    public boolean isHasLegs() {
        return isLeather;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHasLegs(boolean hasLegs) {
        this.isLeather = hasLegs;
    }

    @Override
    public String toString() {
        return "Sofa{" + "price=" + price + ", name=" + name + ", isLeather=" + isLeather + '}';
    }
    
    
    
}
