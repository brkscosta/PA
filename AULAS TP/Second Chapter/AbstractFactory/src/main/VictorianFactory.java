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
public class VictorianFactory implements FunitureFactory {

    @Override
    public Chair createChair() {
        return new VictorianChair(210, "Victorian Chair Model 1", false);
    }

    @Override
    public Sofa createSofa() {
       return new VictorianSofa(123, "Victorian Sofa", true);
    }

    @Override
    public CoffeTable createCoffeTable() {
        return new VictorianCoffe(31, "Victorian CoffeTable", 12.0);
        
    }
    
    
}
