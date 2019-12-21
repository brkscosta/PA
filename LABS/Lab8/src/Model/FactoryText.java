/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Utilizador
 */
public class FactoryText {

    private static int count = 0;
    private static String[] msgs = {" ola amanha",
        " hoje é dia de Natal",
        "padrão command e memento",
        " a luisa foi ao cinema",
        " o rui faltou a aula"};

    /**
     *
     * @return - a sentence, in an iterator mode
     */
    public static String getText() {
        return msgs[(count++) % msgs.length];

    }

}
