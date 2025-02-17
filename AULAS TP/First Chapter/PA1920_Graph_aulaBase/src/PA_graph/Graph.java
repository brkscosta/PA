/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PA_graph;

/**
 *
 * @author PM-Uninova
 * @param <V>
 * @param <E>
 */
public interface Graph<V, E> {

    /**
     * Returns the number of vertices of the graph
     *
     * @return
     */
    public int numVertices();
    /**
     * Returns the number of edges of the graph
     */
    public int numEdges();
    /**
     * Returns the vertices of the graph as an iterable collection
     */
    public Iterable<Vertex<V>> vertices();

    /**
     * Returns the edges of the graph as an iterable collection
     *
     * @return
     */
    public Iterable<Edge<E, V>> edges();
    /**
     * Returns the edges incident on a vertex as an iterable collection
     */
    public Iterable<Edge<E, V>> incidentEdges(Vertex<V> v)
            throws InvalidEdgeException;
    /**
     * Return the  vertex oposite to the vertex v, which belongs to edge e.
     */
     public Vertex<V> opposite(Vertex<V> v, Edge<E, V> e)
            throws InvalidVertexException, InvalidEdgeException;
    /**
     * Tests whether two vertices are adjacent
     */
    public boolean areAdjacent(Vertex<V> u, Vertex<V> v)
            throws InvalidVertexException;
    /**
     * Inserts and return a new vertex with a given element
     */
    public Vertex<V> insertVertex(V o);
    /**
     * Inserts and return a new edge with a given element between two vertices
     */
    public Edge<E, V> insertEdge(Vertex<V> u, Vertex<V> v, E o)
            throws InvalidVertexException;
    public Edge<E, V> insertEdge(V elem1, V elem2, E o)
            throws InvalidVertexException;
    /**
     * Removes a vertex and all its incident edges and returns the element
     * stored at the removed vertex
     */
    public V removeVertex(Vertex<V> v) throws InvalidVertexException;
    /**
     * Removes an edge and return its element
     */
    public E removeEdge(Edge<E, V> e) throws InvalidEdgeException;
    
    /*
    * Get the max degree of an vertex in a graph
    */
    public Vertex<V> getMaxDegreeVertex() throws InvalidVertexException;
}
