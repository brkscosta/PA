/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Observable;

/**
 *
 * @author patricia.macedo
 */
public abstract class Command  {
    /**
     * msg - receiver of command
     */
    protected Message msg;

    public Command(Message msg) {
        this.msg = msg;
    }

   /**
    * actions to perform when the command is executed
    */
    public abstract void execute() ;
}
