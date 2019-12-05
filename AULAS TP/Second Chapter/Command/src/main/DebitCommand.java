/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 * CONCRETE COMMAND
 * @author BRKsCosta
 */
public class DebitCommand implements Command {
    private final Bank receiver;
    private final String accountNumber;
    private final double amount;

    public DebitCommand(Bank receiver, String accountNumber, double amount) {
        this.receiver = receiver;
        this.accountNumber = accountNumber;
        this.amount = amount;
    }
    
    @Override
    public boolean execute() {
        return receiver.debitAccount(accountNumber, amount);
        
    }

    @Override
    public boolean undo() {
        //return receiver.creditAccount(accountNumber, amount);
        throw new UnsupportedOperationException("Cannot return money");
        
    }
    
    
}
