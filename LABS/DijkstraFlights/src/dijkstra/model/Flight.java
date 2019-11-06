/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijkstra.model;

import java.util.Objects;

/**
 *
 * @author brunomnsilva
 */
public class Flight {
    
    private final String company;
    private final int number;
    private final int distanceMiles;
    private final int durationMinutes;
    private final int priceEuros; //rounded values

    public Flight(String company, int number, int distanceMiles, 
            int hours, int minutes, int priceEuros) {
        
        if(hours < 0 || hours > 23) throw new IllegalArgumentException("Hours must be in [0,23]");
        if(minutes < 0 || minutes > 59) throw new IllegalArgumentException("Minutes must be in [0,59]");
        if(priceEuros <= 0) throw new IllegalArgumentException("Price must be > 0");
        
        this.company = company;
        this.number = number;
        this.distanceMiles = distanceMiles;
        this.durationMinutes = hours * 60 + minutes;
        this.priceEuros = priceEuros;
    }

    public int getDistanceMiles() {
        return distanceMiles;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public int getPriceEuros() {
        return priceEuros;
    }
    
    public String getDescription() {
        return company + " " + number;
    }

    @Override
    public String toString() {
        return String.format("%s {%d miles, %d minutes, %d €}", 
                getDescription(), distanceMiles, durationMinutes, priceEuros);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Flight other = (Flight) obj;
        if (this.number != other.number) {
            return false;
        }
        return Objects.equals(this.company, other.company);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.company);
        hash = 41 * hash + this.number;
        return hash;
    }
    
    
    
    
    
}
