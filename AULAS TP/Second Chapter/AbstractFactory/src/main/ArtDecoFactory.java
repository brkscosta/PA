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
public class ArtDecoFactory implements FunitureFactory {

    @Override
    public Chair createChair() {
        return new ArtDecoChair(210, "ArtDeco Chair Model 1", true);
    }

    @Override
    public Sofa createSofa() {
       return new ArtDecoSofa(123, "ArtDeco Sofa", true);
    }

    @Override
    public CoffeTable createCoffeTable() {
        return new ArtDecoCoffeTable(31, "ArtDEco CoffeTable", 12.0);
        
    }
    
    
}
