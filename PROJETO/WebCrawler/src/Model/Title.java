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
public class Title {
    
    private String titleName = "";

    public Title(String name) {
        
        if ("".equals(name) || name == null)
            this.titleName = "Título não encontrado";
        
        this.titleName = name;
    }

    public String getTitleName() {
        return titleName;
        
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    @Override
    public String toString() {
        return "Title Name: " + this.titleName + "\n";
    }
    
}
