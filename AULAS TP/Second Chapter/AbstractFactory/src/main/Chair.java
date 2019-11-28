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
public abstract class Chair implements Furniture {
    
    private double price;
    private String name;
    private boolean hasLegs;

    public Chair(double price, String name, boolean hasLegs) {
        this.price = price;
        this.name = name;
        this.hasLegs = hasLegs;
    }

    @Override
    public String toString() {
        return "Chair{" + "price=" + price + ", name=" + name + ", hasLegs=" + hasLegs + '}';
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
        return hasLegs;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHasLegs(boolean hasLegs) {
        this.hasLegs = hasLegs;
    }
    
    
    
}
