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
        Vertex<Local> v1 = grafo.insertVertex(quintaDoConde);
        Local almada = new Local("Almada");
        Vertex<Local> v2 = grafo.insertVertex(almada);
        Local barreiro = new Local("Barreiro");
        Vertex<Local> v3 = grafo.insertVertex(barreiro);
        Local lisboa = new Local("Lisboa");
        Vertex<Local> v4 = grafo.insertVertex(lisboa);
        
        
        Ponte ptAlmadaQuinta = new Ponte("Vasco da Gama", 14);
        Ponte ptBarreiroQuinta = new Ponte("Bento Gonçalves", 9);
        Ponte pt25Abril = new Ponte("25 de Abril", 10);
        
        grafo.insertEdge(v1, v2, ptAlmadaQuinta);
        grafo.insertEdge(v1, v4, ptBarreiroQuinta);
        grafo.insertEdge(v2, v3, pt25Abril);
        
        System.out.println(grafo.numEdges());
        System.out.println(grafo.numVertices());
        
        System.out.println("Vertex: " + grafo.vertices());
        System.out.println("Edges: " + grafo.edges());
        
        System.out.println("Incidentes de Almada " + grafo.incidentEdges(v2));
        System.out.println("Incidentes de Barreiro " + grafo.incidentEdges(v3));
        
        System.out.println(grafo.areAdjacent(v1, v3));
        
        
    }

}
