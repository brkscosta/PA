/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package templateex;

/**
 *
 * @author Utilizador
 */
public class C extends A {

    public C(char value) {
        super(value);
    }

    //duplicate code in subclasses B and C

   @Override
    public String fraseReplace(String frase) {
        return frase.replace(this.value, 'C');
    }

    @Override
    public String fraseEnclose(String frase) {
        return  frase = "{" + frase + "}";
    }
}
