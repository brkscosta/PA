/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PA_application;


import PA_graph.Graph;
import PA_graph.GraphLEdgeList;
import PA_graph.Vertex;
import java.util.Iterator;

/**
 *
 * @author PM-Uninova
 */
public class TADGraphA {

    public static void main(String[] args) throws Exception {
        
        Graph<Local, Ponte> grafo = new GraphLEdgeList<>();
        
        Local quintaDoConde = new Local("Quinta do Conde");
        Local almada = new Local("Almada");
        Local barreiro = new Local("Barreiro");
        
        Ponte ptAlmadaQuinta = new Ponte("Almada - Quinta Do Conde", 14);
        Ponte ptBarreiroQuinta = new Ponte("Barreiro - Quinta Do Conde", 9);
        
        grafo.insertVertex(quintaDoConde);
        grafo.insertVertex(almada);
        grafo.insertVertex(barreiro);
        
        grafo.insertEdge(almada, quintaDoConde, ptAlmadaQuinta);
        grafo.insertEdge(barreiro, quintaDoConde, ptBarreiroQuinta);
        
        System.out.println(grafo.numEdges());
        System.out.println(grafo.numVertices());
        
        System.out.println(grafo.vertices());
        System.out.println(grafo.edges());
        
        System.out.println(grafo.areAdjacent(quintaDoConde, almada));
        
    }

}
