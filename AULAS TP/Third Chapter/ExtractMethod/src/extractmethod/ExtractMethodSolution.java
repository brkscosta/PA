/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extractmethod;

/**
 *
 * @author Utilizador
 */
public class ExtractMethodSolution {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String titulo1 = "Duplicate Code";
        String titulo2 = "Extract Method";
        printTitle(titulo1, titulo2);
        
    }
    
    private static void printTitle(String title1, String title2) {
        for (int i = 0; i < 4; i++) {
            System.out.println();
        }
        for (int i = 0; i < title1.length(); i++) {
            System.out.print("*");
        }
        System.out.println("\n" + title1);

        for (int i = 0; i < 3; i++) {
            System.out.println();
        }
        for (int i = 0; i < title2.length(); i++) {
            System.out.print("-");
        }
        System.out.println("\n" + title2);
    }
    
    private void printLine(String what, int times){
        for(int i = 0; i < times; i++)
            System.out.println(what);
    }
}
