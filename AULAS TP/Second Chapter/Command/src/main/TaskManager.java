/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.List;

/**
 * INVOKER
 *
 * @author BRKsCosta
 */
public class TaskManager {

    List<Command> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public void clearTasks() {
        tasks.clear();

    }

    public void addTask(Command c) {
        tasks.add(c);

    }

    public void runTasks() {
        tasks.forEach((c) -> {
            System.out.println("Command: " + c.getClass().getSimpleName() 
                    + " result = " + c.execute());
        });
    }

}
