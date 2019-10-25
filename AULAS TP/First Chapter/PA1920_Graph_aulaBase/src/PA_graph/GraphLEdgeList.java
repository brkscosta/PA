/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA_graph;

import java.util.HashMap;
import java.util.HashSet;

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

        if (listVertices.containsKey(elem)) {
            throw new InvalidVertexException("Vertex" + elem + " alredy exists!");
        }

        MyVertex newVertex = new MyVertex(elem);
        listVertices.put(elem, newVertex);

        return newVertex;
    }

    @Override
    public boolean areAdjacent(Vertex<V> u, Vertex<V> v) throws InvalidVertexException {
        MyVertex myU = checkVertex(u);
        MyVertex myV = checkVertex(v);

        for (Edge<E, V> edge : listEdges.values()) {
            Vertex<V>[] vertices = edge.vertices();

            if (vertices[0] == u && vertices[1] == v || vertices[1] == u && vertices[0] == v) {
                return true;
            }
        }
        return true;
    }

    @Override
    public Edge<E, V> insertEdge(V elem1, V elem2, E elemEdge) throws InvalidVertexException {

        Vertex<V> firstVertex = listVertices.get(elem1);

        Vertex<V> lastVertex = listVertices.get(elem2);

        if (lastVertex == null || firstVertex == null) {
            throw new InvalidVertexException("Vertex doesnt exists");
        }

        return insertEdge(firstVertex, lastVertex, elemEdge);

    }

    @Override
    public Edge<E, V> insertEdge(Vertex<V> u, Vertex<V> v, E elemEdge) throws InvalidVertexException {

        if (elemEdge == null) {
            throw new InvalidEdgeException("Values of edge is null");
        }

        MyVertex myU = checkVertex(v);
        MyVertex myV = checkVertex(v);

        MyEdge edge = new MyEdge(elemEdge, myU, myV);
        listEdges.put(elemEdge, edge);
        return edge;
    }

    @Override
    public E removeEdge(Edge<E, V> e) throws InvalidEdgeException {

        checkEdge(e);

        for(Edge<E, V> edges : listEdges.values()) {

            this.listEdges.remove(edges.element());

        }

        this.listEdges.remove(e.element());
        return e.element();

    }

    @Override
    public V removeVertex(Vertex<V> v) throws InvalidVertexException {
        checkVertex(v);

        for (Edge<E, V> vertex : incidentEdges(v)) {
            this.listEdges.remove(vertex.element());
        }

        this.listVertices.remove(v.element());
        return v.element();

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
