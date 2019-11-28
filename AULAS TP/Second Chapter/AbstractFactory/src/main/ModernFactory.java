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
public class ModernFactory implements FunitureFactory {

    @Override
    public Chair createChair() {
        return new ModernChair(210, "Modern Chair Model 1", false);
    }

    @Override
    public Sofa createSofa() {
       return new ModernSofa(123, "Modern Sofa", false);
    }

    @Override
    public CoffeTable createCoffeTable() {
        return new ModernCoffe(31, "Modern CoffeTable", 12.0);
        
    }
    
    
}
