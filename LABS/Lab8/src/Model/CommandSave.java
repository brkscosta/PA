/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author BRKsCosta
 */
public class CommandSave extends Command {

    public CommandSave(Message msg) {
        super(msg);
    }

    @Override
    public void execute() {
        FileWriter fw = null;
        String basePath = "file";

        try{
            fw = new FileWriter(basePath + ".txt");
            fw.write(msg.getTxt());
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{
                fw.close();
            }catch(IOException e){
                System.out.println(e.getMessage());
            }
        }
    }
    
}
