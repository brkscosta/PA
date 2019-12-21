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
public class CommandAllCaps  extends Command {

    public CommandAllCaps(Message msg) {
        super(msg);
    }

    @Override
    public void execute() {
       String msgCap = msg.toString().toUpperCase();
       msg.setTxt(msgCap);
       msg.setDate();
    }
    
}
