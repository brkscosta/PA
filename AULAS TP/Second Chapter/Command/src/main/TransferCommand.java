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
public class TransferCommand implements Command {

    private final Bank receiver;
    private final String accountNumberOrigin;
    private final String accountNumberDest;
    private final double amount;

    public TransferCommand(Bank receiver, String accountNumberOrigin, String accountNumberDest, double amount) {
        this.receiver = receiver;
        this.accountNumberOrigin = accountNumberOrigin;
        this.accountNumberDest = accountNumberDest;
        this.amount = amount;
    }
    
    @Override
    public boolean execute() {
        if(!receiver.debitAccount(accountNumberOrigin, amount))
            return false;
        
        return receiver.creditAccount(accountNumberDest, amount);
    }

    @Override
    public boolean undo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
