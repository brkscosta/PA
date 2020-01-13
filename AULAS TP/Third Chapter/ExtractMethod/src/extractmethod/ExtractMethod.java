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
public class ExtractMethod {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String titulo1 = "Duplicate Code";
        String titulo2 = "Extract Method";

        for (int i = 0; i < 4; i++) {
            System.out.println();
        }
        for (int i = 0; i < titulo1.length(); i++) {
            System.out.print("*");
        }
        System.out.println("\n" + titulo1);

        for (int i = 0; i < 3; i++) {
            System.out.println();
        }
        for (int i = 0; i < titulo2.length(); i++) {
            System.out.print("-");
        }
        System.out.println("\n" + titulo2);
    }
}
