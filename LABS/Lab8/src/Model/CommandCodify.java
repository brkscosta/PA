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
public class CommandCodify extends Command {

    public CommandCodify(Message msg) {
        super(msg);
    }

    @Override
    public void execute() {
        
        String msgCodify = "";
        
        String txt = this.msg.getTxt();
        
        msgCodify = txt.replace(" ", "?");
        msgCodify = txt.replace("n", " ");
        
        this.msg.setTxt(msgCodify);
        this.msg.setDate();
    }

}
