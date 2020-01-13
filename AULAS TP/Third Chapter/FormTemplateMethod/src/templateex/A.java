/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package templateex;

/**
 *
 * @author patricia.macedo
 */
public abstract class A {

    protected char value;
  
   
    public A(char value) {
        this.value = value;
        
    }

    public void setValue(char value) {
        this.value = value;
    }
    
    public String metodo(String frase){
        frase = this.fraseReplace(frase);
        
        //capitalize
        frase = frase.toUpperCase();
        
        //enclose
        return frase = this.fraseEnclose(frase);
        
    }
    
   public abstract String fraseReplace(String frase);
   public abstract String fraseEnclose(String frase);
   
}
