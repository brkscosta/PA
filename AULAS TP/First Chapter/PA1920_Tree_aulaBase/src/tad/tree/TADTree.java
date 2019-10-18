/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tad.tree;

/**
 *
 * @author Utilizador
 */
public class TADTree {

    /**
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LinkedTree<String> myTree = new LinkedTree("Animal");
        Position<String> root = myTree.root();
        Position<String> posMamifero = myTree.insert(root, "Mamifero");
        Position<String> posAve = myTree.insert(root, "Ave");
        Position<String> posCao = myTree.insert(posMamifero, "Cao");
        Position<String> posGato = myTree.insert(posMamifero, "Gato");
        myTree.insert(posMamifero, "Vaca");
        myTree.insert(posAve, "Papagaio");
        Position<String> posAguia = myTree.insert(posAve, "Aguia");
        myTree.insert(posAguia, "Aguia Real");
        
        //myTree.remove(posMamifero);
        
        System.out.println("Cao é externo? " + myTree.isExternal(posCao));
        System.out.println("Cao é interno? " + myTree.isInternal(posCao));
        
        for (String elem : myTree.breathOrder())
            System.out.print(elem + " \n");
        System.out.println("\nFIM");
        
        System.out.println("Size of tree " + myTree.size());
    }
}
