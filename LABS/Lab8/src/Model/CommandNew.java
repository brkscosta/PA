/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author BRKsCosta
 */
public class CommandNew extends Command {

    public CommandNew(Message msg) {
        super(msg);
    }

    @Override
    public void execute() {
        this.msg.setTxt(FactoryText.getText());
        this.msg.setDate();
        
    }
    
}
