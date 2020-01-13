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
public class B extends A {

    public B(char value) {
        super(value);
    }

    @Override
    public String fraseReplace(String frase) {
        return frase.replace(this.value, 'B');
    }

    @Override
    public String fraseEnclose(String frase) {
        return  frase = "[" + frase + "]";
    }
    
    
}
