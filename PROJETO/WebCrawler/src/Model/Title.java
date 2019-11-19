package Model;

/**
 * Class that represents the <code>Vertex</code> on graph.
 * 
 * @author BRKsCosta
 * 
 */
public class Title {
    
    private String titleName = "";
    
   /**
    * Build a new object of this type 
    * 
    * @param name Title name
    */
    public Title(String name) {
        
        if ("".equals(name) || name == null)
            this.titleName = "Título não encontrado";
        
        this.titleName = name;
    }
    
    /**
     * Get a name of the title
     * 
     * @return Name of title
     */
    public String getTitleName() {
        return titleName;
        
    }
    
    /**
     *  Set a title name
     * 
     * @param titleName String title name to set
     */
    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }
    
    /**
     * To print title name
     * 
     * @return Formated title name
     */
    @Override
    public String toString() {
        return "Title Name: " + this.titleName + "\n";
    }
    
}
