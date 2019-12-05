package main;

import java.util.HashMap;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author BRKsCosta
 */
public class Bank {
    
    Map<String, Double> acoounts;

    public Bank() {
        this.acoounts = new HashMap();
        
        this.acoounts.put("1001", 0.0);
        this.acoounts.put("1002", 100.0);
    }
    
    public boolean creditAccount(String accountNumber, double amount) {
        
        if(amount <= 0) return false;
        
        Double balance = acoounts.get(accountNumber);
        
        if(balance == null)  return false;
        
        balance += amount;
        
        acoounts.put(accountNumber, balance);
        
        return true;
        
    }
    
     public boolean debitAccount(String accountNumber, double amount) {
        
        if(amount <= 0) return false;
        
        Double balance = acoounts.get(accountNumber);
        
        if(balance == null)  return false;
        
        
        if(balance - amount < 0) return false;
        
        balance -= amount;
        
        acoounts.put(accountNumber, balance);
        
        return true;
        
    }

    @Override
    public String toString() {
        return "Bank{" + "acoounts=" + acoounts + '}';
    }
    
     
     
}
