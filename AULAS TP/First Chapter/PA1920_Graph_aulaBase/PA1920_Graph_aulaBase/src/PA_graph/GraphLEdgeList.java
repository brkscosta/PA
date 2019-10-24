/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA_graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author patricia.macedo
 */
public class GraphLEdgeList<V, E> implements Graph<V, E> {

    private HashMap<V, Vertex<V>> listVertices; // conjunto de vertices
    private HashMap<E, Edge<E, V>> listEdges; // conjunto de arestas

    public GraphLEdgeList() {
        listVertices = new HashMap<>();
        listEdges = new HashMap<>();
    }

    private MyVertex checkVertex(Vertex<V> p) throws InvalidVertexException {
        if (p == null) {
            throw new InvalidVertexException("WRONG vertex");
        }
        if (!this.listVertices.containsValue(p)) {
            throw new InvalidVertexException("vertex does not exist");
        }
        try {
            return (MyVertex) p;
        } catch (ClassCastException e) {
            throw new InvalidVertexException("WRONG vertex");
        }
    }

    private MyEdge checkEdge(Edge<E, V> ed) throws InvalidEdgeException {
        if (ed == null) {
            throw new InvalidEdgeException("WRONG edge");
        }
        if (!this.listEdges.containsValue(ed)) {
            throw new InvalidEdgeException("Invalid Edge");
        }
        try {
            return (MyEdge) ed;
        } catch (ClassCastException e) {
            throw new InvalidEdgeException("WRONG edge");
        }
    }

    @Override
    public int numVertices() {
        return listVertices.size();

    }

    @Override
    public int numEdges() {
        return listEdges.size();
    }

    @Override
    public Iterable<Vertex<V>> vertices() {
        return listVertices.values();

    }

    @Override
    public Iterable<Edge<E, V>> edges() {
        return listEdges.values();
    }

    private boolean existEdge(Edge e) {
        return listEdges.containsValue(e);
    }

    @Override
    public Iterable<Edge<E, V>> incidentEdges(Vertex<V> v) throws InvalidEdgeException {
        MyVertex vertex = checkVertex(v);
        HashSet<Edge<E, V>> set = new HashSet<>();
        for (Edge<E, V> edge : listEdges.values()) {
            if (edge.vertices()[0] == v || edge.vertices()[1] == v) {
                set.add(edge);
            }
        }
        return set;

    }

    @Override
    public Vertex<V> opposite(Vertex<V> v, Edge<E, V> e)
            throws InvalidVertexException, InvalidEdgeException {
        checkVertex(v);
        MyEdge edge = checkEdge(e);
        if (edge.vertexA == v) {
            return edge.vertexB;
        }
        if (edge.vertexB == v) {
            return edge.vertexA;
        }
        throw new InvalidVertexException("Invalid vertex");
    }

    @Override
    public Vertex<V> insertVertex(V elem) throws InvalidVertexException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

       

    }

  
    @Override
    public boolean areAdjacent(Vertex<V> u, Vertex<V> v) throws InvalidVertexException {
       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

  
    @Override
    public Edge<E, V> insertEdge(V elem1, V elem2, E elemEdge) throws InvalidVertexException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.


    }

   

    @Override
    public Edge<E, V> insertEdge(Vertex<V> u, Vertex<V> v, E elemEdge) throws InvalidVertexException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public E removeEdge(Edge<E, V> e) throws InvalidEdgeException {
   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public V removeVertex(Vertex<V> v) throws InvalidVertexException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private class MyVertex implements Vertex<V> {

        private V elem;

        public MyVertex(V elem) {
            this.elem = elem;
        }

        @Override
        public V element() throws InvalidVertexException {
            if (elem == null) {
                throw new InvalidVertexException("vertex null");
            }
            return elem;
        }

        @Override
        public String toString() {
            return elem.toString();
        }
    }

    private class MyEdge implements Edge<E, V> {

        private E elem;
        private Vertex<V> vertexA, vertexB;

        public MyEdge(E elem, Vertex<V> vertexA, Vertex<V> vertexB) {
            this.elem = elem;
            this.vertexA = vertexA;
            this.vertexB = vertexB;
        }

        @Override
        public E element() throws InvalidEdgeException {
            if (elem == null) {
                throw new InvalidEdgeException("edge null");
            }
            return elem;
        }

        @Override
        public Vertex<V>[] vertices() {
            Vertex[] vertices = new Vertex[2];
            vertices[0] = vertexA;
            vertices[1] = vertexB;
            return vertices;
        }

        @Override
        public String toString() {
            return elem.toString();
        }

    }

}
