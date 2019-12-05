/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.List;

/**
 * CLIENT
 *
 * @author BRKsCosta
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Bank bank = new Bank();
        
        List<Command> commands = new ArrayList<>();
        
        
        System.out.println(bank);
        
        Command credit = new CreditCommand(bank, "1002", 10.0);
        
        Command transfer = new TransferCommand(bank, "1002", "1001", 50.0);

        TaskManager manager = new TaskManager();
        manager.addTask(credit);
        manager.addTask(transfer);
        manager.runTasks();
        manager.addTask(credit);
        manager.addTask(transfer);
        manager.runTasks();
        System.out.println(bank);
        
    }
    
}
