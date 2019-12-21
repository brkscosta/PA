/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import javafx.scene.Group;

/**
 *
 * @author PM
 */
public class Menu {

    private static final String FILENAME_OUT = "msgoOut.txt";

    private final HashMap<String, Command> itemsCmd; //liga as opcoes aos comandos

    private final Message msg; //Receiver e Originator

    private CareTaker careTaker;

    private Group scene;
    private final static Scanner sc = new Scanner(System.in);

    private ArrayList<Command> macroCommands;

    /**
     * Create Menu, and inicialize commands and Message.
     */
    public Menu() {

        itemsCmd = new HashMap();
        msg = new Message(" Esta é a primeira frase a ser alterada depois");
        this.careTaker = new CareTaker(msg);
        macroCommands = new ArrayList<>();
        createCmdOptions();

    }

    private void writeMenu() {
        System.out.println("\tMenu ");
        System.out.println("------------------");
        System.out.println("1 - NEW");
        System.out.println("2 - CODIFY");
        System.out.println("3 - ALLCAPS");
        System.out.println("4 - EXPORT");
        System.out.println("------------------");
        System.out.println("5 - UNDO");
        System.out.println("------------------");
        System.out.println("6 - NEW MACRO");
        System.out.println("7 - PLAY MACRO");
        System.out.println("------------------");
        System.out.println("s -sair");
        System.out.println("------------------");
        System.out.println("Introduz a opção >> ");
    }

    /**
     * Execute the Menu created.
     */
    public void executeMenu() {
        String op = "";
        writeMenu();
        op = sc.next();
        while (!op.equals("s")) {
            switch (op) {
                case "1"://nivel 3
                    executeCmd(op);
                    break;
                case "2"://nivel 2
                    executeCmd(op);
                case "3":
                    executeCmd(op);
                    break;
                case "4"://nivel 3
                    System.out.println("Exporting....");
                    executeCmd(op);//nivel 3
                    break;
                case "5":
                    undo();
                    break;
                case "6":
                    newMacro();
                    break;
                case "7":
                    playMacro();
                    break;
            }
            writeMenu();
            op = sc.next();
        }
    }

    private void createCmdOptions() {
        //nivel 2 e 3

        // Create for command Codify
        Command commandCodify = new CommandCodify(msg);
        itemsCmd.put("2", commandCodify);
        // Create for command AllCaps
        Command commandAllCap = new CommandAllCaps(msg);
        itemsCmd.put("3", commandAllCap);

        // To save file
        Command commandSave = new CommandSave(msg);
        this.itemsCmd.put("4", commandSave);

        // New Menssage
        Command commandNewMsg = new CommandNew(msg);
        this.itemsCmd.put("1", commandNewMsg);

    }

    //nivel 5
    private void playMacro() {
        for (Command c : macroCommands) {
            c.execute();
            this.careTaker.saveState();
            System.out.println(this.msg);
        }
    }

    //nivel 5
    private void newMacro() {
        this.macroCommands.clear();
    }
    //nivel 4

    private void undo() {
        careTaker.restoresState();
        System.out.println(this.msg);
    }

    private void executeCmd(String op) {
        macroCommands.add(itemsCmd.get(op));
        System.out.println(this.msg);
    }

}
